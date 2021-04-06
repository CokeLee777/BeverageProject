package com.example.BeYerage;

import com.example.BeYerage.shop.ShopService;
import com.example.BeYerage.shop.ShopServiceImpl;

public class AppConfig {

    public ShopService shopService(){
        return new ShopServiceImpl();
    }

}
