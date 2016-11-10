package com.azvk.lastfmapi;

import com.azvk.lastfmapi.model.Example;
import com.azvk.lastfmapi.model.Track;
import com.azvk.lastfmapi.model.Tracks;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface LastFmClient {
    @GET("?")
    Observable<Example> getRepos(
            @Query("method") String method,
            @Query("api_key") String api_key,
            @Query("format") String format
    );
}