package com.example.BeYerage.client;

import com.example.BeYerage.shop.Beverage;

public interface ClientService {
    void join(Beverage beverage);
    //음료 말하기 -> 음료위치 반환
    Beverage findBeverage(String beverageName);
}
