package rosie.example.com.secondrosieproject.Activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rosie.example.com.secondrosieproject.Adapter.FireBaseAdapter;
import rosie.example.com.secondrosieproject.Adapter.GridviewAdapter;
import rosie.example.com.secondrosieproject.MyData.MyGridViewData;
import rosie.example.com.secondrosieproject.MyData.MyPhotoData;
import rosie.example.com.secondrosieproject.R;

public class Activity_Complete_MyPhoto_GridView extends AppCompatActivity {

    @BindView(R.id.iv_gridAgain)
    ImageView iv_gridAgain;
    @BindView(R.id.iv_listView)
    ImageView iv_listView;
    @BindView(R.id.iv_direction)
    ImageView iv_direction;

    GridviewAdapter adapter;
    GridView gridView;

    private DatabaseReference firebaseDatabase;
//    private List<MyPhotoData> arrData;
    private ArrayList<MyGridViewData> arrData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);
        gridView = (GridView) findViewById(R.id.gridview);

        ButterKnife.bind(this);

        firebaseDatabase = FirebaseDatabase.getInstance().getReference("/Uploaded_Photo/").child(Activity_UploadPhoto.DATABASE_PATH);
        firebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MyPhotoData myPhotoData = snapshot.getValue(MyPhotoData.class);
                    arrData.add(new MyGridViewData(myPhotoData.getiPhoto()));
                }
                adapter = new GridviewAdapter(Activity_Complete_MyPhoto_GridView.this, R.layout.gridview_items, arrData);
                gridView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @OnClick(R.id.iv_gridAgain)
    public void iv_gridview(View view) {

    }
}
