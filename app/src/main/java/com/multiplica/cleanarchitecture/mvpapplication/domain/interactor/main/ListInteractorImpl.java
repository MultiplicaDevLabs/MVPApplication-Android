package com.multiplica.cleanarchitecture.mvpapplication.domain.interactor.main;

import android.util.Log;

import com.multiplica.cleanarchitecture.mvpapplication.data.network.IWebServices;
import com.multiplica.cleanarchitecture.mvpapplication.data.network.RetrofitClient;
import com.multiplica.cleanarchitecture.mvpapplication.data.network.response.ResponseQuery;
import com.multiplica.cleanarchitecture.mvpapplication.data.network.response.ResponseQueryFeature;
import com.multiplica.cleanarchitecture.mvpapplication.domain.base.BaseResponseList;
import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.EarthquakeEntity;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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
        Observable<ResponseQuery> request = retrofit.create(IWebServices.class).query("geojson","2018-06-27",
                "2018-06-28",10);
        request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseQuery>() {

                    ArrayList<EarthquakeEntity> earthquakes = new ArrayList<EarthquakeEntity>();
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(ResponseQuery responseQuery) {
                        Log.i("RESPONSE: ",responseQuery.getType());
                        ArrayList<ResponseQueryFeature> features = responseQuery.getFeatures();

                        for (ResponseQueryFeature feature: features) {
                            EarthquakeEntity earthquake = new EarthquakeEntity();

                            earthquake.setPlace(feature.getProperties().getPlace());
                            earthquake.setTime(feature.getProperties().getTime());
                            earthquake.setTitle(feature.getProperties().getTitle());
                            earthquake.setLatitude(feature.getGeometry().getCoordinates()[0]);
                            earthquake.setLongitude(feature.getGeometry().getCoordinates()[1]);
                            earthquakes.add(earthquake);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.i("FAILUREs: ",e.getMessage());
                        callback.onError(new Exception(),e.getMessage());
                    }
                    @Override
                    public void onComplete() {
                        Log.i("COMPLETE: ","READY!!!");
                        callback.onSuccess(earthquakes);
                    }
                });
    }
}
