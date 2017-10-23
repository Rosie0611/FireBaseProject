package rosie.example.com.secondrosieproject.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rosie.example.com.secondrosieproject.R;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ed_loginID)
    EditText ed_loginID;
    @BindView(R.id.ed_loginPW)
    EditText ed_loginPW;
    @BindView(R.id.bt_login)
    Button bt_login;
    @BindView(R.id.bt_JoinUs)
    Button bt_joinUs;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        ButterKnife.bind(this);


//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    Toast.makeText(MainActivity.this, "로그인했당", Toast.LENGTH_SHORT).show();
//                    Log.d("TAG", user.getUid());
//                    Intent intent2 = new Intent(MainActivity.this, Activity_Account.class);
//                    Toast.makeText(MainActivity.this, "You Already been Logged", Toast.LENGTH_SHORT).show();
//                    startActivity(intent2);
//                }
//            }
//        };
    }

    @OnClick(R.id.bt_login)
    public void Login(View v) {
        Log.d("TAG", "onClick 호출");
//                onStartSignIn();
        final String strId = ed_loginID.getText().toString().trim();
        String strPw = ed_loginPW.getText().toString().trim();

        if (TextUtils.isEmpty(strId) || TextUtils.isEmpty(strPw)) {
            Toast.makeText(MainActivity.this, "Check Empty Space For ID,Password", Toast.LENGTH_SHORT).show();
        }

        progressDialog.setMessage("Login Processing...");

        //로그인
        mAuth.signInWithEmailAndPassword(strId, strPw).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "You Logged in just now as " + strId, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Activity_Account.class);
                    intent.putExtra("EMAIL",ed_loginID.getText().toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Need to Check for Login", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //회원가입
    @OnClick(R.id.bt_JoinUs)
    public void onBtnJoinUs() {
        Intent intent = new Intent(MainActivity.this, Registerpage_Auth.class);
        startActivity(intent);
    }
}