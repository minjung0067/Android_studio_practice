package com.example.practiveeee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn_move;
    private EditText et_test;
    private String str;
    ImageView test;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {  //앱을 처음 실행할때 돌아가는 거
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et_test = findViewById(R.id.et_test);
        list = (ListView)findViewById(R.id.list); //리스트 뷰의 id가져옴

        List<String> data = new ArrayList<>();

        //리스트랑 리스트 뷰 연결하는 어댑터
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, data);
        list.setAdapter(adapter);  //만들어놓은 list에다가 setting

        data.add("아배고파"); //원하는 데이터 값 넣어주기
        data.add("너무바빠");
        adapter.notifyDataSetChanged(); //현재 상태를 저장 완료


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

        test = (ImageView)findViewById(R.id.test);
        test.setOnClickListener(new View.OnClickListener() { //이미지클릭시
            @Override
            public void onClick(View v) { //토스트 메세지 띄움
                Toast.makeText(getApplicationContext(), "하핫띠",Toast.LENGTH_SHORT).show();

            }
        });





    }
}