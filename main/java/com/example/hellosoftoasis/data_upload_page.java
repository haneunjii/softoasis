package com.example.hellosoftoasis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class data_upload_page extends AppCompatActivity {

    EditText et_category,et_phonenumber,et_CEO,et_company,et_position,et_duty;
    Button btn_data_upload_complate;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_upload_page);

        et_category= findViewById(R.id.data_upload_category);
        et_phonenumber=findViewById(R.id.data_upload_phonenumber);
        et_CEO=findViewById(R.id.data_upload_Ceo);
        et_company =findViewById(R.id.data_upload_company);
        et_position=findViewById(R.id.data_upload_position);
        et_duty=findViewById(R.id.data_upload_duty);

        mFirebaseAuth = FirebaseAuth.getInstance(); //어스 사용자
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("softoasis"); //리얼타임 데이터

        FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
        firebase account = new firebase();

        btn_data_upload_complate = findViewById(R.id.data_upload_complate);
        btn_data_upload_complate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String category = et_category.getText().toString();
                String phonenumber = et_phonenumber.getText().toString();
                String CEO = et_CEO.getText().toString();
                String company = et_company.getText().toString();
                String position = et_position.getText().toString();
                String duty = et_duty.getText().toString();

                //화면에서 입력한 장보를 파이어 베이스 어타운트에 저장
                //이메일, 비밀번호와 uid는 로그인시 저장이 이미 되어있기때문에 불러오기만 하면 된다
                account.setCategory(category);
                account.setPhonenumber(phonenumber);
                account.setCeo(CEO);
                account.setCompany(company);
                account.setPosition(position);
                account.setDuty(duty);
                //받아온 정보형으로 파이어 베이스에 저장한다
                mDatabaseRef.child("유저정보").child(firebaseUser.getUid()).setValue(account);


                Intent intent1 = new Intent(data_upload_page.this,flatform_mainpage.class);
                startActivity(intent1);
            }
        });
    }
}