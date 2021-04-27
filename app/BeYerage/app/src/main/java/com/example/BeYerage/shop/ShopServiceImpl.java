package com.example.BeYerage.shop;

import android.os.Build;
import android.speech.tts.TextToSpeech;

import com.example.BeYerage.MainActivity;

public class ShopServiceImpl extends MainActivity implements ShopService{

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
    public void voiceGuidance2(TextToSpeech tts, String result) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            //QUEUE_FLUSH: Queue 값을 초기화한 후 값을 넣는다.
            tts.speak(result, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            tts.speak(result, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    public void voiceGuidance3(TextToSpeech tts) {
        //지원 편의점에서 버튼을 클릭했을 때 음성안내
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            //QUEUE_FLUSH: Queue 값을 초기화한 후 값을 넣는다.
            tts.speak("현재 사용자의 위치를 기반으로, 지원되는 편의점은 경기대학교 제2공학관 4층 편의점이며, 위치는 경기도 수원시 영통구 광교산로 154-42입니다.", TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            tts.speak("현재 사용자의 위치를 기반으로, 지원되는 편의점은 경기대학교 제2공학관 4층 편의점이며, 위치는 경기도 수원시 영통구 광교산로 154-42입니다.", TextToSpeech.QUEUE_FLUSH, null);
        }
    }
}
