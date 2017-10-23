package rosie.example.com.secondrosieproject.Adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import rosie.example.com.secondrosieproject.Activity.Activity_WritePost;
import rosie.example.com.secondrosieproject.MyData.MyRegularPostData;
import rosie.example.com.secondrosieproject.R;

/**
 * Created by skyki on 2017-08-17.
 */

public class RegularPost_Adapter extends ArrayAdapter<MyRegularPostData> {

    private Activity_WritePost context;
    private int ResId;
    private List<MyRegularPostData> list;

    private FirebaseUser user;

    DatabaseReference  dataRef = FirebaseDatabase.getInstance().getReference().child("/RegularPost/");

    public RegularPost_Adapter( Activity_WritePost context,  int resource,  List<MyRegularPostData> objects) {
        super(context, resource, objects);
        this.context = context;
        this.ResId = resource;
        list = objects;
    }


    @Override
    public View getView(final int position,  final View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View v = inflater.inflate(ResId, null);
        TextView tv_date = (TextView) v.findViewById(R.id.tv_posted_date);
        TextView tv_userid = (TextView) v.findViewById(R.id.tv_posted_userid);
        TextView tv_post = (TextView) v.findViewById(R.id.tv_posted_post);
        ImageButton ib_delete = (ImageButton)v.findViewById(R.id.ib_delete);

        ib_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                dataRef.removeValue();
//                {
//                    @Override
//                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
//                        databaseReference.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                Toast.makeText(Activity_WritePost.ACTIVITY_SERVICE, "Post Deleted", Toast.LENGTH_SHORT).show();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(Activity_WritePost.this, "Delete Processing is FAILED", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                });
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();

//        SimpleDateFormat sd = new SimpleDateFormat("a hh:mm:ss dd MM YYYY");
//        String time = sd.format(System.currentTimeMillis());

        tv_date.setText(list.get(position).getTime());
        tv_userid.setText(list.get(position).getUser());
        tv_post.setText(list.get(position).getPostContents());

        return v;
    }
}