package com.azvk.lastfmapi.lastFm.build;

import com.azvk.lastfmapi.lastFm.ui.MainInterface;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private MainInterface.TopView view;

    public MainModule(MainInterface.TopView view) {
        this.view = view;
    }

    @Provides
    @CustomScope
    MainInterface.TopView getView(){
        return view;
    }
}
