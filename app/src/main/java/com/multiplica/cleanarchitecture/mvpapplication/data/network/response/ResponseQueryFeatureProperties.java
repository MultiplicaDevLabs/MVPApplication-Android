package com.multiplica.cleanarchitecture.mvpapplication.data.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 26/06/18.
 */

public class ResponseQueryFeatureProperties {

    @SerializedName("mag")
    @Expose
    double mag;

    @SerializedName("place")
    @Expose
    String place;

    @SerializedName("time")
    @Expose
    long time;

    @SerializedName("update")
    @Expose
    long update;

    @SerializedName("tsunami")
    @Expose
    double tsunami;

    @SerializedName("type")
    @Expose
    String type;

    @SerializedName("title")
    @Expose
    String title;

    public double getMag() {
        return mag;
    }

    public void setMag(double mag) {
        this.mag = mag;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getUpdate() {
        return update;
    }

    public void setUpdate(long update) {
        this.update = update;
    }

    public double getTsunami() {
        return tsunami;
    }

    public void setTsunami(double tsunami) {
        this.tsunami = tsunami;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
