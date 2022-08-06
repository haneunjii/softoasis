package com.example.hellosoftoasis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class flatform_mainpage extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flatform_mainpage);

        Button btn_logout =findViewById(R.id.flatform_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseAuth.signOut(); //로그아웃
            }
        });

        Button btn_dtaupload = findViewById(R.id.data_upload);
        btn_dtaupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //업로드 페이지 이동
                Intent intent1 = new Intent(flatform_mainpage.this,data_upload_page.class);
                startActivity(intent1);
            }
        });

        Button userdelate =findViewById(R.id.userdelate);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseAuth.signOut(); //로그아웃
                mFirebaseAuth.getCurrentUser().delete(); //사용자 목록에서 지우기
            }
        });
    }
}