package com.moasseo;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainLogin extends MainIntroLogin {

    TextView JoinLogin, FindId, FindPwd;

   // CheckBox LoginCheck;
    TextView LoginText;  //자동 로그인
    ImageButton LoginCheck;  //하단 로그인 버튼
    ImageView Eyes;  //눈 이미지 뷰
    EditText password;  //비밀번호 EditText

    static boolean Loginflag = true;  //자동로그인 체크, 미체크 할 때 사용
    static boolean eyes = true;  //비밀번호 보이게, 안보이게 할 때 사용


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

      /*  Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainLogin.this, MainJoinLogin1.class); //화면 전환
                startActivity(intent);

            }
        }, 4000); //딜레이 타임 조절*/

        JoinLogin = (TextView)findViewById(R.id.textView11);    //회원가입 Text
        FindId = (TextView)findViewById(R.id.textView4);    //아이디 찾기 Text
        FindPwd = (TextView)findViewById(R.id.textView6);   //비밀번호 찾기 Text
        LoginCheck = (ImageButton)findViewById(R.id.imageButton4); //자동로그인 체크 박스
        LoginText = (TextView)findViewById(R.id.textView3);  //자동로그인 Text
        Eyes = (ImageView)findViewById(R.id.imageView4);  //비밀번호 보이게/안보이게 ㅇㄴㅁImageView
        password = (EditText)findViewById(R.id.editTextTextPersonName3);  //비밀번호 입력 EditText


        JoinLogin.setOnClickListener(new View.OnClickListener() {  //하단 회원가입 Text 클릭
            public void onClick(View v) {
                Intent intent = new Intent(MainLogin.this, MainJoinLogin1.class);
                startActivity(intent);
            }
        });

        FindId.setOnClickListener(new View.OnClickListener() {  //아이디 찾기 Text 클릭
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainLogin.this, MainFindID.class);
                startActivity(intent);
            }
        });

        FindPwd.setOnClickListener(new View.OnClickListener() {  //비밀번호 찾기 Text 클릭
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainLogin.this, MainFindPwd.class);
                startActivity(intent);
            }
        });

        LoginCheck.setOnClickListener(new View.OnClickListener() {  //자동 로그인 체크 버튼 클릭
            @Override
            public void onClick(View v) {
                if(Loginflag == true) {
                    LoginCheck.setImageResource(R.drawable.check_colro_circle);
                    Loginflag = false;
                }
                else {
                    LoginCheck.setImageResource(R.drawable.check_circle);
                    Loginflag = true;
                }
            }
        });

        LoginText.setOnClickListener(new View.OnClickListener() {  //자동 로그인 Text 클릭
            @Override
            public void onClick(View v) {
                if(Loginflag == true) {
                    LoginCheck.setImageResource(R.drawable.check_colro_circle);
                    Loginflag = false;
                }
                else {
                    LoginCheck.setImageResource(R.drawable.check_circle);
                    Loginflag = true;
                }
            }
        });

        Eyes.setOnClickListener(new View.OnClickListener() {  //비밀번호 EditText 눈 표시 클릭
            @Override
            public void onClick(View v) {
                if(eyes == true) {
                    Eyes.setImageResource(R.drawable.eyes_on);  //클릭 시 비밀번호 보이게 이미지 변경
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);  //비밀번호 보이게
                    password.setLetterSpacing((float) -0.04);
                    eyes = false;

                }
                else {
                    Eyes.setImageResource(R.drawable.eyes_off);  //켜진 상태에서 클릭시 비밀번호 안보이게 이미지 변경
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);  //비밀번호 안보이게
                    password.setLetterSpacing((float)-0.04);
                    eyes = true;
                }
            }
        });
    }
}
