package com.brian.brianmvvm.earthquakes;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.brian.brianmvvm.R;
import com.brian.brianmvvm.callback.EarthQuakeItemCallback;
import com.brian.brianmvvm.databinding.EarthquakeItemBinding;
import com.brian.brianmvvm.domain.Earthquake;

import java.util.List;

/**
 * @author Brian
 * @date: 6/5/18
 */

public class EarthquakesListAdapter extends RecyclerView.Adapter<EarthquakesListAdapter.EarthquakeViewHolder> {

    private List<Earthquake> list;

    private EarthQuakeItemCallback earthQuakeItemCallback;

    public EarthquakesListAdapter(List<Earthquake> list, EarthQuakeItemCallback earthQuakeItemCallback) {
        this.list = list;
        this.earthQuakeItemCallback = earthQuakeItemCallback;
        notifyDataSetChanged();
    }

    @Override
    public EarthquakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        EarthquakeItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.earthquake_item, parent, false);
        itemBinding.setCallback(earthQuakeItemCallback);

        return new EarthquakeViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull EarthquakeViewHolder holder, int position) {
        holder.earthquakeItemBinding.setEarthquake(list.get(position));
        holder.earthquakeItemBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class EarthquakeViewHolder extends RecyclerView.ViewHolder {

        final EarthquakeItemBinding earthquakeItemBinding;

        EarthquakeViewHolder(EarthquakeItemBinding earthquakeItemBinding) {
            super(earthquakeItemBinding.getRoot());
            this.earthquakeItemBinding = earthquakeItemBinding;
        }
    }
}
