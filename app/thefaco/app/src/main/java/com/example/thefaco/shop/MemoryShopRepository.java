package com.example.thefaco.shop;

import java.util.HashMap;
import java.util.Map;

public class MemoryShopRepository implements ShopRepository{

    private static final Map<String, Beverage> store = new HashMap<>();

    @Override
    public void save(Beverage beverage) {
        store.put(beverage.getName(), beverage);
    }

    @Override
    public Beverage findByName(String beverageName){
        return store.get(beverageName);
    }
}
