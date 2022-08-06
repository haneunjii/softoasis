package com.example.hellosoftoasis;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class loginpage extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseRef;
    private EditText etID, etPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        Button btn_login =findViewById(R.id.btn_login);
        Button btn_join =findViewById(R.id.btn_joinregister);

        mFirebaseAuth = FirebaseAuth.getInstance();

        etID = findViewById(R.id.et_ID);
        etPwd = findViewById(R.id.et_password);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String stremail = etID.getText().toString();
                String strPwd = etPwd.getText().toString();

                mFirebaseAuth.signInWithEmailAndPassword(stremail,strPwd).addOnCompleteListener(loginpage.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                            firebase account = new firebase();
                            //로그인 성공
                            account.setEmailId(stremail);
                            account.setPassword(strPwd);
                            account.setIdToken(firebaseUser.getUid()); //로그인시 유저 고유 번호가 디바이스에 저장이 된다
                            Toast.makeText(loginpage.this, "로그인 성공 하셨습니다", Toast.LENGTH_SHORT).show();
                            Intent intent2 = new Intent(loginpage.this,flatform_mainpage.class);
                            startActivity(intent2);
                            finish(); //현재 액티비티는 지운다
                        }
                        else{
                            Toast.makeText(loginpage.this, "로그인에 실패 하셨습니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(loginpage.this,registerpage.class);
                startActivity(intent1);
                /*
                회원가입 버튼
                 */
            }
        });
    }
}