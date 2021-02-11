package com.example.thefacoBP.client;

import com.example.thefacoBP.shop.Beverage;
import com.example.thefacoBP.shop.ShopRepository;

public class ClientServiceImpl implements ClientService{

    private final ShopRepository shopRepository;

    public ClientServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public void join(Beverage beverage) {
        shopRepository.save(beverage);
    }

    @Override
    public Beverage findBeverage(String beverageName) {
        return shopRepository.findByName(beverageName);
    }
}
