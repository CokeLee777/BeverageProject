package com.example.beverageProject_test.shop;

import java.util.HashMap;
import java.util.Map;

public class MemoryShopRepository implements com.example.beverageProject_test.shop.ShopRepository {

    private static final Map<String, Beverage> store = new HashMap<>();

    @Override
    public void save(Beverage beverage) {
        store.put(beverage.getName(), beverage);
    }

    @Override
    public String findBeverageLocation(String beverageName) {
        Beverage beverage = store.get(beverageName);
        String location = beverage.getLocation();
        if(location != null){
            return location;
        } else {
            return null;
        }
    }
}
