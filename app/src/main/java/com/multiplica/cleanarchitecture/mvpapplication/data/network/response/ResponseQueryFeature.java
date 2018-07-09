package com.multiplica.cleanarchitecture.mvpapplication.data.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 26/06/18.
 */

public class ResponseQueryFeature {

    @SerializedName("type")
    @Expose
    String type;

    @SerializedName("properties")
    @Expose
    ResponseQueryFeatureProperties properties;

    @SerializedName("geometry")
    @Expose
    ResponseQueryFeatureGeometry geometry;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ResponseQueryFeatureProperties getProperties() {
        return properties;
    }

    public void setProperties(ResponseQueryFeatureProperties properties) {
        this.properties = properties;
    }

    public ResponseQueryFeatureGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(ResponseQueryFeatureGeometry geometry) {
        this.geometry = geometry;
    }
}
