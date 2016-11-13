package com.azvk.lastfmapi.lastFm.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.azvk.lastfmapi.R;
import com.azvk.lastfmapi.lastFm.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.layout_intro_dots)
    LinearLayout dotsLayout;
    @BindView(R.id.intro_next_button)
    Button nextButton;
    @BindView(R.id.intro_skip_button)
    Button skipButton;

    private ViewPagerAdapter viewPagerAdapter;
    private int[] layouts;
    private TextView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.activity_intro);

        ButterKnife.bind(this);

        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.intro_slide_1,
                R.layout.intro_slide_2,
                R.layout.intro_slide_3};

        initDots(0);

        // making notification bar transparent
        changeStatusBarColor();

        viewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), layouts);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                initDots(position);

                // changing the next button text 'NEXT' / 'GOT IT'
                if (position == layouts.length - 1) {
                    // last page change button text to GOT IT
                    nextButton.setText(getString(R.string.start));
                    skipButton.setVisibility(View.GONE);
                } else {
                    // still pages are left
                    nextButton.setText(getString(R.string.button_intro_next));
                    skipButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        
        skipButton.setOnClickListener(v -> {
            callMainActivity();         
        });
        nextButton.setOnClickListener(v -> {
            // checking for last page
            // if last page home screen will be launched
            int current = getItem(+1);
            if (current < layouts.length) {
                // move to next screen
                viewPager.setCurrentItem(current);
            } else {
                callMainActivity();
            }
        });
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void callMainActivity() {
        SharedPreferences.Editor editor = getSharedPreferences("appInfo", MODE_PRIVATE).edit();
                editor.putString("isSecondLaunch", "true");
                editor.apply();

        startActivity(new Intent(IntroActivity.this, MainActivity.class));
        finish();
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void initDots(int currentPage) {
        dots = new TextView[layouts.length];

        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.dot_inactive));
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0){
            dots[currentPage].setTextColor(getResources().getColor(R.color.dot_active));
        }
    }
}
