package com.azvk.lastfmapi.lastFm;

import android.util.Log;

import com.azvk.lastfmapi.LastFmClient;
import com.azvk.lastfmapi.lastFm.ui.MainInterface;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by azvk on 2016-11-10.
 */

public class MainPresenter implements MainInterface.TopPresenter {

    private Retrofit retrofit;
    private MainInterface.TopView view;

    private static final String TAG = MainPresenter.class.getSimpleName();

    @Inject
    public MainPresenter(Retrofit retrofit, MainInterface.TopView view) {
        this.retrofit = retrofit;
        this.view = view;
    }


    @Override
    public void fetchData() {
        Log.i(TAG, "fetchData started");

        retrofit.create(LastFmClient.class).getTopTracks("chart.gettoptracks", "6851ebd7175ef7d058c9c565c9bf5d84", "json")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(repos -> {
                            Log.e(TAG, "Successfully got data");

                            view.onComplete(repos.getTracks().getTrack());
                        },
                        throwable -> {
                            Log.e("Error", throwable.getMessage());
                            view.onError(throwable.getMessage());
                        });

    }

    @Override
    public void fetchDataDB() {

    }
}
