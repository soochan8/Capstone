package com.moasseo;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class MainMap extends MainActivity{

    ConstraintLayout constraintLayout11;
    ConstraintLayout constraintLayout9;  //젤 위
    ImageView imageView19;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        constraintLayout11 = (ConstraintLayout) findViewById(R.id.constraintLayout11);  //가려진 레이아웃
        constraintLayout9 = (ConstraintLayout) findViewById(R.id.constraintLayout9); //젤 위
        imageView19 = (ImageView) findViewById(R.id.imageView19);  //지도

        constraintLayout11.bringToFront();  //레이아웃 앞으로 보내기

    }
}
