package com.multiplica.cleanarchitecture.mvpapplication.domain.interactor.main;

import android.util.Log;

import com.multiplica.cleanarchitecture.mvpapplication.Application;
import com.multiplica.cleanarchitecture.mvpapplication.data.network.IWebServices;
import com.multiplica.cleanarchitecture.mvpapplication.data.network.response.ResponseQuery;
import com.multiplica.cleanarchitecture.mvpapplication.data.network.response.ResponseQueryFeature;
import com.multiplica.cleanarchitecture.mvpapplication.data.repository.implementation.EarthquakeDataRepositoryImpl;
import com.multiplica.cleanarchitecture.mvpapplication.domain.base.BaseResponseList;
import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.EarthquakeEntity;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by user on 13/07/18.
 */

public class ListInteractorImpl implements IListInteractor {

    EarthquakeDataRepositoryImpl repository = EarthquakeDataRepositoryImpl.init();

    @Inject
    Retrofit retrofit;

    public ListInteractorImpl(){
        Application.getInstance().getComponents().inject(this);
    }

    @Override
    public void getEarthquakeList(String startDate, String endDate, final BaseResponseList<EarthquakeEntity> callback) {
        //Retrofit retrofit = RetrofitClient.getRetrofitClient();
        Observable<ResponseQuery> request = retrofit.create(IWebServices.class).query("geojson",startDate,
                endDate,15);
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

                        int id = 1;

                        for (ResponseQueryFeature feature: features) {
                            EarthquakeEntity earthquake = new EarthquakeEntity();

                            earthquake.setId(id);
                            earthquake.setPlace(feature.getProperties().getPlace());
                            earthquake.setTime(feature.getProperties().getTime());
                            earthquake.setTitle(feature.getProperties().getTitle());
                            earthquake.setLatitude(feature.getGeometry().getCoordinates()[0]);
                            earthquake.setLongitude(feature.getGeometry().getCoordinates()[1]);
                            earthquakes.add(earthquake);

                            id++;
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.i("FAILURE: ",e.getMessage());
                        callback.onError(new Exception(),e.getMessage());
                    }
                    @Override
                    public void onComplete() {
                        Log.i("COMPLETE: ","READY!!!");

                        repository.create(earthquakes);
                        callback.onSuccess(earthquakes);
                    }
                });
    }

    @Override
    public void getLocalEarthquakeList(BaseResponseList<EarthquakeEntity> callback) {

        ArrayList<EarthquakeEntity> earthquakes = repository.getAll();
        callback.onSuccess(earthquakes);

    }
}
