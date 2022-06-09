package com.moasseo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

public class MainMypagePoint1 extends MainActivity {

    TextView barcode, usemoney, barcode1, usemoney1; //바코드, 사용금액
    ConstraintLayout barcodeLayout, usemoneyLayout;  //바코드 화면, 사용내역 화면


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_point_1);

        barcode = (TextView) findViewById(R.id.textView80);  //바코드 Text
        barcode1 = (TextView) findViewById(R.id.textView86);  //바코드 Text

        usemoney = (TextView) findViewById(R.id.textView81);  //사용내역 Text
        usemoney1 = (TextView) findViewById(R.id.textView87);  //사용내역 Text

        barcodeLayout = (ConstraintLayout) findViewById(R.id.barcodeLayout);  //바코드 화면
        usemoneyLayout = (ConstraintLayout) findViewById(R.id.usemoneyLayout);  //사용내역 화면

        barcode.setOnClickListener(new View.OnClickListener() {  //바코드 클릭 시
            @Override
            public void onClick(View v) {
                barcodeLayout.setVisibility(View.VISIBLE);  //바코드 화면 나오기
                usemoneyLayout.setVisibility(View.GONE);  //사용 내역 가리기
                //Toast.makeText(getApplicationContext(), "바코드 클릭", Toast.LENGTH_LONG).show();
            }
        });

        barcode1.setOnClickListener(new View.OnClickListener() {  //바코드 클릭 시
            @Override
            public void onClick(View v) {
                barcodeLayout.setVisibility(View.VISIBLE);  //바코드 화면 나오기
                usemoneyLayout.setVisibility(View.GONE);  //사용 내역 가리기
                //Toast.makeText(getApplicationContext(), "바코드 클릭", Toast.LENGTH_LONG).show();
            }
        });


        usemoney.setOnClickListener(new View.OnClickListener() {  //사용내역 클릭 시
            @Override
            public void onClick(View v) {
                barcodeLayout.setVisibility(View.GONE);  //바코드 화면 가리기
                usemoneyLayout.setVisibility(View.VISIBLE);  //사용 내역 화면 나오기
            }
        });

        usemoney1.setOnClickListener(new View.OnClickListener() {  //사용내역 클릭 시
            @Override
            public void onClick(View v) {
                barcodeLayout.setVisibility(View.GONE);  //바코드 화면 가리기
                usemoneyLayout.setVisibility(View.VISIBLE);  //사용 내역 화면 나오기
            }
        });
    }
}
