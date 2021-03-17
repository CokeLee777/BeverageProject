package com.example.thefaco;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.thefaco.adapters.RecognitionListenerAdapter;
import com.example.thefaco.client.ClientService;
import com.example.thefaco.shop.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity{
    //컨트롤러
    private static final AppConfig appConfig = new AppConfig();
    //고객 서비스, 음성 서비스, 저장소 변수
    private static final ClientService clientService = appConfig.clientService();
    private static final ShopService shopService = appConfig.shopService();
    private static final ShopRepository shopRepository = appConfig.shopRepository();
    //TTS 변수 선언
    private TextToSpeech tts, tts2;
    //STT를 사용할 intent 와 SpeechRecognizer 초기화
    private SpeechRecognizer sRecognizer;
    private Intent intent;
    //음성인식 결과를 담을 변수
    private String result = new String();
    //DB에 담을 변수
    private String httpResponse = new String();
    private String param = new String();
    //퍼미션 체크를 위한 변수
    private final int PERMISSION = 1;
    TextView st;


    /*테스트*/
    private SpeechRecognizer speechRecognizer;
    private static final int REQUEST_RECORD_AUDIO_PERMISSION_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] colors = {
                ContextCompat.getColor(this, R.color.color1),
                ContextCompat.getColor(this, R.color.color2),
                ContextCompat.getColor(this, R.color.color3),
                ContextCompat.getColor(this, R.color.color4),
                ContextCompat.getColor(this, R.color.color5)
        };

        int[] heights = { 10, 24, 18, 23, 16 };

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);


        final RecognitionProgressView recognitionProgressView = (RecognitionProgressView) findViewById(R.id.recognition_view);
        recognitionProgressView.setSpeechRecognizer(speechRecognizer);
        recognitionProgressView.setRecognitionListener(new RecognitionListenerAdapter() {
            @Override
            public void onResults(Bundle results) {
                showResults(results);
            }
        });
        recognitionProgressView.setColors(colors);
        recognitionProgressView.setBarMaxHeightsInDp(heights);
        recognitionProgressView.setCircleRadiusInDp(8);
        recognitionProgressView.setSpacingInDp(8);
        recognitionProgressView.setIdleStateAmplitudeInDp(8);
        recognitionProgressView.setRotationRadiusInDp(25);
        recognitionProgressView.play();


        /*listen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.RECORD_AUDIO)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                } else {
                    startRecognition();
                    recognitionProgressView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startRecognition();
                        }
                    }, 50);
                }
            }
        });
        */

        //st = findViewById(R.id.Text_say);

        //음성 버튼
        ImageButton voiceButton = findViewById(R.id.voiceButton);
        Button listen = (Button) findViewById(R.id.listen);

        //TTS 환경설정
        checkTTS();

        //버튼 클릭시 음성 안내 서비스 호출
        voiceButton.setOnClickListener(view -> {
            //음성안내 시작
            shopService.voiceGuidance(tts);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //음성인식 시작
                    startSTT();
                }
            },2000);
            // 2초 딜레이 첨부
        });
    }

    private void startRecognition() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en");
        speechRecognizer.startListening(intent);
    }

    /*@Override
    protected void onDestroy() {
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
        super.onDestroy();
    }*/

    private void showResults(Bundle results) {
        ArrayList<String> matches = results
                .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        Toast.makeText(this, matches.get(0), Toast.LENGTH_LONG).show();
    }
    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.RECORD_AUDIO)) {
            Toast.makeText(this, "Requires RECORD_AUDIO permission", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    REQUEST_RECORD_AUDIO_PERMISSION_CODE);
        }
    }

    public void httpConn(String beverageName){
        //================================= Server 통신 ============================================//

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://15.165.63.211:3333//findBeverageInfo";

        //음료 정보 요청
        StringRequest request = new StringRequest(Request.Method.POST, url,
                //요청 성공 시
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        startTTS(response);
                        Log.d("result", response);
                    }
                },
                // 에러 발생 시
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error", "[" + error.getMessage() + "]");
                    }
                }) {
            //요청보낼 때 추가로 파라미터가 필요할 경우
            //url?a=xxx 이런식으로 보내는 대신에 아래처럼 가능.
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("param1", beverageName);
                return  params;
            }
        };

        queue.add(request);

        //================================= Server 통신 ============================================//
    }

    public class VoiceTask extends AsyncTask<String, Integer, String> {
        String str = null;

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            try {
                getVoice();
            } catch (Exception e) {
                // TODO: handle exception
            }
            result = str;
            return str;
        }

        @Override
        protected void onPostExecute(String result) {
            try {

            } catch (Exception e) {
                Log.d("onActivityResult", "getImageURL exception");
            }
        }
    }

    private void getVoice() {

        Intent intent = new Intent();
        intent.setAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        String language = "ko-KR";

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, language);
        startActivityForResult(intent, 2);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            ArrayList<String> results = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            //말한 음료 값
            String str = results.get(0);
            Toast.makeText(getBaseContext(), str, Toast.LENGTH_SHORT).show();

            //TextView tv = findViewById(R.id.Text_say);


            Thread httpThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    httpConn(str);
                    //tts_restart(httpResponse);
                }
            });
            httpThread.start();

        }
    }

    private void startTTS(String result){
        //음료 위치 tts 안내
        tts2 = new TextToSpeech(this, status -> {
            if(status == TextToSpeech.SUCCESS) {
                //언어 선택
                tts2.setLanguage(Locale.KOREAN);
                //tts2.speak(s, TextToSpeech.QUEUE_FLUSH, null);
                shopService.voiceGuidance2(tts2, result);
            }
        });
    }

    //음성인식 환경설정
    private void startSTT(){
        //퍼미션 체크했는데 오류뜸..
        //STT 퍼미션 체크
        if(Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET,
                    Manifest.permission.RECORD_AUDIO},PERMISSION);
        }

        VoiceTask voiceTask = new VoiceTask();
        voiceTask.execute();

    }

    private RecognitionListener listener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle params) {
            Toast.makeText(getApplicationContext(),"음성인식을 시작합니다.",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onBeginningOfSpeech() {

        }

        @Override
        public void onRmsChanged(float rmsdB) {

        }

        @Override
        public void onBufferReceived(byte[] buffer) {

        }

        @Override
        public void onEndOfSpeech() {

        }

        @Override
        public void onError(int error) {
            String message;

            switch (error) {
                case SpeechRecognizer.ERROR_AUDIO:
                    message = "오디오 에러";
                    break;
                case SpeechRecognizer.ERROR_CLIENT:
                    message = "클라이언트 에러";
                    break;
                case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                    message = "퍼미션 없음";
                    break;
                case SpeechRecognizer.ERROR_NETWORK:
                    message = "네트워크 에러";
                    break;
                case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                    message = "네트웍 타임아웃";
                    break;
                case SpeechRecognizer.ERROR_NO_MATCH:
                    message = "찾을 수 없음";
                    break;
                case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                    message = "RECOGNIZER가 바쁨";
                    break;
                case SpeechRecognizer.ERROR_SERVER:
                    message = "서버가 이상함";
                    break;
                case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                    message = "말하는 시간초과";
                    break;
                default:
                    message = "알 수 없는 오류임";
                    break;
            }

            Toast.makeText(getApplicationContext(), "에러가 발생하였습니다. : " + message,Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResults(Bundle results) {
            ArrayList<String> matches = results
                    .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

        }

        @Override
        public void onPartialResults(Bundle partialResults) {

        }

        @Override
        public void onEvent(int eventType, Bundle params) {

        }
    };

    private void checkTTS(){
        //TTS를 생성하고 OnInitListener로 초기화
        tts = new TextToSpeech(this, status -> {
            if(status == TextToSpeech.SUCCESS) {
                //언어 선택
                tts.setLanguage(Locale.KOREAN);
            }
        });

    }

    // 앱 종료시
    @Override
    protected void onDestroy() {

        // Don't forget to shutdown!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }

        if (tts2 != null) {
            tts2.stop();
            tts2.shutdown();
        }

        super.onDestroy();

    }

}