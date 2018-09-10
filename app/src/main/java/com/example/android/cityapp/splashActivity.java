package com.example.android.cityapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class splashActivity extends Activity {
    LinearLayout l1, l2;
    Animation uptodown, downtoup, together;
    ImageView image;
    AppCompatButton appCompatButtonEvet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        appCompatButtonEvet = (AppCompatButton) findViewById(R.id.evet);

        l1 = (LinearLayout) findViewById(R.id.linearsplash1);
        l2 = (LinearLayout) findViewById(R.id.linearsplash2);
        image = (ImageView) findViewById(R.id.imageView);
        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        together = AnimationUtils.loadAnimation(this, R.anim.together);
        l1.setAnimation(uptodown);
        l2.setAnimation(downtoup);
        image.setAnimation(together);
        appCompatButtonEvet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(splashActivity.this, StartActivity.class);
                startActivity(go);

            }
        });


    }
}
