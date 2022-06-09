package com.moasseo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

public class MainMypagePoint extends MainActivity {

    protected void onCreate(Bundle savedInstanceState) {

        ConstraintLayout marketLayout;  //시장이 들어있는 레이아웃
        ConstraintLayout mangwon_market;  //망원시장
        ConstraintLayout tongin_market;  //통인시장

        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_point);

        marketLayout = (ConstraintLayout) findViewById(R.id.marketLayout);  //시장이 들어있는 레이아웃
        mangwon_market = (ConstraintLayout) findViewById(R.id.constraintLayout14);  //망원 시장
        tongin_market = (ConstraintLayout) findViewById(R.id.constraintLayout15);  //통인 시장

        mangwon_market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //망원시장 바코드 및 사용 내역으로 이동
                Intent intent = new Intent(MainMypagePoint.this, MainMypagePoint1.class);
                startActivity(intent);
            }
        });




    }
}
