package com.example.BeYerage;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public class DetailActivity extends AppCompatActivity {
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        String[] first_sentence = data.split("'");

        String second_sentence = data.substring(data.length()-13, data.length()-12); //@행
        String second_sentence2 = data.substring(data.length()-10, data.length()-9); //@열

//        for(int i=0 ; i<first_sentence.length ; i++)
//        {
//            System.out.println("first_sentence["+i+"] : "+first_sentence[i]);
//        }
        textView4 = (findViewById(R.id.textView4));
        textView5 = (findViewById(R.id.textView5));
        textView6 = (findViewById(R.id.textView6));
        textView7 = (findViewById(R.id.textView7));
        imageView = (findViewById(R.id.imageView_location));

        textView4.setText(first_sentence[1]);
        textView5.setText(first_sentence[3]);
        textView6.setText(first_sentence[5]);
        textView7.setText(first_sentence[7]);

        StringBuffer ab = new StringBuffer();
        ab.append('b').append(second_sentence).append(second_sentence2);
        System.out.println(ab);
        String s3 = ab.toString();

        Resources res = getResources();
        int resID = res.getIdentifier(s3 , "drawable", getPackageName());
        imageView.setImageResource(resID);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(imageView);
        Glide.with(this).load(resID).into(gifImage);
    }
}
