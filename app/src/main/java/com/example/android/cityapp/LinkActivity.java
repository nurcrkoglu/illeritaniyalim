package com.example.android.cityapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class LinkActivity extends AppCompatActivity {

    MainActivity mainActivity = new MainActivity();

    TextView gelenLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);

        gelenLink=(TextView)findViewById(R.id.link);

      //  gelenLink.setText(mainActivity.txtlink.toString());

        gelenLink.setText(getIntent().getExtras().getString("veri"));
        gelenLink.setMovementMethod(LinkMovementMethod.getInstance());

    }
}
