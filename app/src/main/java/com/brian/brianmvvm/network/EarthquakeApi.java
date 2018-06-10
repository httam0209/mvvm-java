package com.brian.brianmvvm.network;


import com.brian.brianmvvm.data.EarthQuakeListResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Brian
 * @date: 6/5/18
 */
public interface EarthquakeApi {
    //This class is simply a retrofit interface class

    @GET("/fdsnws/event/1/query")
    Single<EarthQuakeListResponse> getEarthquakes(
            @Query("format") String format,
            @Query("starttime") String startDate,
            @Query("endtime") String endDate,
            @Query("minmagnitude") String magnitude
    );
}
