package com.brian.brianmvvm.earthquakes;


import com.brian.brianmvvm.domain.Earthquake;
import com.brian.brianmvvm.network.EarthquakeService;

import java.util.List;

import io.reactivex.Single;


/**
 * @author Brian
 * @date: 6/5/18
 */

public class EarthquakeInteractor {
    //This class is where Business Logic sits

    private final EarthquakeService earthquakeService;

    public EarthquakeInteractor(EarthquakeService earthquakeService) {
        this.earthquakeService = earthquakeService;
    }

    Single<List<Earthquake>> loadEarthquakeList() {
        //show examples of business logic manipulations here
        return earthquakeService.getEarthquakes();
    }
}