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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainJoinLogin2 extends MainJoinLogin1 {

    Button ColorButton1, GrayButton1, JoinNext;
    Button LoginNextButton;  //본인인증하고 회원가입
    ImageButton BackButton;
    EditText IdEdit, NickNameEdit;
    String aa, bb;
    TextView NameError;  //이름 오류시 나오는 TextView
    TextView NickNameError;  //닉네임 오류시 나오는 TextView

    //MainJoinLogin1(회원가입 첫번째 창)에서 넘긴 값 저장할 변수
    String Id, Pwd, Pwd1, Email;

    private String NameValidation = "^[가-힣]{2,}";  //이름 정규식
    private String NickNameValidation = "^[가-힣]{2,11}";  //닉네임 정규식

    public static boolean flag1 = false;  //이름
    public static boolean flag2 = false;  //닉네임

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joinlogin2);

       Id = getIntent().getStringExtra("아이디 입력 값");

        ColorButton1 = (Button)findViewById(R.id.colorbutton);  //1번 버튼
        GrayButton1 = (Button)findViewById(R.id.graybutton);  //2번 버튼
        JoinNext = (Button)findViewById(R.id.LoginNextButton);  //본인인증하고 회원가입 버튼

        IdEdit = (EditText) findViewById(R.id.Login1IdEditText);
        NickNameEdit = (EditText)findViewById(R.id.Login1PwdEditText);

        BackButton = (ImageButton)findViewById(R.id.BackButton);

        LoginNextButton = (Button)findViewById(R.id.LoginNextButton);  //하단 본인인증하고 회원가입 버튼

        NameError = (TextView) findViewById(R.id.NameError);  //이름 에러시 나오는 Text
        NickNameError = (TextView) findViewById(R.id.NickNameError);  //닉네임 에러시 나오는 Text

        //intent로 넘긴 값 받기
        Intent intent = getIntent();
        String User_id= intent.getStringExtra("User_id").toString();
        String User_pwd = intent.getStringExtra("User_pwd").toString();
        String User_email = intent.getStringExtra("User_email").toString();


        ColorButton1.setOnClickListener(new View.OnClickListener() {  //칼라버튼
            @Override
            public void onClick(View v) {
                ColorButton1.setBackgroundResource(R.drawable.circlebutton);
                GrayButton1.setBackgroundResource(R.drawable.graycirclebutton);
                Intent intent = new Intent(MainJoinLogin2.this, MainJoinLogin1.class);
                startActivity(intent);
            }
        });

        GrayButton1.setOnClickListener(new View.OnClickListener() {  //그레이버튼
            @Override
            public void onClick(View v) {
                ColorButton1.setBackgroundResource(R.drawable.graycirclebutton);
                GrayButton1.setBackgroundResource(R.drawable.circlebutton);

            }
        });

        IdEdit.addTextChangedListener(new TextWatcher() {   //이름 EditText
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

                if(!(aa.matches(NameValidation))) {  //이름 Text가 유효성 검사에 일치하지 않는다면
                    IdEdit.setBackgroundResource(R.drawable.erroredit);
                    IdEdit.setTextColor(Color.parseColor("#191919"));
                    flag1 = false;
                    NameError.setVisibility(View.VISIBLE);  //텍스트보이게
                    NameError.setText("2글자 이상 입력해 주세요.");
                    NameError.setTextColor(Color.parseColor("#ff3120"));

                }
                else {  //유효성 검사에 일치하다면
                    IdEdit.setBackgroundResource(R.drawable.login1editshape);
                    IdEdit.setTextColor(Color.parseColor("#191919"));
                    flag1 = true;
                    NameError.setVisibility(View.VISIBLE);
                    NameError.setText("사용 가능한 이름입니다.");  //에러메시지 > 사용가능 메시지로 변경
                    NameError.setTextColor(Color.parseColor("#3e68ff"));
                }


                if(aa.matches("")) {
                    flag1 = false;
                    IdEdit.setBackgroundResource(R.drawable.login1editshape);
                    NameError.setVisibility(View.GONE);  //아무것도 입력안했을 시 에러메시지 들어감
                }
                else {
                    flag1 = true;
                }

                if(flag1 == true && flag2 == true) {
                    JoinNext.setBackgroundResource(R.drawable.nextcolorbutton);
                    JoinNext.setTextColor(Color.parseColor("#FFFFFF"));
                }
                else {
                    JoinNext.setBackgroundResource(R.drawable.nextgraybutton);
                }
            }
        });

        NickNameEdit.addTextChangedListener(new TextWatcher() {     //닉네임 EditText, 유효성 검사 아직 안함
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                bb = NickNameEdit.getText().toString();
                if(!(bb.matches(NickNameValidation))) {
                    NickNameEdit.setBackgroundResource(R.drawable.erroredit);
                    NickNameEdit.setTextColor(Color.parseColor("#191919"));
                    flag2 = false;
                    NickNameError.setVisibility(View.VISIBLE);  //텍스트보이게
                    NickNameError.setText("2글자 이상 입력해 주세요.");
                    NickNameError.setTextColor(Color.parseColor("#ff3120"));
                }
                else {
                    NickNameEdit.setBackgroundResource(R.drawable.login1editshape);
                    NickNameEdit.setTextColor(Color.parseColor("#191919"));
                    flag2 = true;
                    NickNameError.setVisibility(View.VISIBLE);
                    NickNameError.setText("사용 가능한 이름입니다.");  //에러메시지 > 사용가능 메시지로 변경
                    NickNameError.setTextColor(Color.parseColor("#3e68ff"));
                }


                if(bb.matches("")) {
                    flag2 = false;
                    NickNameEdit.setBackgroundResource(R.drawable.login1editshape);
                    NickNameError.setVisibility(View.GONE);  //아무것도 입력안했을 시 에러메시지 들어감
                }
                else {
                    flag2 = true;
                }

                if(flag1 == true && flag2 == true) {
                    JoinNext.setBackgroundResource(R.drawable.nextcolorbutton);
                    JoinNext.setTextColor(Color.parseColor("#FFFFFF"));
                }
                else {
                    JoinNext.setBackgroundResource(R.drawable.nextgraybutton);
                }
            }
        });

        BackButton.setOnClickListener(new View.OnClickListener() {  //뒤로가기 버튼 클릭 시
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainJoinLogin2.this, MainJoinLogin1.class);
                startActivity(intent);
            }
        });

        LoginNextButton.setOnClickListener(new View.OnClickListener() {  //하단 본인인증하고 회원가입 버튼
            @Override
            public void onClick(View v) {


                String User_name = IdEdit.getText().toString();  //이름 입력
                String User_NickName = NickNameEdit.getText().toString();  //닉네임 입력

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));

                            //success값을 Php에서 가져오기
                            boolean success = jsonObject.getBoolean("success");
                           // Log.d("test","success 값 > " + success);

                            if (success) {  //아이디, 비밀번호, 이메일, 이름, 닉네임이 정상으로 DB에 들어가면 success True
                                //MainJoinLogin3, Pass 화면으로 넘김
                                Intent intent = new Intent(MainJoinLogin2.this, MainJoinLogin3.class);
                                startActivity(intent);
                            } else {  //insert 오류시 토스트 메세지..
                                //Toast.makeText(getApplicationContext(), "insert 실패", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                MainJoinLoginRequest mainJoinLoginRequest = new MainJoinLoginRequest(User_id, User_pwd, User_name, User_email, User_NickName, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainJoinLogin2.this);
                queue.add(mainJoinLoginRequest);
            }
        });

    }
}
