package com.moasseo;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainLogin extends MainIntroLogin  {

    TextView JoinLogin, FindId, FindPwd;
    Button button;

    //String User_id, User_pwd;  //아이디와 비밀번호 입력값 저장할 변수

    // CheckBox LoginCheck;
    TextView LoginText;  //자동 로그인
    ImageButton LoginCheck;  //하단 로그인 버튼
    ImageView Eyes;  //눈 이미지 뷰
    EditText Editid, password;  //비밀번호 EditText

    static boolean Loginflag = true;  //자동로그인 체크, 미체크 할 때 사용
    static boolean eyes = true;  //비밀번호 보이게, 안보이게 할 때 사용


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        JoinLogin = (TextView) findViewById(R.id.textView11);    //회원가입 Text
        FindId = (TextView) findViewById(R.id.textView4);    //아이디 찾기 Text
        FindPwd = (TextView) findViewById(R.id.textView6);   //비밀번호 찾기 Text
        LoginCheck = (ImageButton) findViewById(R.id.imageButton4); //자동로그인 체크 박스
        LoginText = (TextView) findViewById(R.id.textView3);  //자동로그인 Text
        Eyes = (ImageView) findViewById(R.id.imageView4);  //비밀번호 보이게/안보이게 ImageView
        Editid = (EditText) findViewById(R.id.editTextTextPersonName);  //아이디 입력 EditText
        password = (EditText) findViewById(R.id.editTextTextPersonName3);  //비밀번호 입력 EditText

        button = (Button) findViewById(R.id.button);


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

        Eyes.setOnClickListener(new View.OnClickListener() {  //비밀번호 EditText 눈 표시 클릭
            @Override
            public void onClick(View v) {
                if (eyes == true) {
                    Eyes.setImageResource(R.drawable.eyes_on);  //클릭 시 비밀번호 보이게 이미지 변경
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);  //비밀번호 보이게
                    password.setLetterSpacing((float) -0.04);
                    eyes = false;

                } else {
                    Eyes.setImageResource(R.drawable.eyes_off);  //켜진 상태에서 클릭시 비밀번호 안보이게 이미지 변경
                    password.setInputType(InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_VARIATION_PASSWORD);  //비밀번호 안보이게
                    password.setLetterSpacing((float) -0.04);
                    eyes = true;
                }
            }
        });

        LoginCheck.setOnClickListener(new View.OnClickListener() {  //자동 로그인 체크 버튼 클릭
            @Override
            public void onClick(View v) {


                if (Loginflag == true) {
                    LoginCheck.setImageResource(R.drawable.check_colro_circle);
                    Loginflag = false;
                } else {
                    LoginCheck.setImageResource(R.drawable.check_circle);
                    Loginflag = true;
                }
            }
        });

        LoginText.setOnClickListener(new View.OnClickListener() {  //자동 로그인 Text 클릭
            @Override
            public void onClick(View v) {

                if (Loginflag == true) {
                    LoginCheck.setImageResource(R.drawable.check_colro_circle);
                    Loginflag = false;
                } else {
                    LoginCheck.setImageResource(R.drawable.check_circle);
                    Loginflag = true;
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {  //하단 로그인 버튼
            @Override
            public void onClick(View v) {

                String User_id = Editid.getText().toString();  //아이디 입력 값 저장
                String User_pwd = password.getText().toString();  //비밀번호 입력 값 저장

                //서버로부터 데이터를 받아오는 부분
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(getApplicationContext(), "onResponse 접근 성공", Toast.LENGTH_LONG).show();
                        try {
                            //Toast.makeText(getApplicationContext(), "Try 접근 성공", Toast.LENGTH_LONG).show();
                            //JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));

                            //String User_email = jsonObject.getString("User_email");  //DB에 있는 User_pw를 받아옴, 확인용
                           // Log.d("test", "MainLogin 이메일 " + User_email);

                            boolean success = jsonObject.getBoolean("success");
                            /*Log.d("test","asd " + success);
                            Log.d("test","Id " + User_id);*/

                            if (success) {  //로그인 성공시
                                //Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_LONG).show();
                                //로그인 성공시 메인화면으로 넘어감
                                Intent intent = new Intent(MainLogin.this,Main.class);  //메인화면으로 이동
                                startActivity(intent);
                            } else {
                                //로그인 실패 시 팝업창
                                AlertDialog.Builder dlg = new AlertDialog.Builder(MainLogin.this);
                                dlg.setTitle("로그인 실패");
                                dlg.setMessage("아이디 또는 비밀번호를 확인해주세요.");
                                dlg.setPositiveButton("확인", null);
                                dlg.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                Log.d("테스트", "되나영" + User_id);
                //Log.d("테스트", "되나영" + User_pwd);
                MainLoginRequest mainLoginRequest = new MainLoginRequest(User_id, User_pwd, responseListener);
                //MainLoginRequest mainLoginRequest = new MainLoginRequest(User_id, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainLogin.this);
                queue.add(mainLoginRequest);
            }
        });
    }


}