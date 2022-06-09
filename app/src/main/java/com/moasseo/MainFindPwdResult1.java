package com.moasseo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainFindPwdResult1 extends MainFindPwdResult{
    protected void onCreate(Bundle savedInstanceState) {

        Button Login3;  //하단 로그인 버튼

        super.onCreate(savedInstanceState);
        setContentView(R.layout.findpasswordresult1);

        Login3 = (Button)findViewById(R.id.button2);

        Login3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainFindPwdResult1.this, MainLogin.class);
                startActivity(intent);
            }
        });
    }
}
