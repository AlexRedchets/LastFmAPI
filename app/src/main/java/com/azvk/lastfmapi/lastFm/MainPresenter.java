package com.azvk.lastfmapi.lastFm;

import com.azvk.lastfmapi.lastFm.ui.MainInterface;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by azvk on 2016-11-10.
 */

public class MainPresenter implements MainInterface.Presenter {

    private Retrofit retrofit;
    private MainInterface.View view;

    private static final String TAG = MainPresenter.class.getSimpleName();

    @Inject
    public MainPresenter(Retrofit retrofit, MainInterface.View view) {
        this.retrofit = retrofit;
        this.view = view;
    }


    @Override
    public void fetchData() {

    }

    @Override
    public void fetchDataDB() {

    }
}
