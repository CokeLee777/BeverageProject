package com.example.thefaco.client;

import android.widget.Button;
import com.example.thefaco.shop.Beverage;

public interface ClientService {
    //버튼 클릭시 버튼 클릭여부 반환
    boolean buttonClick(Button button);
    //음료 말하기 -> 음료위치 반환
    String sayBeverageName(String beverageName);
}
