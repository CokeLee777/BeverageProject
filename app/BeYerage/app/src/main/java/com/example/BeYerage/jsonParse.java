package com.example.BeYerage;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class jsonParse extends AppCompatActivity {
    //json 파싱을 위한 변수
    public static Context mContext;
    Text tts_text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    public void parse(String jsonStr) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            JSONArray jsonArray = new JSONArray(jsonStr);

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                //이건 json 데이터에 따라 바뀜
                String name = jsonObject.getString("name");
                int age = jsonObject.getInt("age");
                String address = jsonObject.getString("address");
                stringBuilder.append(name).append("의 위치는 ").append(" 위치 : ").append(" 위치 : ").append(age).append(" 가격 : ").append(address).append("\n");
            }
            tts_text.setData(String.valueOf(stringBuilder));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}