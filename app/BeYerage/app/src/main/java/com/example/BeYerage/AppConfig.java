package com.example.BeYerage;

import com.example.BeYerage.client.ClientService;
import com.example.BeYerage.client.ClientServiceImpl;
import com.example.BeYerage.shop.MemoryShopRepository;
import com.example.BeYerage.shop.ShopRepository;
import com.example.BeYerage.shop.ShopService;
import com.example.BeYerage.shop.ShopServiceImpl;

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
