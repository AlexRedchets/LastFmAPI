package com.azvk.lastfmapi.lastFm.ui;

import com.azvk.lastfmapi.model.Track;

import java.util.List;

public interface MainInterface {

    interface View{

        void onComplete(List<Track> data);

        void onError(String message);
    }

    interface Presenter{

        void fetchData();

        void fetchDataDB();
    }
}
