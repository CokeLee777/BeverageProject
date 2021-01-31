package com.example.thefaco.shop;

import android.view.View;
import android.widget.Button;

public class ShopServiceImpl implements ShopService{

    private final ShopRepository shopRepository;

    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public String voiceGuidance(Button button) {
        //버튼을 클릭했을 때 음성안내 (미완성)
        button.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

        return null;
    }

    @Override
    public String findBeverageByShop(Beverage beverage) {
        String name = beverage.getName();
        String beverageLocation = shopRepository.findBeverageLocation(name);
        if(beverageLocation != null){
            return beverageLocation;
        } else {
            return null;
        }
    }

}
