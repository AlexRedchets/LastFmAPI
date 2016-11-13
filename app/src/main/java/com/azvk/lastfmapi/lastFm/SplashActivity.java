package com.azvk.lastfmapi.lastFm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.azvk.lastfmapi.R;
import com.azvk.lastfmapi.lastFm.ui.IntroActivity;
import com.azvk.lastfmapi.lastFm.ui.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //running time of splash activity
        int splash_time_out = 3000;

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        SharedPreferences sharedPreferences = getSharedPreferences("appInfo", Context.MODE_PRIVATE);

        new Handler().postDelayed(() -> {
            //checking if sharedPreferences file exists
            if (sharedPreferences.contains("isSecondLaunch")) {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                finish();
                startActivity(i);
            } else {
                Intent i = new Intent(SplashActivity.this, IntroActivity.class);
                finish();
                startActivity(i);
            }
        }, splash_time_out);
    }
}

