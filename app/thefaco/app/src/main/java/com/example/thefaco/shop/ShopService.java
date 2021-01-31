package com.example.thefaco.shop;

import android.widget.Button;

public interface ShopService {
    //고객이 버튼 클릭 시 음성안내
    String voiceGuidance(Button button);
    //해당 음료가 있을 시 반환
    String findBeverageByShop(Beverage beverage);
}
