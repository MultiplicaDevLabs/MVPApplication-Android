package com.multiplica.cleanarchitecture.mvpapplication.domain.interactor.main;

import android.util.Log;

import com.multiplica.cleanarchitecture.mvpapplication.data.network.IWebServices;
import com.multiplica.cleanarchitecture.mvpapplication.data.network.RetrofitClient;
import com.multiplica.cleanarchitecture.mvpapplication.data.network.response.ResponseQuery;
import com.multiplica.cleanarchitecture.mvpapplication.data.network.response.ResponseQueryFeature;
import com.multiplica.cleanarchitecture.mvpapplication.domain.base.BaseResponseList;
import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.EarthquakeEntity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by user on 13/07/18.
 */

public class ListInteractorImpl implements IListInteractor {
    @Override
    public void getEarthquakeList(final BaseResponseList<EarthquakeEntity> callback) {


        Retrofit retrofit = RetrofitClient.getRetrofitClient();
        Call<ResponseQuery> request = retrofit.create(IWebServices.class).query("geojson","2018-06-27",
                "2018-06-28",10);


        request.enqueue(new Callback<ResponseQuery>() {
            @Override
            public void onResponse(Call<ResponseQuery> call, Response<ResponseQuery> response) {
                Log.i("RESPONSE: ",response.message());

                ResponseQuery  responseQuery = response.body();

                ArrayList<ResponseQueryFeature> features = responseQuery.getFeatures();

                ArrayList<EarthquakeEntity> earthquakes = new ArrayList<EarthquakeEntity>();

                for (ResponseQueryFeature feature: features) {
                    EarthquakeEntity earthquake = new EarthquakeEntity();

                    earthquake.setPlace(feature.getProperties().getPlace());
                    earthquake.setTime(feature.getProperties().getTime());
                    earthquake.setTitle(feature.getProperties().getTitle());
                    earthquake.setLatitude(feature.getGeometry().getCoordinates()[0]);
                    earthquake.setLongitude(feature.getGeometry().getCoordinates()[1]);

                    earthquakes.add(earthquake);
                }

                callback.onSuccess(earthquakes);
            }

            @Override
            public void onFailure(Call<ResponseQuery> call, Throwable t) {
                Log.i("FAILURE: ",t.getMessage());
                callback.onError(new Exception(),t.getMessage());
            }
        });
    }
}
