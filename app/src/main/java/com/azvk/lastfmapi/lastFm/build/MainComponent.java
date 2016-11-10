package com.azvk.lastfmapi.lastFm.build;

import com.azvk.lastfmapi.lastFm.ui.MainActivity;
import com.azvk.lastfmapi.lastFm.ui.MainFragment;

import dagger.Subcomponent;

@CustomScope
@Subcomponent(modules = MainModule.class)
public interface MainComponent {
    MainFragment inject (MainFragment fragment);
}
