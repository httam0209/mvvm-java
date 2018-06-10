package com.brian.brianmvvm;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.brian.brianmvvm.domain.Earthquake;
import com.brian.brianmvvm.earthquakes.EarthquakeInteractor;
import com.brian.brianmvvm.earthquakes.EarthquakeListViewModel;
import com.brian.brianmvvm.earthquakes.EarthquakeListViewModelFactory;
import com.brian.brianmvvm.earthquakes.EarthquakesListAdapter;
import com.brian.brianmvvm.network.ApiClientService;
import com.brian.brianmvvm.network.EarthquakeApi;
import com.brian.brianmvvm.network.EarthquakeService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    EarthquakeListViewModelFactory viewModelFactory;

    private EarthquakeListViewModel viewModel;

    @BindView(R.id.loading_indicator)
    ProgressBar loadingIndicator;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerEarths;

    private List<Earthquake> mEarthquakes;

    private EarthquakesListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        viewModelFactory = Injection.provideViewModelFactory(this);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(EarthquakeListViewModel.class);

        mRecyclerEarths.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerEarths.setHasFixedSize(true);

        mEarthquakes = new ArrayList<>();
        mAdapter = new EarthquakesListAdapter(mEarthquakes);

        mRecyclerEarths.setAdapter(mAdapter);


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
        loadingIndicator.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    private void processError(Throwable error) {
        Toast.makeText(this, "Error " + error, Toast.LENGTH_LONG).show();
        //deal with errors
    }
}
