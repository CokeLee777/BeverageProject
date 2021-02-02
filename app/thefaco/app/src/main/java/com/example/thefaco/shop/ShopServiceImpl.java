package com.example.thefaco.shop;

import android.os.Build;
import android.speech.tts.TextToSpeech;

public class ShopServiceImpl implements ShopService{

    private final ShopRepository shopRepository;

    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public String voiceGuidance(TextToSpeech tts) {

        //버튼을 클릭했을 때 음성안내 (미완성)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            //QUEUE_FLUSH: Queue 값을 초기화한 후 값을 넣는다.
            tts.speak("구매하실 음료를 말씀해주세요.", TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            tts.speak("구매하실 음료를 말씀해주세요.", TextToSpeech.QUEUE_FLUSH, null);
        }

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
