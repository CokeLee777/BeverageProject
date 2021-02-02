package com.example.thefaco;

import com.example.thefaco.client.ClientService;
import com.example.thefaco.client.ClientServiceImpl;
import com.example.thefaco.shop.MemoryShopRepository;
import com.example.thefaco.shop.ShopRepository;
import com.example.thefaco.shop.ShopService;
import com.example.thefaco.shop.ShopServiceImpl;

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
