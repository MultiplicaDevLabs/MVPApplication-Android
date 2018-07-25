package com.multiplica.cleanarchitecture.mvpapplication.domain.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.multiplica.cleanarchitecture.mvpapplication.data.network.EndPointConf;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 20/07/18.
 */

@Module
public class RetrofitModule {

    private Retrofit retrofit = null;
    public RetrofitModule(){
        initRetrofit();
    }

    private void initRetrofit(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(EndPointConf.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGsonConverter()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(getOkHttpClient())
                    .build();
        }
    }

    @Provides
    @Singleton
    public Retrofit getRetrofitClient(){
        return retrofit;
    }

    private static Gson getGsonConverter(){
        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.create();
        return gson;
    }

    private static OkHttpClient getOkHttpClient(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();

        return okHttpClient;
    }
}
