package com.moasseo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONObject;

public class Main extends MainLogin {

    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    static String abc;
    //TextView tv_name;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        //navigationView.setItemIconTintList(null);

        //MainLogin에서 넘긴 NickName값
        Intent intent = getIntent();
        String User_id= intent.getStringExtra("User_id").toString();
        String User_pwd = intent.getStringExtra("User_pwd").toString();
        String User_NickName = intent.getStringExtra("User_NickName").toString();

        //메뉴바를 클릭하면...
        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        //하단 뒤로가기 버튼 누를 시 드로어 레이아웃 닫히게 구현할 것.


        //메뉴바 목록 클릭 시
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.information:
                        Intent intent = new Intent(Main.this, MainSetting.class);
                        startActivity(intent);
                        Toast.makeText(Main.this,"정보",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.event:
                        Toast.makeText(Main.this,"이벤트",Toast.LENGTH_LONG).show();;
                        break;
                    case R.id.map:
                        Toast.makeText(Main.this,"지도",Toast.LENGTH_LONG).show();;
                        break;
                    case R.id.setting:
                        Toast.makeText(Main.this,"설정",Toast.LENGTH_LONG).show();;
                        break;
                }
                return false;
            }
        });

        //xml 파일에서 넣어놨던 header 선언
        View header = navigationView.getHeaderView(0);

        //header에 있는 리소스 가져오기
        //로그인 시 아이디, 비밀번호에 닉네임 출력
        TextView text = (TextView) header.findViewById(R.id.tv_name);
        text.setText(User_NickName);



    }
}
