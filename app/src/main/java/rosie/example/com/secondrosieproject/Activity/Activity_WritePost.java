package rosie.example.com.secondrosieproject.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rosie.example.com.secondrosieproject.MyData.MyRegularPostData;
import rosie.example.com.secondrosieproject.R;
import rosie.example.com.secondrosieproject.Adapter.RegularPost_Adapter;

public class Activity_WritePost extends AppCompatActivity {

    @BindView(R.id.listview_post)
    ListView listview_posted;
    @BindView(R.id.tv_regular_userid)
    TextView tv_regular_userid;
    @BindView(R.id.ed_post)
    EditText ed_post;
    @BindView(R.id.bt_postNow)
    Button bt_postnow;
    @BindView(R.id.bt_write_cancel)
    Button bt_write_cancel;
    @BindView(R.id.bt_reset)
    Button bt_reset;
    @BindView(R.id.ib_delete)
    ImageButton ib_delete;

    private FirebaseUser user;
    private DatabaseReference dataRef;

    RegularPost_Adapter adapter;
    List<MyRegularPostData> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_post);
        ButterKnife.bind(this);
        listview_posted = (ListView) findViewById(R.id.listview_post);
        dataRef = FirebaseDatabase.getInstance().getReference().child("/RegularPost/");

        arrayList = new ArrayList<>();
        user = FirebaseAuth.getInstance().getCurrentUser();
        tv_regular_userid.setText(user.getEmail());

        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        MyRegularPostData myRegularPostData = snapshot.getValue(MyRegularPostData.class);
                        arrayList.add(myRegularPostData);
                    }

                    adapter = new RegularPost_Adapter(Activity_WritePost.this, R.layout.inflated_items_regular_posts, arrayList);
                    listview_posted.setAdapter(adapter);
                } else {
                    adapter = new RegularPost_Adapter(Activity_WritePost.this, R.layout.inflated_items_regular_posts, arrayList);
                    listview_posted.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @OnClick(R.id.bt_postNow)
    public void postnow() {
        String strPost = ed_post.getText().toString();
        SimpleDateFormat sd = new SimpleDateFormat("a hh:mm:ss dd MM YYYY");
        String format = sd.format(System.currentTimeMillis());
        MyRegularPostData myRegularPostData = new MyRegularPostData(user.getEmail(),strPost, format);
        ed_post.setText("");

        dataRef.child(format).setValue(myRegularPostData);

    }

    @OnClick(R.id.bt_write_cancel)
    public void write_Cancel() {

    }

    @OnClick(R.id.bt_reset)
    public void bt_post_reset() {
        ed_post.setText("");

    }


}
