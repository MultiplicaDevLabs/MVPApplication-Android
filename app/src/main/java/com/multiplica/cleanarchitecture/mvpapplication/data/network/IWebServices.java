package com.multiplica.cleanarchitecture.mvpapplication.data.network;

import com.multiplica.cleanarchitecture.mvpapplication.data.network.response.ResponseQuery;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by user on 26/06/18.
 */

public interface IWebServices {

    @GET(EndPointConf.QUERY)
    Observable<ResponseQuery> query(@Query("format") String format, @Query("starttime") String starttime,
                                          @Query("endtime") String endtime, @Query("limit") int limit);

}
