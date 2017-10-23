package rosie.example.com.secondrosieproject.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

public class Activity_Account extends AppCompatActivity {

    @BindView(R.id.tv_welcomeText)
    TextView tv_welcomeText;
    @BindView(R.id.tv_WelcomPage_email)
    TextView tv_WelcomPage_email;
    @BindView(R.id.tv_WelcomPage_name)
    TextView tv_WelcomPage_name;
    @BindView(R.id.tv_WelcomPage_UserID)
    TextView tv_WelcomPage_UserID;
    @BindView(R.id.bt_logout)
    Button bt_logout;
    @BindView(R.id.bt_Board)
    Button bt_Board;
    @BindView(R.id.bt_Update)
    Button bt_Update;

    DatabaseReference DataRef;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        final String strEmail = intent.getStringExtra("EMAIL");
        tv_WelcomPage_email.setText(strEmail);
        mAuth = FirebaseAuth.getInstance();
        DataRef = FirebaseDatabase.getInstance().getReference().child("/MEMBER/").child(mAuth.getCurrentUser().getUid());
      DataRef.addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
              MyInformData myInformData =dataSnapshot.getValue(MyInformData.class);
              Log.d("TAG",myInformData.toString());
              String userid = myInformData.getUserID();
              String name = myInformData.getName();
              tv_WelcomPage_email.setText(mAuth.getCurrentUser().getEmail());
              tv_WelcomPage_UserID.setText(userid);
              tv_WelcomPage_name.setText(name);
          }

          @Override
          public void onCancelled(DatabaseError databaseError) {

          }
      });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d("TAG","LOG02");
                    Toast.makeText(Activity_Account.this, "로그인했당", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", user.getUid());
                } else {
                    Intent intent2 = new Intent(Activity_Account.this, MainActivity.class);
                    Toast.makeText(Activity_Account.this, "Please Login Again", Toast.LENGTH_SHORT).show();
                    startActivity(intent2);
                }
            }
        };
    }
    @OnClick(R.id.bt_Update)
    public void personel_update(){
        startActivity(new Intent(Activity_Account.this,Activity_UpdatePage.class));
    }
    @OnClick(R.id.bt_Board)
    public void Move_Main_Board(){
        startActivity(new Intent(Activity_Account.this,Activity_Board_Main.class));
    }

    @OnClick(R.id.bt_logout)
    public void logout() {
        mAuth.signOut();
        mAuth.removeAuthStateListener(mAuthListener);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(Activity_Account.this,"Logout",Toast.LENGTH_LONG).show();
    }
}