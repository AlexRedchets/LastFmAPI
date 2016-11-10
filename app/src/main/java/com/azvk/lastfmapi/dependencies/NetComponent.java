package com.azvk.lastfmapi.dependencies;

import com.azvk.lastfmapi.lastFm.build.MainComponent;
import com.azvk.lastfmapi.lastFm.build.MainModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    MainComponent plus (MainModule module);
}
