package com.moasseo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainFindPwdResult extends MainFindPwd {

    TextView Id, pwderror1, pwderror2;  //아이디, 비밀번호 오류, 비밀번호 재입력 오류
    Button pwdsuccess;  //비밀번호 찾기(변경)버튼
    EditText password1, password2;  //비밀번호 입력

    //비밀번호 입력, 비밀번호 재입력 값 제대로 들어오는지 확인.
    //제대로 입력되면 true
    static boolean flag1 = false;
    static boolean flag2 = false;

    static String aa1;  //비밀번호와 비밀번호 재입력 검사하려고 비밀번호 값 저장.

    //비밀번호 유효성 검사 - 숫자, 영문, 특수문자 최소 8자 이상
    private String PwdValidation = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&]).{8,}.$";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findpasswordresult);

        Id = (TextView)findViewById(R.id.textView29);
        pwderror1 = (TextView)findViewById(R.id.textView23);  //비밀번호 입력 에러 시 나타날 Text
        pwderror2 = (TextView)findViewById(R.id.textView25);  //비밀번호 재입력 에러 시 나타날 Text
        pwdsuccess = (Button)findViewById(R.id.button6);
        password1 = (EditText)findViewById(R.id.editTextTextPersonName2);  //비밀번호 입력
        password2 = (EditText)findViewById(R.id.editTextTextPersonName5);  //비밀번호 재입력

        Intent intent = getIntent();
        String User_id1 = intent.getStringExtra("User_id");  //아이디 전달

        Id.setText("아이디 : " + User_id1);  //아이디 출력

        pwdsuccess.setOnClickListener(new View.OnClickListener(){  //하단 비밀번호 찾기(변경)버튼
            @Override
            public void onClick(View v) {
                //User_name을 통해 DB값을 가져옴
                //SELECT User_id FROM uesr WHERE User_name = ?
               // String User_name = NameEdit.getText().toString();  //이름 값 저장

                //String User_id = id.getText().toString();
                String User_id = User_id1;  //아이디 값을 통해 아이디가 맞으면 DB에서 비밀번호 변경
                String User_pwd = password1.getText().toString();  //비밀번호를 User_pwd에 저장

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(getApplicationContext(), "onResponse 접근 성공", Toast.LENGTH_LONG).show();
                        try {
                            //Toast.makeText(getApplicationContext(), "Try 접근 성공", Toast.LENGTH_LONG).show();
                            //JSONObject jsonObject = new JSONObject(response);
                            JSONObject jsonObject = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));

                            //DB에 있는 값을 가져오는 코드 2줄
                           // String User_id = jsonObject.getString("User_id");  //DB에 있는 User_pw를 받아옴
                           // Log.d("test", "아이디 " + User_id);

                            boolean success = jsonObject.getBoolean("success");
                            /*Log.d("test","asd " + success);
                            Log.d("test","Id " + User_id);*/

                            Toast.makeText(getApplicationContext(), "success 값 " + success, Toast.LENGTH_LONG).show();

                            if (success) {
                                //Toast.makeText(getApplicationContext(), "비밀번호 변경 성공", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(MainFindPwdResult.this,MainFindPwdResult1.class); //비밀번호 확인 창으로 넘김
                                //intent.putExtra("User_id", User_id);//user_id 전송
                                startActivity(intent);
                                /*Intent intent = new Intent(MainFindID.this,MainFindIDResult.class);

                                startActivity(intent);*/
                            } else {
                                //Toast.makeText(getApplicationContext(), "비밀번호 변경 실패", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                MainFindPwdResultRequest mainFindPwdResultRequest = new MainFindPwdResultRequest(User_id, User_pwd, responseListener);
                //MainLoginRequest mainLoginRequest = new MainLoginRequest(User_id, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainFindPwdResult.this);
                queue.add(mainFindPwdResultRequest);
            }
        });

        password1.addTextChangedListener(new TextWatcher() {  //비밀번호 입력 EditText
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String aa = password1.getText().toString();  //비밀번호 값 aa에 저장
                aa1 = password1.getText().toString();

                if(!(aa.matches(PwdValidation))) {  //aa와 비밀번호 유효성 검사가 일치하지 않다면
                    password1.setBackgroundResource(R.drawable.erroredit);  //빨간색 테두리
                    password1.setTextColor(Color.parseColor("#191919")); //글자색 설정
                    flag1 = false; //유효성 검사와 일치하지 않기 때문에 false
                    pwderror1.setVisibility(View.VISIBLE);  //오류 시 등장할 비밀번호 오류 Text
                }
                else {
                    password1.setBackgroundResource(R.drawable.login1editshape);  //검정색 테두리
                    password1.setTextColor(Color.parseColor("#191919"));  //글자색 설정
                    flag1 = true;  //유효성 검사와 일치했기 때문에 true
                    pwderror1.setVisibility(View.GONE);  //사라짐
                }

                if(aa.matches("")) {  //aa가 비어있다면;
                    flag1 = false;  //아무것도 안적었기 때문에 false
                    IdEdit.setBackgroundResource(R.drawable.login1editshape);  //검정색 테두리
                    pwderror1.setVisibility(View.GONE);  //사라짐
                }

                //비밀번호, 비밀번호 재입력 모두 유효성 검사에 일치하다면
                if(flag1 == true && flag2 == true) {
                    pwdsuccess.setBackgroundResource(R.drawable.nextcolorbutton);  //하단 다음버튼 활성화
                    pwdsuccess.setTextColor(Color.parseColor("#FFFFFF"));  //하단 다음버튼 글씨 흰색으로 변경
                }
                else {
                    pwdsuccess.setBackgroundResource(R.drawable.nextgraybutton);  //하나라도 아니라면 다시 비활성화로
                }
            }
        });

        password2.addTextChangedListener(new TextWatcher() {  //비밀번호 재입력 EditText
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String bb = password2.getText().toString();  //비밀번호 값 aa에 저장

                if(!(bb.equals(aa1))) {  //비밀번호와 비밀번호 재입력이 일치하지 않는다면
                    password2.setBackgroundResource(R.drawable.erroredit);  //빨간색 테두리
                    password2.setTextColor(Color.parseColor("#191919"));
                    flag2 = false;
                    pwderror2.setVisibility(View.VISIBLE);  //비밀번호와 재입력이 일치하지 않으면 나타날 오류 Text
                }
                else {  //비밀번호와 비밀번호 재입력이 일치하다면
                    password2.setBackgroundResource(R.drawable.login1editshape);
                    password2.setTextColor(Color.parseColor("#191919"));
                    flag2 = true;
                    pwderror2.setVisibility(View.GONE);  //오류 Text 사라짐
                }

                if(bb.matches("")) {  //아무것도 입력 안했다면
                    flag2 = false;
                    password2.setBackgroundResource(R.drawable.login1editshape); //흰색 배경
                    pwderror2.setVisibility(View.GONE);  //오류 Text 사라짐
                }

                //비밀번호, 비밀번호 재입력 모두 유효성 검사에 일치하다면
                if(flag1 == true && flag2 == true ) {
                    pwdsuccess.setBackgroundResource(R.drawable.nextcolorbutton);
                    pwdsuccess.setTextColor(Color.parseColor("#FFFFFF"));
                }
                else {
                    pwdsuccess.setBackgroundResource(R.drawable.nextgraybutton);
                }

            }
        });
    }
}
