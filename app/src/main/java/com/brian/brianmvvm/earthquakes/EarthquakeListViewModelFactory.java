package com.brian.brianmvvm.earthquakes;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

/**
 * @author Brian
 * @date: 6/5/18
 */


public class EarthquakeListViewModelFactory implements ViewModelProvider.Factory {

    private EarthquakeInteractor earthquakeInteractor;

    public EarthquakeListViewModelFactory(EarthquakeInteractor earthquakeInteractor) {
        this.earthquakeInteractor = earthquakeInteractor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(EarthquakeListViewModel.class)) {
            return (T) new EarthquakeListViewModel(earthquakeInteractor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
