package com.brian.brianmvvm;

import android.content.Context;

import com.brian.brianmvvm.earthquakes.EarthquakeInteractor;
import com.brian.brianmvvm.earthquakes.EarthquakeListViewModelFactory;
import com.brian.brianmvvm.network.ApiClientService;
import com.brian.brianmvvm.network.EarthquakeApi;
import com.brian.brianmvvm.network.EarthquakeService;

/**
 * @author Brian
 * @date: 6/9/18
 */


public final class Injection {

    public static EarthquakeApi provideEarthquakApi(Context context) {
        return ApiClientService.getInstance().createService(context)
                .create(EarthquakeApi.class);
    }

    public static EarthquakeInteractor provideEarthquakeInteractor(Context context) {
        return new EarthquakeInteractor(new EarthquakeService(provideEarthquakApi(context)));
    }

    public static EarthquakeListViewModelFactory provideViewModelFactory(Context context) {
        return new EarthquakeListViewModelFactory(provideEarthquakeInteractor(context));
    }
}
