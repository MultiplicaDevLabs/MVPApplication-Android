package com.multiplica.cleanarchitecture.mvpapplication.data.network;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 26/06/18.
 */
public class RetrofitClient {

    private static Retrofit retrofit = null;


    public static Retrofit getRetrofitClient(){

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(EndPointConf.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGsonConverter()))
                    .client(getOkHttpClient())
                    .build();
        }

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
