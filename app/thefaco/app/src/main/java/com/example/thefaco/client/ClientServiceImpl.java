package com.example.thefaco.client;

import com.example.thefaco.shop.ShopRepository;

public class ClientServiceImpl implements ClientService{

    private final ShopRepository shopRepository;

    public ClientServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public String sayBeverageName(String beverageName) {
        if(beverageName != null){
            String beverageLocation = shopRepository.findBeverageLocation(beverageName);
            return beverageLocation;
        } else {
            return null;
        }
    }
}
