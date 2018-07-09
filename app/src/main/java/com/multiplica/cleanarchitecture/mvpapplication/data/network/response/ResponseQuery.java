package com.multiplica.cleanarchitecture.mvpapplication.data.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by user on 26/06/18.
 */

public class ResponseQuery {

    @SerializedName("type")
    @Expose
    String type;

    @SerializedName("features")
    @Expose
    ArrayList<ResponseQueryFeature> features;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<ResponseQueryFeature> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<ResponseQueryFeature> features) {
        this.features = features;
    }
}
