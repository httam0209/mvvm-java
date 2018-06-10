package com.brian.brianmvvm;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.brian.brianmvvm.callback.EarthQuakeItemCallback;
import com.brian.brianmvvm.databinding.ActivityMainBinding;
import com.brian.brianmvvm.domain.Earthquake;
import com.brian.brianmvvm.earthquakes.EarthquakeListViewModel;
import com.brian.brianmvvm.earthquakes.EarthquakeListViewModelFactory;
import com.brian.brianmvvm.earthquakes.EarthquakesListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements EarthQuakeItemCallback {

    EarthquakeListViewModelFactory viewModelFactory;

    private EarthquakeListViewModel viewModel;

    private List<Earthquake> mEarthquakes;

    private EarthquakesListAdapter mAdapter;

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModelFactory = Injection.provideViewModelFactory(this);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(EarthquakeListViewModel.class);

        mEarthquakes = new ArrayList<>();
        mAdapter = new EarthquakesListAdapter(mEarthquakes, this);

        mainBinding.recyclerView.setHasFixedSize(true);
        mainBinding.recyclerView.setAdapter(mAdapter);

        viewModel.loadEarthQuakeList();

        observeLoadingStatus();

        observeResponse();

        observeError();
    }


    private void observeLoadingStatus() {
        viewModel.getLoadingStatus().observe(this, this::processLoadingStatus);
    }

    private void observeResponse() {
        viewModel.earthQuakes().observe(this, earthquakes -> {
            mEarthquakes.clear();
            mEarthquakes.addAll(earthquakes);

            mAdapter.notifyDataSetChanged();
        });
    }

    private void observeError() {
        viewModel.getError().observe(this, this::processError);
    }

    private void processLoadingStatus(boolean isLoading) {
        mainBinding.loadingIndicator.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    private void processError(Throwable error) {
        Toast.makeText(this, "Error " + error, Toast.LENGTH_LONG).show();
        //deal with errors
    }

    @Override
    public void onItemClick(Earthquake earthquake) {
        startActivity(DetailActivity.startIntent(this, earthquake));
    }
}
