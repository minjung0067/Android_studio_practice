package com.example.practiveeee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btn_move;
    private EditText et_test;
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {  //앱을 처음 실행할때 돌아가는 거
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et_test = findViewById(R.id.et_test);


    //누르면 subactivity쪽으로 이동하는 버튼 만들기
        btn_move = findViewById(R.id.btn_move);
        btn_move.setOnClickListener(new View.OnClickListener() {   //버튼 눌렀을 때
            @Override
            public void onClick(View v) {  //이 함수를 실행시키겠다
                str = et_test.getText().toString();
                Intent intent = new Intent(MainActivity.this , subactivity.class);
                //위 subactivity는 내가 버튼을 만든 곳 자바파일의 이름
                intent.putExtra("str", str);
                startActivity(intent); //실제적으로 액티비티 이동하는 구문

           }
        });




    }
}