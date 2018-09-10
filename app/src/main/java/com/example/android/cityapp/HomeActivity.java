package com.example.android.cityapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class HomeActivity extends AppCompatActivity {

    private Button btnİller;
    private Button btngezilecekyerler;
    private Button btnHavaDurumu;
    private Button btnNesiMeshur;
    Animation uptodwon, downtoup,left;
    LinearLayout l1, l2,l3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        l1 = (LinearLayout) findViewById(R.id.L1);
        l2 = (LinearLayout) findViewById(R.id.l2);
        l3 = (LinearLayout) findViewById(R.id.l3);
        uptodwon = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        left = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        l1.setAnimation(uptodwon);
        l2.setAnimation(downtoup);
        l3.setAnimation(left);

        btnİller = (Button) findViewById(R.id.illeritanıyalım);
        btnİller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnHavaDurumu = (Button) findViewById(R.id.btngirishavadurumu);
        btnHavaDurumu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, havadurumuActivity.class);
                startActivity(intent);
            }
        });

        btnNesiMeshur = (Button) findViewById(R.id.nesimeshur);
        btnNesiMeshur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });

    }


}
