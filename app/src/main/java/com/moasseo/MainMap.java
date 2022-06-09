package com.moasseo;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class MainMap extends MainActivity{

    ConstraintLayout constraintLayout11;
    ConstraintLayout constraintLayout10;  //지역 선택
    ConstraintLayout pullLayout, pullLayout1;  //전체 화면
    ConstraintLayout constraintLayout9;  //젤 위
    ImageView imageView19;
    ImageView BackButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        constraintLayout11 = (ConstraintLayout) findViewById(R.id.constraintLayout11);  //가려진 레이아웃
        constraintLayout10 = (ConstraintLayout) findViewById(R.id.constraintLayout10);  //지역 선택
        pullLayout = (ConstraintLayout) findViewById(R.id.pullLayout);  //전체 화면
        pullLayout1 = (ConstraintLayout) findViewById(R.id.pullLayout1);  //전체 화면
        constraintLayout9 = (ConstraintLayout) findViewById(R.id.constraintLayout9); //젤 위
        imageView19 = (ImageView) findViewById(R.id.imageView19);  //지도
        BackButton = (ImageView) findViewById(R.id.BackButton);  //뒤로가기 버튼

        constraintLayout11.bringToFront();  //레이아웃 앞으로 보내기

        /*constraintLayout10.setOnClickListener(new View.OnClickListener() {  //지역 선택
            @Override
            public void onClick(View v) {
                pullLayout1.setVisibility(View.GONE);
                BackButton.setBackgroundColor(Color.parseColor("#4c000000"));
                pullLayout.setBackgroundColor(Color.parseColor("#4c000000"));
            }
        });*/

    }
}
