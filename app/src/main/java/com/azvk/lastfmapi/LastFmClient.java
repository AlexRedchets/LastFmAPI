package com.azvk.lastfmapi;

import com.azvk.lastfmapi.model.Track;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface LastFmClient {
    @GET("?")
    Observable<Track> getRepos(
            @Query("method") String method,
            @Query("api_key") String api_key,
            @Query("format") String format
    );
}