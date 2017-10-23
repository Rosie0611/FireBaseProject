package rosie.example.com.secondrosieproject.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import rosie.example.com.secondrosieproject.Adapter.FireBaseAdapter;
import rosie.example.com.secondrosieproject.MyData.MyPhotoData;
import rosie.example.com.secondrosieproject.R;

public class Activity_Complete_Photo_List extends AppCompatActivity {

    private DatabaseReference firebaseDatabase;
    private List<MyPhotoData> list;
    private FireBaseAdapter adapter;
    ProgressDialog progressDialog;
    ListView listView;
    TextView tv_userid;
    ImageButton ib_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageview_list);

        listView = (ListView) findViewById(R.id.listview);
        tv_userid = (TextView) findViewById(R.id.tv_userid);
        ib_back =(ImageButton)findViewById(R.id.ib_back);

        list = new ArrayList<>();

        progressDialog = new ProgressDialog(this);

        firebaseDatabase = FirebaseDatabase.getInstance().getReference("/Uploaded_Photo/").child(Activity_UploadPhoto.DATABASE_PATH);
        firebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MyPhotoData myPhotoData = snapshot.getValue(MyPhotoData.class);
                    list.add(myPhotoData);
                }
                adapter = new FireBaseAdapter(Activity_Complete_Photo_List.this, R.layout.getimage_layout, list);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_Complete_Photo_List.this,Activity_Board_Main.class));
            }
        });

    }
}
