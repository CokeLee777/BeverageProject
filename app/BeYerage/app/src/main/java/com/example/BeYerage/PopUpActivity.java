package com.example.BeYerage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

// 음료 정보 알려주는 팝업창

public class PopUpActivity extends Activity {

    TextView popupText;
    //음료 데이터 가져오기

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);

        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        //UI 객체생성
        popupText = (TextView)findViewById(R.id.popupText);
        popupText.setText(data);
    }

    //확인 버튼 클릭
    public void mOnClose(View v){
        //데이터 전달하기
        Intent intent = new Intent();
        intent.putExtra("result", "Close Popup");
        setResult(RESULT_OK, intent);

        //액티비티(팝업) 닫기
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return true;
        }
        return true;
    }

    /*
    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 안 막고싶은데 ㅇㅅaㅇ
        return;
    }
    */
}

