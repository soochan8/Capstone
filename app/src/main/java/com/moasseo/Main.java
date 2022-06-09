package com.moasseo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONObject;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator3;

public class Main extends MainLogin {

    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    static String abc;

    ImageView close;  //side_header 닫기 버튼 이미지
    ImageView imageAlarm;  //상단 우측 알람 이미지

    //--------------------
    //ViewPager 변수
    private ViewPager2 mPager;
    private FragmentStateAdapter pagerAdapter;
    private int num_page = 4;
    private CircleIndicator3 mIndicator;

    //Recycler 변수
    private RecyclerView mRecyclerView;
    private ArrayList<RecyclerViewItem> mList;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        //navigationView.setItemIconTintList(null);

        close = (ImageView) findViewById(R.id.icon_close);  //side_header 닫기 버튼 이미지
        imageAlarm = (ImageView) findViewById(R.id.imageAlarm); //상단 우측 알람 이미지


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
                        Intent intent1 = new Intent(Main.this, MainMypage.class);
                        startActivity(intent1);
                        //Toast.makeText(Main.this,"정보",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.event:
                        Intent intent2 = new Intent(Main.this, MainEvent.class);
                        startActivity(intent2);
                        //Toast.makeText(Main.this,"이벤트",Toast.LENGTH_LONG).show();;
                        break;
                    case R.id.map:
                        Intent intent3 = new Intent(Main.this, MainMap.class);
                        startActivity(intent3);
                        //Toast.makeText(Main.this,"지도",Toast.LENGTH_LONG).show();;
                        break;
                    case R.id.setting:
                        Intent intent4 = new Intent(Main.this, MainSetting.class);
                        startActivity(intent4);
                        //Toast.makeText(Main.this,"설정",Toast.LENGTH_LONG).show();;
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

        imageAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, MainAlarm.class); //메인 > 알람 화면으로 이동
                intent.putExtra("User_NickName", User_NickName);  //닉네임을 같이 넘김
                startActivity(intent);
            }
        });


        //-------------------------------------------------------
        //Recycler List item 생성
        mList = new ArrayList<RecyclerViewItem>();
        mList.add(new RecyclerViewItem(R.drawable.mangwon));
        mList.add(new RecyclerViewItem(R.drawable.tongin));
        mList.add(new RecyclerViewItem(R.drawable.dongmun));
        mList.add(new RecyclerViewItem(R.drawable.booksin));

        //Recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //Recycler Adapter 추가
        mRecyclerViewAdapter = new RecyclerViewAdapter(mList);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        //Recycler Layout manager 추가
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        //ViewPager2
        mPager = findViewById(R.id.viewpager1);

        pagerAdapter = new MyAdapter(this, num_page);
        mPager.setAdapter(pagerAdapter);
        //Indicator
        mIndicator = findViewById(R.id.indicator1);
        mIndicator.setViewPager(mPager);
        mIndicator.createIndicators(num_page,0);
        //ViewPager Setting
        mPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);

        mPager.setCurrentItem(1000); //시작 지점
        mPager.setOffscreenPageLimit(4); //최대 이미지 수

        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0) {
                    mPager.setCurrentItem(position);
                }
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mIndicator.animatePageSelected(position%num_page);
            }
        });


        mRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main.this, "눌렀음", Toast.LENGTH_LONG).show();
            }
        });





    }
}
