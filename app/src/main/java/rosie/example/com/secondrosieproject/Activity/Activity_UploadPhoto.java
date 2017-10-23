package rosie.example.com.secondrosieproject.Activity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;

import rosie.example.com.secondrosieproject.MyData.MyGridViewData;
import rosie.example.com.secondrosieproject.MyData.MyPhotoData;
import rosie.example.com.secondrosieproject.R;

public class Activity_UploadPhoto extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1001;
    public static String STORAGE_PATH = "images/";
    public static String DATABASE_PATH = "image";

    Button bt_UploadPhoto, bt_Album;
    ImageView imageView;
    EditText ed_Explain;

    Uri filePath;

    private StorageReference storageRef;
    private DatabaseReference mDataRef;

    private ProgressDialog mProgressDialog;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_photo);

        //root url is included getReference();
        storageRef = FirebaseStorage.getInstance().getReference();
        mDataRef = FirebaseDatabase.getInstance().getReference("/Uploaded_Photo/").child(DATABASE_PATH);
        mProgressDialog = new ProgressDialog(this);
        user = FirebaseAuth.getInstance().getCurrentUser();

        bt_UploadPhoto = (Button) findViewById(R.id.bt_UploadPhoto);
        bt_Album = (Button) findViewById(R.id.bt_Album);
        imageView = (ImageView) findViewById(R.id.ImgView);
        ed_Explain = (EditText) findViewById(R.id.ed_explainPhoto);

        bt_UploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageView!=null) {
                    StorageReference riversRef = storageRef.child(STORAGE_PATH + System.currentTimeMillis() + "." + getImagExt(filePath));
                    //FileUpload실행후
                    riversRef.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @SuppressWarnings("VisibleForTests")
                        @Override   //업로드성공했을 때 리스너
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            SimpleDateFormat sd = new SimpleDateFormat("a hh:mm:ss dd MM YYYY");
                            String format = sd.format(System.currentTimeMillis());
                            Toast.makeText(Activity_UploadPhoto.this, "File Uploaded", Toast.LENGTH_LONG).show();
                            MyPhotoData myPhotoData = new MyPhotoData(taskSnapshot.getDownloadUrl().toString(), ed_Explain.getText().toString(), user.getEmail(), format);
                            MyGridViewData myGridViewData = new MyGridViewData(taskSnapshot.getDownloadUrl().toString());
                            //Save ImageInfo in to firebase
                            String UploadID = mDataRef.push().getKey();
                            mDataRef.child(UploadID).setValue(myPhotoData);
                            startActivity(new Intent(Activity_UploadPhoto.this, Activity_Complete_Photo_List.class));

                        }
                    }).addOnFailureListener(new OnFailureListener() {       //업로드 실패시 리스너
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            mProgressDialog.dismiss();
                            Toast.makeText(Activity_UploadPhoto.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @SuppressWarnings("VisibleForTests")
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = ((100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount());
                            mProgressDialog.setMessage((int) progress + "% Uploaded ");
                        }
                    });
                }else{
                    finish();
                    Toast.makeText(Activity_UploadPhoto.this, "Please Add your Photo", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_Album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select An Image"), PICK_IMAGE_REQUEST);

                mProgressDialog.dismiss();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                mProgressDialog.setMessage("Uploading");
                mProgressDialog.show();
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException Fe) {

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public String getImagExt(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

}
