package com.azvk.lastfmapi.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Tracks {

   /* @SerializedName("track")
    @Expose*/
    private List<Track> track;
   /* @SerializedName("@attr")
    @Expose*/
    private Attr attr;

    public List<Track> getTrack() {
        return track;
    }

    public void setTrack(List<Track> track) {
        this.track = track;
    }

    public Attr getAttr() {
        return attr;
    }

    public void setAttr(Attr attr) {
        this.attr = attr;
    }

}

