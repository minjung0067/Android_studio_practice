package com.example.practiveeee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
    EditText et_save;
    String shared = "file";
    private WebView webView;
    private String url = "https://m.naver.com/";
    private DrawerLayout drawerLayout;
    private View drawerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {  //앱을 처음 실행할때 돌아가는 거
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.drawer);

        Button btn_open = (Button)findViewById(R.id.btn_open);
        btn_open.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(drawerView);
            }

        });

        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;

            }
        });

        Button btn_close = (Button)findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }

        });



        et_save = (EditText)findViewById(R.id.et_save);  //#8
        SharedPreferences sharedPreferences = getSharedPreferences(shared, 0);
        String value = sharedPreferences.getString("mant","");
        et_save.setText(value);


        et_test = findViewById(R.id.et_test);
        list = (ListView)findViewById(R.id.list); //리스트 뷰의 id가져옴

        List<String> data = new ArrayList<>();

        //리스트랑 리스트 뷰 연결하는 어댑터
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, data);
        list.setAdapter(adapter);  //만들어놓은 list에다가 setting

        data.add("아배고파"); //원하는 데이터 값 넣어주기
        data.add("너무바빠");
        adapter.notifyDataSetChanged(); //현재 상태를 저장 완료

        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebChromeClient(new WebChromeClient()); //구글 크롬으로 열겠다
        webView.setWebViewClient(new WebViewClientClass());




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
        }
        );





    }

    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };

    //앱을 종료 시켰을 때, activity 벗어났을 때 실행할 수 있는 거 #8
    // 나중에 앱 키면 oncreate 실행이 될텐데, 얘에서 저장하면서 빠져나오고 새로 키면 oncreate에서 들어오고
    @Override
    protected void onDestroy() {
        super.onDestroy();

        //값 저장해놓고 실행될 때 #8
        SharedPreferences sharedPreferences = getSharedPreferences(shared, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String value = et_save.getText().toString(); //실제 써져있는 값을 받아오는 거
        editor.putString("mant",value);  //만트라는 이름으로 이 value값을 가져올 거야
        editor.commit();
        //임시저장이라 앱 삭제하면 사라짐 -> 데이터베이스 연동시켜서 서버 운영하면 영구저장 가능

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    };

    private class WebViewClientClass extends WebViewClient { //현재 페이지 url을 읽어올 수 있음
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}