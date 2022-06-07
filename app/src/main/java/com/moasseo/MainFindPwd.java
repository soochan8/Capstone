package com.moasseo;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainFindPwd extends MainLogin {

    Button FindId, FindPwd, FindPwdNext;
    EditText IdEdit, EmailEdit;
    Button NextFindPwd;  //하단 비밀번호 찾기 버튼
    ImageButton BackButton;

    String aa, bb;

    //이메일 유효성
    private String emailValidation = "^[_A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean flag1 = false;
    public static boolean flag2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findpassword);

        FindId = (Button)findViewById(R.id.button3);  //상단 아이디찾기 버튼
        FindPwd = (Button)findViewById(R.id.button4);  // 상단 비밀번호찾기 버튼

        FindPwdNext = (Button)findViewById(R.id.button5);  //비밀번호 찾기 버튼

        BackButton = (ImageButton)findViewById(R.id.BackButton);  //뒤로가기 버튼

        NextFindPwd = (Button)findViewById(R.id.button5);  //하단 비밀번호 찾기 버튼

        IdEdit = (EditText)findViewById(R.id.editTextTextPersonName3);  //아이디 입력
        EmailEdit = (EditText)findViewById(R.id.editTextTextPersonName4);  //이메일 입력


        FindId.setOnClickListener(new View.OnClickListener() {   //상단 아이디찾기 버튼 클릭
            @Override
            public void onClick(View v) {
                FindId.setBackgroundResource(R.drawable.findidbutton);
                FindPwd.setBackgroundResource(R.drawable.findpwdbutton);
                Intent intent = new Intent(MainFindPwd.this, MainFindID.class);
                startActivity(intent);
            }
        });

        IdEdit.addTextChangedListener(new TextWatcher() {  //아이디 입력, 유효성 검사 아직 안됌!
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                aa = IdEdit.getText().toString();
                if(aa.length() != 0) {
                    flag1 = true;
                }

                if(aa.matches("")) {
                    flag1 = false;
                }

                if(flag1 == true && flag2 == true) {
                    FindPwdNext.setBackgroundResource(R.drawable.nextcolorbutton);
                }
                else {
                    FindPwdNext.setBackgroundResource(R.drawable.nextgraybutton);
                }
            }
        });

        EmailEdit.addTextChangedListener(new TextWatcher() {  //이메일 입력
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                bb = EmailEdit.getText().toString();

                //이메일 유효성 검사
                if(!(bb.matches(emailValidation) && s.length() > 0)) {
                    // Toast.makeText(getApplicationContext(), "이메일형식으로 입력해주세요", Toast.LENGTH_SHORT).show();
                    EmailEdit.setBackgroundResource(R.drawable.erroredit);
                    EmailEdit.setTextColor(Color.parseColor("#191919"));
                    flag2 = false;
                }
                else {
                    EmailEdit.setBackgroundResource(R.drawable.login1editshape);
                    EmailEdit.setTextColor(Color.parseColor("#191919"));
                    flag2 = true;
                }


                if(bb.matches("")) {
                    flag2 = false;
                    EmailEdit.setBackgroundResource(R.drawable.login1editshape);
                }

                if(flag1 == true && flag2 == true) {
                    FindPwdNext.setBackgroundResource(R.drawable.nextcolorbutton);
                    FindPwdNext.setTextColor(Color.parseColor("#FFFFFF"));
                }
                else {
                    FindPwdNext.setBackgroundResource(R.drawable.nextgraybutton);
                }
            }
        });

        BackButton.setOnClickListener(new View.OnClickListener() {  //뒤로가기 버튼 클릭 시
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainFindPwd.this, MainLogin.class);
                startActivity(intent);
            }
        });

        NextFindPwd.setOnClickListener(new View.OnClickListener() {  //하단 비밀번호 찾기 버튼
            @Override
            public void onClick(View v) {

                String User_id = IdEdit.getText().toString();  //아이디 입력 값 저장
                String User_email = EmailEdit.getText().toString();  //비밀번호 입력 값 저장

                //서버로부터 데이터를 받아오는 부분
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(getApplicationContext(), "onResponse 접근 성공", Toast.LENGTH_LONG).show();
                        try {
                            JSONObject jsonObject = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));

                            boolean success = jsonObject.getBoolean("success");

                            if (success) {  //DB에 아이디와 이메일이 있는지? -> 있으면 비밀번호 변경 창으로
                                //로그인 성공시 비밀번호 변경 창으로 넘어감
                                Intent intent = new Intent(MainFindPwd.this,MainFindPwdResult.class);  //메인화면으로 이동
                                intent.putExtra("User_id", User_id);// User_id (아이디) 전송
                                startActivity(intent);
                            } else {  //DB에 아이디와 이메일이 없으면 -> 오류 팝업창
                                //DB에 아이디 또는 이메일이 없으면 오류 팝업창 발생
                                AlertDialog.Builder dlg = new AlertDialog.Builder(MainFindPwd.this);
                                dlg.setTitle("비밀번호 찾기 실패");
                                dlg.setMessage("아이디 또는 이메일을 확인해주세요.");
                                dlg.setPositiveButton("확인", null);
                                dlg.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                MainFindPwdRequest mainFindPwdRequest = new MainFindPwdRequest(User_id, User_email, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainFindPwd.this);
                queue.add(mainFindPwdRequest);
            }
        });


    }
}


