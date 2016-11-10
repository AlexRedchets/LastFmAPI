package com.azvk.lastfmapi.lastFm.build;

import com.azvk.lastfmapi.lastFm.ui.MainInterface;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private MainInterface.View view;

    public MainModule(MainInterface.View view) {
        this.view = view;
    }

    @Provides
    @CustomScope
    MainInterface.View getView(){
        return view;
    }
}
