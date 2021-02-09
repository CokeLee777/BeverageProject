package com.example.beverageProject_test;

import com.example.beverageProject_test.client.ClientService;
import com.example.beverageProject_test.client.ClientServiceImpl;
import com.example.beverageProject_test.shop.MemoryShopRepository;
import com.example.beverageProject_test.shop.ShopRepository;
import com.example.beverageProject_test.shop.ShopService;
import com.example.beverageProject_test.shop.ShopServiceImpl;

public class AppConfig {

    public ClientService clientService(){
        return new ClientServiceImpl(shopRepository());
    }

    public ShopService shopService(){
        return new ShopServiceImpl(shopRepository());
    }

    public ShopRepository shopRepository(){
        return new MemoryShopRepository();
    }
}
