package com.example.practiveeee;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class subactivity extends AppCompatActivity {

    private TextView tv_sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subactivity);

        tv_sub = findViewById(R.id.tv_sub);

        Intent intent = getIntent();//날아오는 데이터 값 받겠다
        String str = intent.getStringExtra("str");



        tv_sub.setText(str);
    }
}