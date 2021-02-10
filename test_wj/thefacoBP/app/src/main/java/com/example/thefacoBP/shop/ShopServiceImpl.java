package com.example.thefacoBP.shop;

import android.os.Build;
import android.speech.tts.TextToSpeech;
import com.example.thefacoBP.MainActivity;

public class ShopServiceImpl extends MainActivity implements ShopService{

    private final ShopRepository shopRepository;

    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public void voiceGuidance(TextToSpeech tts) {

        //버튼을 클릭했을 때 음성안내
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            //QUEUE_FLUSH: Queue 값을 초기화한 후 값을 넣는다.
            tts.speak("구매하실 음료를 말씀해주세요.", TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            tts.speak("구매하실 음료를 말씀해주세요.", TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    public void voiceGuidance2(TextToSpeech tts, String name, String location) {
        if(name == null){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                //QUEUE_FLUSH: Queue 값을 초기화한 후 값을 넣는다.
                tts.speak("찾으시는 음료가 없습니다.", TextToSpeech.QUEUE_FLUSH, null, null);
            } else {
                tts.speak("찾으시는 음료가 없습니다.", TextToSpeech.QUEUE_FLUSH, null);
            }
        } else {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                //QUEUE_FLUSH: Queue 값을 초기화한 후 값을 넣는다.
                tts.speak(name + "는 " + location + " 에 위치합니다.", TextToSpeech.QUEUE_FLUSH, null, null);
            } else {
                tts.speak(name + "는 " + location + " 에 위치합니다.", TextToSpeech.QUEUE_FLUSH, null);
            }
        }

    }


}
