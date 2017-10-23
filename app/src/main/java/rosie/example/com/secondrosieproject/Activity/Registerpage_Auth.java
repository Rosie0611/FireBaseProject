package rosie.example.com.secondrosieproject.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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

//회원가입을 두페이지로 나눠서 저장할때 데이터받아오는게 힘들면
//한페이지로 합쳐서 이메일,비밀번호는 Authentication으로 저장시키고, 회원정보는 database에 저장시켜보자

public class Registerpage_Auth extends AppCompatActivity {

    @BindView(R.id.ed_register_Email)
    EditText ed_register_Email;
    @BindView(R.id.ed_register_password)
    EditText ed_register_password;
    @BindView(R.id.bt_JoinNow)
    Button bt_joinNow;
    @BindView(R.id.bt_JoinCancel)
    Button bt_joinCancel;

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerpage__auth);

        ButterKnife.bind(this);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        bt_joinCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registerpage_Auth.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.bt_JoinNow)
    public void RegisterMember() {

        final String strEmail = ed_register_Email.getText().toString().trim();
        String strPW = ed_register_password.getText().toString().trim();

        if (TextUtils.isEmpty(strEmail)) {
            Toast.makeText(this, "Please enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(strPW)) {
            Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering User");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(strEmail, strPW)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Registerpage_Auth.this, "Registered Successfully ", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Registerpage_Auth.this, Registerpage_Additional.class);
                            intent.putExtra("Email",strEmail);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Registerpage_Auth.this, "Fail to Register, Please Try Again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        
    }
}
