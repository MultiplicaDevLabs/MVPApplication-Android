package com.multiplica.cleanarchitecture.mvpapplication.data.model.item;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by user on 18/07/18.
 */

public class EarthquakeRealm extends RealmObject {

    @PrimaryKey
    private int id;
    private String place;
    private long time;
    private String title;
    private double latitude;
    private double longitude;

    public static final String ID = "id";
    public static final String PLACE = "place";
    public static final String TIME = "time";
    public static final String TITLE = "title";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
