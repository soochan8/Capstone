package com.moasseo;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class MainMypage extends MainActivity{

    TextView grade_info;  //등급안내 Text
    ConstraintLayout grade_pop;  //팝업 레이아웃
    ConstraintLayout mypage_point;  //시장 별 포인트
    View close;  //하단 닫기 뷰

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        grade_info = (TextView) findViewById(R.id.textView36);  //등급안내 Text
        grade_pop = (ConstraintLayout) findViewById(R.id.constraintlayout10);  //팝업 레이아웃
        mypage_point = (ConstraintLayout) findViewById(R.id.constraintLayout5);  //내 정보 - 시장 별 포인트
        close = (View) findViewById(R.id.view3);  //하단 닫기 뷰

        grade_info.setPaintFlags(grade_info.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);  //등급안내 Text 밑줄

        grade_info.setOnClickListener(new View.OnClickListener() {  //등급안내 Text 클릭 시
            @Override
            public void onClick(View v) {
                grade_pop.setVisibility(View.VISIBLE);  //팝업 레이아웃(모아써 등급 표시)
            }
        });

        close.setOnClickListener(new View.OnClickListener() {  //닫기 뷰 클릭 시
            @Override
            public void onClick(View v) {
                grade_pop.setVisibility(View.GONE);  //팝업 레이아웃(모아써 등급 표시) 꺼짐
            }
        });

        mypage_point.setOnClickListener(new View.OnClickListener() {  //시장 별 포인트 레이아웃을 클릭하면
            @Override
            public void onClick(View v) {
                //시장별 포인트로 넘어가기
                Intent intent = new Intent(MainMypage.this, MainMypagePoint.class);  //시장 별 포인트 화면으로 이동
                startActivity(intent);
            }
        });
    }
}
