package com.example.hellosoftoasis;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registerpage extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseRef;
    private EditText etID, etPwd;
    private Button mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerpage);

        mFirebaseAuth = FirebaseAuth.getInstance(); //어스 사용자
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("softoasis"); //리얼타임 데이터



        etID = findViewById(R.id.et_ID_join);
        etPwd = findViewById(R.id.et_password_join);
        mBtnRegister = findViewById(R.id.btn_joincomplate);

        Button btn_joinbomplate = findViewById(R.id.btn_joincomplate);

        btn_joinbomplate.setOnClickListener(new View.OnClickListener() {//입력완료 버튼센서 대기 상태 만들어 주기
            @Override
            public void onClick(View view) {   //입력완료 버튼이 눌려 지면
                String stremail = etID.getText().toString();
                String strPwd = etPwd.getText().toString();
                //입력된 데이터 불러 오기

                mFirebaseAuth.createUserWithEmailAndPassword(stremail, strPwd).addOnCompleteListener(registerpage.this, new OnCompleteListener<AuthResult>() {
                    //완료 되었는지 확인하는 롹장자
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) //완료가 되었다면
                    {
                        if(task.isSuccessful()){   //만약 완료가 성공이라면
                            //어스와 데이터망 연결
                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                            firebase account = new firebase();
                            //자료형 만들기
                            account.setIdToken(firebaseUser.getUid());
                            account.setEmailId(firebaseUser.getEmail());
                            account.setPassword(strPwd);


                            mDatabaseRef.child("유저정보").child(firebaseUser.getUid()).setValue(account);

                            Toast.makeText(registerpage.this, "회원가입에 성공하셨습니다", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(registerpage.this,loginpage.class);
                            startActivity(intent1);
                        }
                        else{
                            Toast.makeText(registerpage.this, "회원가입에 실패 하셨습니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}