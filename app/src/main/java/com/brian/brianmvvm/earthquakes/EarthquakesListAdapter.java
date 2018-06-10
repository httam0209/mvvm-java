package com.brian.brianmvvm.earthquakes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.brian.brianmvvm.R;
import com.brian.brianmvvm.domain.Earthquake;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Brian
 * @date: 6/5/18
 */

public class EarthquakesListAdapter extends RecyclerView.Adapter<EarthquakesListAdapter.EarthquakeViewHolder> {

    private List<Earthquake> list;

    public EarthquakesListAdapter(List<Earthquake> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public EarthquakeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.earthquake_item, parent, false);

        return new EarthquakeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EarthquakeViewHolder holder, int position) {
        Earthquake earthquake = list.get(position);

        holder.place.setText(earthquake.getPlace());
        holder.magnitude.setText(String.format(Locale.ENGLISH,"%4.3f" , earthquake.getMagnitude()));
        holder.time.setText(earthquake.getTime());
        holder.url.setText(earthquake.getUrl());
        holder.detail.setText(earthquake.getDetail());
        holder.status.setText(earthquake.getStatus());
        holder.type.setText(earthquake.getType());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class EarthquakeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.place)
        TextView place;

        @BindView(R.id.magnitude)
        TextView magnitude;

        @BindView(R.id.time)
        TextView time;

        @BindView(R.id.url)
        TextView url;

        @BindView(R.id.detail)
        TextView detail;

        @BindView(R.id.status)
        TextView status;

        @BindView(R.id.type)
        TextView type;

        EarthquakeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
