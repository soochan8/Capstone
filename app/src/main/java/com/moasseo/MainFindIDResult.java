//새 비밀번호를 DB에 넣음

package com.moasseo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainFindIDResult extends MainFindID {

    TextView textView22;  //찾은 아이디 결과를 보여줄 Text
    TextView FindPwd;  //비밀번호 찾기 Text
    Button Login2;  //하단 로그인 버튼

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findidresult);

        textView22 = (TextView)findViewById(R.id.textView22);  //찾은 아이디 결과를 보여줄 Text
        FindPwd = (TextView)findViewById(R.id.textView21);  //비밀번호 찾기 Text
        Login2 = (Button)findViewById(R.id.button12);  //하단 로그인 버튼

        Intent intent = getIntent();
        String User_id = intent.getStringExtra("User_id");

        textView22.setText(User_id);  //찾은 아이디를 TextView로 출력해서 보여줌

        FindPwd.setOnClickListener(new View.OnClickListener() { //비밀번호 찾기 Text 클릭시
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainFindIDResult.this, MainFindPwd.class);  //비밀번호 창으로 이동
                startActivity(intent);
            }
        });

        Login2.setOnClickListener(new View.OnClickListener() {  //하단 로그인 하기 버튼 클릭시
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainFindIDResult.this, MainLogin.class);  //비밀번호 창으로 이동
                startActivity(intent);
            }
        });

    }
}
