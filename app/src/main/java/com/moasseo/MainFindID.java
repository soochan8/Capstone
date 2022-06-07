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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainFindID extends MainLogin {

    Button FindId, FindPwd, FindIdNext;  //IDNext - 아이디찾기
    EditText NameEdit, EmailEdit;

    ImageButton BackButton;

    String aa, bb;
    boolean Emailflag = true;
    String EmailText;

    //이메일, 이름 유효성 검사
    private String emailValidation = "^[_A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private String IdValidation= ("^[ㄱ-ㅎ가-힣]+$");

    public static boolean flag1 = false;
    public static boolean flag2 = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findid);

        FindId = (Button)findViewById(R.id.button3);  //상단 아이디 찾기버튼
        FindPwd = (Button)findViewById(R.id.button4);  //상단 비밀번호 찾기 버튼
        FindIdNext = (Button)findViewById(R.id.button5);  //하단 아이디 찾기 버튼
        BackButton = (ImageButton)findViewById(R.id.BackButton);  //뒤로가기 버튼

        NameEdit = (EditText)findViewById(R.id.editTextTextPersonName3);  //이름 edit
        EmailEdit = (EditText)findViewById(R.id.editTextTextPersonName4);  //이메일 edit



        FindPwd.setOnClickListener(new View.OnClickListener() {      //비밀번호찾기 버튼
            @Override
            public void onClick(View v) {
                FindId.setBackgroundResource(R.drawable.findpwdbutton);
                FindPwd.setBackgroundResource(R.drawable.findidbutton);
                Intent intent = new Intent(MainFindID.this, MainFindPwd.class);  //화면 이동
                startActivity(intent);
            }
        });

        NameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                aa = NameEdit.getText().toString();

                if(!(aa.matches(IdValidation) && s.length() >= 2)) {
                    NameEdit.setBackgroundResource(R.drawable.erroredit);
                    NameEdit.setTextColor(Color.parseColor("#191919"));
                    flag1 = false;
                }
                else {
                    NameEdit.setBackgroundResource(R.drawable.login1editshape);
                    NameEdit.setTextColor(Color.parseColor("#191919"));
                    flag1 = true;
                }

                if(aa.matches("")) {
                    flag1 = false;
                    NameEdit.setBackgroundResource(R.drawable.login1editshape);
                }

                if(flag1 == true && flag2 == true) {
                    FindIdNext.setBackgroundResource(R.drawable.nextcolorbutton);
                }
                else {
                    FindIdNext.setBackgroundResource(R.drawable.nextgraybutton);
                }
            }
        });

        EmailEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                bb = EmailEdit.getText().toString();

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
                    FindIdNext.setBackgroundResource(R.drawable.nextcolorbutton);
                    FindIdNext.setTextColor(Color.parseColor("#FFFFFF"));
                }
                else {
                    FindIdNext.setBackgroundResource(R.drawable.nextgraybutton);
                }
            }
        });

        FindIdNext.setOnClickListener(new View.OnClickListener() {  //아이디 찾기 버튼
            @Override
            public void onClick(View v) {
                //User_name을 통해 DB값을 가져옴
                //SELECT User_id FROM uesr WHERE User_name = ?
                String User_name = NameEdit.getText().toString();  //이름 값 저장
                //[해야 할 것]이메일도 받아와서 이름, 이메일로 아이디 가져오기

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(getApplicationContext(), "onResponse 접근 성공", Toast.LENGTH_LONG).show();
                        try {
                            //Toast.makeText(getApplicationContext(), "Try 접근 성공", Toast.LENGTH_LONG).show();
                            //JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));

                            //DB에 있는 값을 가져오는 코드 2줄
                            String User_id = jsonObject.getString("User_id");  //DB에 있는 User_id를 받아옴
                           // Log.d("test", "아이디 " + User_id);

                            boolean success = jsonObject.getBoolean("success");
                            /*Log.d("test","asd " + success);
                            Log.d("test","Id " + User_id);*/

                            if (success) {
                                //Toast.makeText(getApplicationContext(), "우선 성공", Toast.LENGTH_LONG).show();
                                //아이디 찾기 시 MainFindIdResult로 넘어감
                                Intent intent = new Intent(MainFindID.this,MainFindIDResult.class);
                                intent.putExtra("User_id", User_id);//User_id (아이디) 전송
                                startActivity(intent);
                                /*Intent intent = new Intent(MainFindID.this,MainFindIDResult.class);

                                startActivity(intent);*/
                            } else {
                               // Toast.makeText(getApplicationContext(), "실패", Toast.LENGTH_LONG).show();
                                //아이디 찾기 실패시 팝업창
                                AlertDialog.Builder dlg = new AlertDialog.Builder(MainFindID.this);
                                dlg.setTitle("아이디 찾기 실패");
                                dlg.setMessage("이름 또는 이메일을 확인해주세요.");
                                dlg.setPositiveButton("확인", null);
                                dlg.show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                MainFindIDRequest mainFindIDRequest = new MainFindIDRequest(User_name, responseListener);
                //MainLoginRequest mainLoginRequest = new MainLoginRequest(User_id, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainFindID.this);
                queue.add(mainFindIDRequest);
            }
        });

        BackButton.setOnClickListener(new View.OnClickListener() {  //뒤로가기 버튼 클릭 시
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainFindID.this, MainLogin.class);
                startActivity(intent);
            }
        });

    }
}
