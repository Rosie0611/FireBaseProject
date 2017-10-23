package rosie.example.com.secondrosieproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rosie.example.com.secondrosieproject.MyData.MyInformData;
import rosie.example.com.secondrosieproject.R;

public class Activity_UpdatePage extends AppCompatActivity {

    @BindView(R.id.tv_email)
    TextView tv_email;
    @BindView(R.id.ed_userid)
    TextView ed_userid;
    @BindView(R.id.ed_name)
    EditText ed_name;
    @BindView(R.id.ed_phone_first)
    EditText ed_phone_first;
    @BindView(R.id.ed_phone_middle)
    EditText ed_phone_middle;
    @BindView(R.id.ed_phone_end)
    EditText ed_phone_end;
    @BindView(R.id.ed_address)
    EditText ed_address;
    @BindView(R.id.radio_female)
    RadioButton radio_female;
    @BindView(R.id.radio_male)
    RadioButton radio_male;
    @BindView(R.id.bt_personal_Inform_Update)
    Button bt_personal_Inform_Update;
    @BindView(R.id.bt_UpdateCancel)
    Button bt_UpdateCancel;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("/MEMBER/");

        databaseReference.child(auth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MyInformData myInformData = dataSnapshot.getValue(MyInformData.class);
//                String email = auth.getCurrentUser().getEmail();
                String userid = myInformData.getUserID();
                String name = myInformData.getName();
                String address = myInformData.getAddress();
                String phone01 = myInformData.getPhone1();
                String phone02 = myInformData.getPhone2();
                String phone03 = myInformData.getPhone3();

//                tv_email.setText(email);
                ed_userid.setHint(userid);
                ed_name.setHint(name);
                if (myInformData.getGender().equals("female")) {
                    radio_female.isChecked();
                } else if (myInformData.getGender().equals("male")) {
                    radio_male.isChecked();
                }
                ed_address.setHint(address);
                ed_phone_first.setHint(phone01);
                ed_phone_middle.setHint(phone02);
                ed_phone_end.setHint(phone03);

                Log.d("TAG", dataSnapshot.getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @OnClick(R.id.bt_personal_Inform_Update)
    public void UpdateInform() {
        Update();
        Intent intent = new Intent(Activity_UpdatePage.this, Activity_Account.class);
        startActivity(intent);

    }

    @OnClick(R.id.bt_UpdateCancel)
    public void updateCancel() {
        startActivity(new Intent(Activity_UpdatePage.this, Activity_Account.class));
    }


    //업데이트해야해
    public void Update() {

        String strUserID = ed_userid.getText().toString();
        String strName = ed_name.getText().toString();
        String strPhone_01 = ed_phone_first.getText().toString();
        String strPhone_02 = ed_phone_middle.getText().toString();
        String strPhone_03 = ed_phone_end.getText().toString();
        String strAddress = ed_address.getText().toString();
        String strGender = null;
        if (radio_female.isChecked()) {
            strGender = radio_female.getText().toString();
        } else if (radio_male.isChecked()) {
            strGender = radio_male.getText().toString();
        }

        DatabaseReference DataRef = firebaseDatabase.getReference().child("/MEMBER/");
        MyInformData myInformData = new MyInformData(strUserID, strName, strGender, strPhone_01, strPhone_02, strPhone_03, strAddress);
        DataRef.child(auth.getCurrentUser().getUid()).setValue(myInformData);

    }
}