package com.moasseo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainJoinLogin3 extends MainActivity {
    protected void onCreate(Bundle savedInstanceState) {

        Button button7;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.joinlogin3);

        button7 = (Button)findViewById(R.id.button7);  //로그인하기 버튼 클릭

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainJoinLogin3.this, MainLogin.class);  //로그인 화면으로 이동
                startActivity(intent);
            }
        });

    }
}
