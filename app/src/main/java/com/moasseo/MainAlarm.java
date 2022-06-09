package com.moasseo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainAlarm extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        TextView textView102;  //닉네임 받아올 텍스트
        ImageView imageView24;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_alarm);

        textView102 = (TextView) findViewById(R.id.textView102);  //닉네임 받아올 텍스트
        imageView24 = (ImageView) findViewById(R.id.imageView24);

        Intent intent = getIntent();
        String User_NickName = intent.getStringExtra("User_NickName").toString();

        textView102.setText(User_NickName + "님, 1천원 쿠폰 받으세요.");

        /*imageView24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAlarm.this, Main.class);
                startActivity(intent);
            }
        });*/

    }
}