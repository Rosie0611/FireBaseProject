package rosie.example.com.secondrosieproject.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rosie.example.com.secondrosieproject.MyData.MyInformData;
import rosie.example.com.secondrosieproject.R;

public class Registerpage_Additional extends AppCompatActivity {

    @BindView(R.id.tv_email)
    TextView tv_email;
    @BindView(R.id.ed_userid)
    EditText ed_userid;
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
    @BindView(R.id.bt_register)
    Button bt_register;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    MyInformData myInformData;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register_additional_inform);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        auth = FirebaseAuth.getInstance();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        ButterKnife.bind(this);

        myInformData = new MyInformData();
        Intent intent = getIntent();
        String strEmail = intent.getStringExtra("Email");
        tv_email.setText(strEmail);

    }

    @OnClick(R.id.bt_register)
    public void SaveInform() {
        save();

        Intent intent = new Intent(Registerpage_Additional.this, MainActivity.class);
        startActivity(intent);

    }

    public void save() {

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
        MyInformData myInformData = new MyInformData(strUserID, strName, strGender, strAddress, strPhone_01, strPhone_02, strPhone_03);
        DataRef.child(auth.getCurrentUser().getUid()).setValue(myInformData);

    }
}