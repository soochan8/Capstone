package com.moasseo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainJoinLogin1 extends MainLogin {

    Button ColorButton1, GrayButton1;  //1번,2번 Button
    Button LoginNextButton1;  //하단 다음 Button
    ImageButton BackButton;  //뒤로가기 ImageButton
    EditText IdEdit, PwdEdit, PwdEdit2, EmailEdit;  //아이디, 비밀번호, 비밀번호 재입력, 이메일

    TextView text15,text16, text17, LoginPwdText2;
    TextView LoginText;  //하단 로그인 하기 Text

    ImageView Eyes;  //비밀번호 입력 보이게 / 안보이게
    ImageView Eyes1;  //비밀번호 재입력 보이게 / 안보이게

    static boolean eyes = true;  //비밀번호 보이게, 안보이게 할 때 사용
    static boolean eyes1 = true;  //비밀번호 재입력 보이게, 안보이게 할 때 사용

    //유효성검사(아이디, 비밀번호, 이메일)
    private String IdValidation = "^[a-zA-Z0-9]{5,11}";  //영문 또는 숫자로 이루어진 5~11자
    private String PwdValidation = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&]).{8,}.$";  //숫자, 영문, 특수문자 최소 8자 이상
    private String emailValidation = "^[_A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    //EditText 4개 다 적었는지 확인용
    static boolean flag1 = false;
    static boolean flag2 = false;
    static boolean flag3 = false;
    static boolean flag4 = false;


    //아이디, 비밀번호, 비밀번호 재입력, 이메일 값 저장할 String형 변수
    String aa, bb, cc, dd;

    //비밀번호와 비밀번호 재입력 비교할 때 사용
    public static String bb1, cc1;  //비밀번호 맞는지?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joinlogin1);

        ColorButton1 = (Button)findViewById(R.id.colorbutton);  //1번 Button
        GrayButton1 = (Button)findViewById(R.id.graybutton);  //2번 Button
        BackButton = (ImageButton) findViewById(R.id.BackButton);  //뒤로가기 ImageButton

        LoginNextButton1 = (Button)findViewById(R.id.LoginNextButton);  //다음 버튼

        IdEdit = (EditText)findViewById(R.id.Login1IdEditText);  //아이디 입력 EditText
        PwdEdit = (EditText)findViewById(R.id.Login1PwdEditText);  //비밀번호 입력 EditText
        PwdEdit2 = (EditText)findViewById(R.id.Login1PwdEditText1);  //비밀번호 재입력 EditText
        EmailEdit = (EditText)findViewById(R.id.Login1EmailEditText);  //이메일 입력 EditText

        text15 = (TextView)findViewById(R.id.textView15);
        text16 = (TextView)findViewById(R.id.textView);
        text17 = (TextView)findViewById(R.id.textView8);
        LoginPwdText2 = (TextView)findViewById(R.id.LoginPwdText2);
        LoginText = (TextView)findViewById(R.id.textView27);  //하단 로그인 Text

        Eyes = (ImageView)findViewById(R.id.imageView3);  //비밀번호 보이게 / 안보이게
        Eyes1 = (ImageView)findViewById(R.id.imageView5);  //비밀번호 재입력 보이게 / 안보이게

        //색 변경, 화면넘김 아직!!
        ColorButton1.setOnClickListener(new View.OnClickListener() {  //칼라버튼
            @Override
            public void onClick(View v) {
                ColorButton1.setBackgroundResource(R.drawable.circlebutton);
                GrayButton1.setBackgroundResource(R.drawable.graycirclebutton);
            }
        });

        IdEdit.addTextChangedListener(new TextWatcher() {   //아이디입력 EditText
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressLint("ResourceType")
            @Override
            public void afterTextChanged(Editable s) {
                aa = IdEdit.getText().toString();  //아이디 입력 값 aa에 저장

                if(!(aa.matches(IdValidation))) {  //aa와 아이디 유효성 검사가 일치하지 않다면
                    IdEdit.setBackgroundResource(R.drawable.erroredit);  //빨간색 테두리
                    IdEdit.setTextColor(Color.parseColor("#191919"));
                    flag1 = false;
                    text15.setVisibility(View.VISIBLE);  //텍스트보이게
                    text15.setText("영문, 숫자 5~11자를 입력해야 됩니다.");
                    text15.setTextColor(Color.parseColor("#ff3120"));
                }
                else {  //aa와 아이디 유효성 검사가 일치하다면
                    IdEdit.setBackgroundResource(R.drawable.login1editshape);  //검정색 테두리
                    IdEdit.setTextColor(Color.parseColor("#191919"));
                    flag1 = true;  //flag1을 true로 변경 / flag 1~4가 다 true면 하단 다음 Button 활성화
                    text15.setVisibility(View.VISIBLE);
                    text15.setText("사용가능한 아이디입니다.");
                    text15.setTextColor(Color.parseColor("#3e68ff"));
                }

                if(aa.matches("")) {  //aa가 비어있다면
                    text15.setVisibility(View.GONE);
                    flag1 = false;
                    IdEdit.setBackgroundResource(R.drawable.login1editshape);  //검정색 테두리
                }

                //아이디,비밀번호, 비밀번호 재입력, 이메일이 모두 유효성 검사에 일치하다면
                if(flag1 == true && flag2 == true && flag3 == true && flag4 == true) {
                    LoginNextButton1.setBackgroundResource(R.drawable.nextcolorbutton);  //하단 다음버튼 활성화
                    LoginNextButton1.setTextColor(Color.parseColor("#FFFFFF"));  //하단 다음버튼 글씨 흰색으로 변경
                }
                else {
                    LoginNextButton1.setBackgroundResource(R.drawable.nextgraybutton);  //하나라도 아니라면 다시 비활성화로
                }
            }
        });

        PwdEdit.addTextChangedListener(new TextWatcher() {  //비밀번호 입력 EditText
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                bb = PwdEdit.getText().toString();  //비밀번호 입력 값 bb에 저장
                bb1 = PwdEdit.getText().toString();  //비밀번호 입력 값 bb에 저장 (비밀번호 재입력과 비교하기 위해)

                /*if (!(bb1.equals(cc1))) {  //비밀번호와 비밀번호 재입력이 일치하지 않는다면
                    PwdEdit2.setBackgroundResource(R.drawable.erroredit);  //빨간색 테두리
                    PwdEdit2.setTextColor(Color.parseColor("#191919"));
                    flag3 = false;
                } else {
                    PwdEdit2.setBackgroundResource(R.drawable.login1editshape);
                    PwdEdit2.setTextColor(Color.parseColor("#191919"));
                    flag3 = true;
                }  -> 필요없음 삭제, 비밀번호 재입력시 비밀번호칸 빨간색 */

                if(!(bb.matches(PwdValidation))) { //bb가 비밀번호 유효성 검사가 일치하지 않다면
                    PwdEdit.setBackgroundResource(R.drawable.erroredit);
                    PwdEdit.setTextColor(Color.parseColor("#191919"));
                    flag2 = false;
                }
                else {
                    PwdEdit.setBackgroundResource(R.drawable.login1editshape);
                    PwdEdit.setTextColor(Color.parseColor("#191919"));
                    flag2 = true;
                }

                if(bb.matches("")) {
                    flag2 = false;
                    PwdEdit.setBackgroundResource(R.drawable.login1editshape);  //썼다가 지웠을 때 오류 사라짐
                }

                if(flag1 == true && flag2 == true && flag3 == true && flag4 == true) {
                    LoginNextButton1.setBackgroundResource(R.drawable.nextcolorbutton);
                    LoginNextButton1.setTextColor(Color.parseColor("#FFFFFF"));
                }
                else {
                    LoginNextButton1.setBackgroundResource(R.drawable.nextgraybutton);
                }
            }
        });

        Eyes.setOnClickListener(new View.OnClickListener() {  //비밀번호 EditText 눈 표시 클릭
            @Override
            public void onClick(View v) {
                if (eyes == true) {
                    Eyes.setImageResource(R.drawable.eyes_on);  //클릭 시 비밀번호 보이게 이미지 변경
                    PwdEdit.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);  //비밀번호 보이게
                    PwdEdit.setLetterSpacing((float) -0.04);
                    eyes = false;

                } else {
                    Eyes.setImageResource(R.drawable.eyes_off);  //켜진 상태에서 클릭시 비밀번호 안보이게 이미지 변경
                    PwdEdit.setInputType(InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_VARIATION_PASSWORD);  //비밀번호 안보이게
                    PwdEdit.setLetterSpacing((float) -0.04);
                    eyes = true;
                }
            }
        });

        PwdEdit2.addTextChangedListener(new TextWatcher() {  //비밀번호 재입력
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                cc = PwdEdit2.getText().toString();
                cc1 = PwdEdit2.getText().toString();

                if(!(cc.equals(bb1))) {  //비밀번호와 비밀번호 재입력이 일치하지 않는다면
                    PwdEdit2.setBackgroundResource(R.drawable.erroredit);  //빨간색 테두리
                    PwdEdit2.setTextColor(Color.parseColor("#191919"));
                    flag3 = false;
                    text16.setVisibility(View.VISIBLE);  //텍스트보이게
                    text16.setText("비밀번호가 일치하지 않습니다.");
                    text16.setTextColor(Color.parseColor("#ff3120"));
                }
                else {  //비밀번호와 비밀번호 재입력이 일치하다면
                    PwdEdit2.setBackgroundResource(R.drawable.login1editshape);
                    PwdEdit2.setTextColor(Color.parseColor("#191919"));
                    flag3 = true;
                    text16.setVisibility(View.VISIBLE);
                    text16.setText("비밀번호가 일치합니다.");
                    text16.setTextColor(Color.parseColor("#3e68ff"));
                }

                if(cc.matches("")) {  //아무것도 입력 안했더라면
                    flag3 = false;
                    PwdEdit2.setBackgroundResource(R.drawable.login1editshape); //흰색 배경
                    text16.setVisibility(View.GONE);
                }

                if(flag1 == true && flag2 == true && flag3 == true && flag4 == true) {
                    LoginNextButton1.setBackgroundResource(R.drawable.nextcolorbutton);
                    LoginNextButton1.setTextColor(Color.parseColor("#FFFFFF"));
                }
                else {
                    LoginNextButton1.setBackgroundResource(R.drawable.nextgraybutton);
                }
            }
        });

        Eyes1.setOnClickListener(new View.OnClickListener() {  //비밀번호 재입력 EditText 눈 표시 클릭
            @Override
            public void onClick(View v) {
                if (eyes1 == true) {
                    Eyes1.setImageResource(R.drawable.eyes_on);  //클릭 시 비밀번호 보이게 이미지 변경
                    PwdEdit2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);  //비밀번호 보이게
                    PwdEdit2.setLetterSpacing((float) -0.04);
                    eyes1 = false;

                } else {
                    Eyes1.setImageResource(R.drawable.eyes_off);  //켜진 상태에서 클릭시 비밀번호 안보이게 이미지 변경
                    PwdEdit2.setInputType(InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_VARIATION_PASSWORD);  //비밀번호 안보이게
                    PwdEdit2.setLetterSpacing((float) -0.04);
                    eyes1 = true;
                }
            }
        });

        EmailEdit.addTextChangedListener(new TextWatcher() {  //이메일 입력 EditText
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                dd = EmailEdit.getText().toString();

                if(!(dd.matches(emailValidation) && s.length() > 0)) {  //dd가 이메일 유효성 검사가 일치하지 않다면
                    // Toast.makeText(getApplicationContext(), "이메일형식으로 입력해주세요", Toast.LENGTH_SHORT).show();
                    EmailEdit.setBackgroundResource(R.drawable.erroredit);
                    EmailEdit.setTextColor(Color.parseColor("#191919"));
                    flag4 = false;
                    text17.setVisibility(View.VISIBLE);  //텍스트보이게
                    text17.setText("이메일 주소가 올바르지 않습니다.");
                    text17.setTextColor(Color.parseColor("#ff3120"));
                }
                else {  //dd가 이메일 유효성 검사에 일치하다면
                    EmailEdit.setBackgroundResource(R.drawable.login1editshape);
                    EmailEdit.setTextColor(Color.parseColor("#191919"));
                    flag4 = true;
                    text17.setVisibility(View.VISIBLE);
                    text17.setText("사용가능한 이메일입니다.");
                    text17.setTextColor(Color.parseColor("#3e68ff"));
                }

                if(dd.matches("")) {
                    flag4 = false;
                    EmailEdit.setBackgroundResource(R.drawable.login1editshape);
                    text17.setVisibility(View.GONE);
                }

                if(flag1 == true && flag2 == true && flag3 == true && flag4 == true) {
                    LoginNextButton1.setBackgroundResource(R.drawable.nextcolorbutton);
                    LoginNextButton1.setTextColor(Color.parseColor("#FFFFFF"));
                }
                else {
                    LoginNextButton1.setBackgroundResource(R.drawable.nextgraybutton);
                }
            }
        });

        LoginNextButton1.setOnClickListener(new View.OnClickListener() {  //하단 다음 버튼
            @Override
            public void onClick(View v) {
                /*if(flag1 == true && flag2 == true && flag3 == true && flag4 == true) {
                    Intent intent = new Intent(MainJoinLogin1.this, MainJoinLogin2.class);
                    startActivity(intent);
                }*/
                String User_id = IdEdit.getText().toString();  //아이디 값
                String User_pwd = PwdEdit.getText().toString();  //비밀번호 값
                String User_email = EmailEdit.getText().toString();  //이메일 값

                Intent intent = new Intent(MainJoinLogin1.this, MainJoinLogin2.class);  //화면 넘김
                //intent로 값 넘기기
                intent.putExtra("User_id", User_id);
                intent.putExtra("User_pwd", User_pwd);
                intent.putExtra("User_email", User_email);
                startActivity(intent);

            }
        });

        BackButton.setOnClickListener(new View.OnClickListener() {  //뒤로가기 버튼 클릭 시
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainJoinLogin1.this, MainLogin.class);
                intent.putExtra("아이디 입력 값", aa);
                intent.putExtra("비밀번호 입력 값", bb);
                intent.putExtra("비밀번호 재입력 값", cc);
                intent.putExtra("이메일 입력 값", dd);
                startActivity(intent);
            }
        });

        LoginText.setOnClickListener(new View.OnClickListener() {  //하단 로그인 Text 클릭 시
            @Override
            public void onClick(View v) {
                //로그인 Text 클릭 시 로그인 화면으로 이동
                Intent intent = new Intent(MainJoinLogin1.this, MainLogin.class);
                startActivity(intent);
            }
        });
    }
}
