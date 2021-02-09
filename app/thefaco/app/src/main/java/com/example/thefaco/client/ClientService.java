package com.example.thefaco.client;

import com.example.thefaco.shop.Beverage;

public interface ClientService {
    void join(Beverage beverage);
    //음료 말하기 -> 음료위치 반환
    Beverage findBeverage(String beverageName);
}
