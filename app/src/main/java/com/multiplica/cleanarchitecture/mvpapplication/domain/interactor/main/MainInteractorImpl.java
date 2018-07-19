package com.multiplica.cleanarchitecture.mvpapplication.domain.interactor.main;

import com.multiplica.cleanarchitecture.mvpapplication.domain.base.BaseResponseList;
import com.multiplica.cleanarchitecture.mvpapplication.domain.entity.EarthquakeEntity;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;


/**
 * Created by user on 27/06/18.
 */

public class MainInteractorImpl implements IMainInteractor{
    @Override
    public void getEarthquakeList(final BaseResponseList<EarthquakeEntity> callback) {

        Observable integerObservable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(20);
                emitter.onNext(313);
                emitter.onNext(44);
                emitter.onComplete();
            }
        });

        Observer integerObserver = new Observer() {

            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSuscribe : "+d.isDisposed());
            }

            @Override
            public void onNext(Object o) {
                System.out.println("onNext : "+o);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError : "+e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete : COMPLETE!!!");
            }
        };

        integerObservable.subscribe(integerObserver);

    }
}
