package com.example.thefaco.client;

import android.view.View;
import android.widget.Button;
import com.example.thefaco.shop.ShopRepository;

public class ClientServiceImpl implements ClientService{

    private final ShopRepository shopRepository;

    public ClientServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public boolean buttonClick(Button button) {

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(view.isSelected())
//                else
//            }
//        });
        return false;
    }

    @Override
    public String sayBeverageName(String beverageName) {
        String beverageLocation = shopRepository.findBeverageLocation(beverageName);
        return beverageLocation;
    }
}
