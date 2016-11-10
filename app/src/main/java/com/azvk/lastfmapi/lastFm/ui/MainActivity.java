package com.azvk.lastfmapi.lastFm.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.azvk.lastfmapi.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            Log.e(TAG, "savedInstanceState == null");
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout, new MainFragment())
                    .commit();
        }
    }
}
