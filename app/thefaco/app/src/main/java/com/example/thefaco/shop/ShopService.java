package com.example.thefaco.shop;

import android.speech.tts.TextToSpeech;

public interface ShopService {
    //고객이 버튼 클릭 시 음성안내
    void voiceGuidance(TextToSpeech tts);
}
