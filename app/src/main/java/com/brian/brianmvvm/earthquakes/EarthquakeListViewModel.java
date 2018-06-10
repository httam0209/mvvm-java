package com.brian.brianmvvm.earthquakes;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.brian.brianmvvm.domain.Earthquake;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Brian
 * @date: 6/5/18
 */


public class EarthquakeListViewModel extends ViewModel {

    private final EarthquakeInteractor mEarthquakeInteractor;

    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<List<Earthquake>> earthquakeList = new MutableLiveData<>();
    private final MutableLiveData<Throwable> error = new MutableLiveData<>();

    public EarthquakeListViewModel(EarthquakeInteractor earthquakeInteractor) {
        this.mEarthquakeInteractor = earthquakeInteractor;
    }

    public LiveData<Boolean> getLoadingStatus() {
        return isLoading;
    }

    public LiveData<List<Earthquake>> earthQuakes() {
        return earthquakeList;
    }

    public LiveData<Throwable> getError() {
        return error;
    }

    public void loadEarthQuakeList() {
        mCompositeDisposable.add(
                mEarthquakeInteractor.loadEarthquakeList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(s -> isLoading.setValue(true))
                        .doAfterTerminate(() -> isLoading.setValue(false))
                       .subscribe(earthquakeList::setValue, error::setValue)
        );
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.clear();
    }
}
