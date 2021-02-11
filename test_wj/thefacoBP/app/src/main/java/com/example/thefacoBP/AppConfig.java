package com.example.thefacoBP;

import com.example.thefacoBP.client.ClientService;
import com.example.thefacoBP.client.ClientServiceImpl;
import com.example.thefacoBP.shop.MemoryShopRepository;
import com.example.thefacoBP.shop.ShopRepository;
import com.example.thefacoBP.shop.ShopService;
import com.example.thefacoBP.shop.ShopServiceImpl;

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
