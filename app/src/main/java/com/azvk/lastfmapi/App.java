package com.azvk.lastfmapi;

import android.app.Application;

import com.azvk.lastfmapi.dependencies.AppModule;
import com.azvk.lastfmapi.dependencies.DaggerNetComponent;
import com.azvk.lastfmapi.dependencies.NetComponent;
import com.azvk.lastfmapi.dependencies.NetModule;
import com.azvk.lastfmapi.lastFm.build.MainComponent;
import com.azvk.lastfmapi.lastFm.build.MainModule;
import com.azvk.lastfmapi.lastFm.ui.MainInterface;

public class App extends Application {

    private NetComponent netComponent;
    private MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        netComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("http://gateway.marvel.com/v1/public/"))
                .build();
    }

    public MainComponent getMainComponent(MainInterface.View view){
        mainComponent = netComponent.plus(new MainModule(view));
        return mainComponent;
    }

    public void releaseMainComponent(){
        mainComponent = null;
    }

    public NetComponent getNetComponent(){
        return netComponent;
    }
}
