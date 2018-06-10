package com.brian.brianmvvm;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.brian.brianmvvm.databinding.ActivityDetailBinding;
import com.brian.brianmvvm.domain.Earthquake;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_EARTHQUAKE = "extra_earth_quake";

    private ActivityDetailBinding detailBinding;

    private Earthquake mEarthquake;

    public static Intent startIntent(Context context, Earthquake earthquake) {
        return new Intent(context, DetailActivity.class).putExtra(EXTRA_EARTHQUAKE, earthquake);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        detailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        mEarthquake = getIntent().getParcelableExtra(EXTRA_EARTHQUAKE);

        detailBinding.setEarthquakeDetail(mEarthquake);

    }
}
