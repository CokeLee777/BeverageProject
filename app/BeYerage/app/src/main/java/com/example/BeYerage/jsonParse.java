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
                Long id = jsonObject.getLong("id");
                String name = jsonObject.getString("name");
                int price = jsonObject.getInt("price");
                String type = jsonObject.getString("type");
                int size = jsonObject.getInt("size");

                //말할 내용 세팅
                stringBuilder.append(name).append("의 가격은").append(price).append("원 이며 ")
                        .append(size).append("밀리리터 용량의 ")
                        .append(type).append("음료 입니다").append("\n");
            }
            tts_text.setData(String.valueOf(stringBuilder));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}