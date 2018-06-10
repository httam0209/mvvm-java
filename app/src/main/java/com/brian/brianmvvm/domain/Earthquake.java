package com.brian.brianmvvm.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Brian
 * @date: 6/5/18
 */
public class Earthquake implements Parcelable {

    private Double magnitude;
    private String place;
    private String time;
    private String url;
    private String detail;
    private String status;
    private String type;
    private String title;

    public Double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Double magnitude) {
        this.magnitude = magnitude;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.magnitude);
        dest.writeString(this.place);
        dest.writeString(this.time);
        dest.writeString(this.url);
        dest.writeString(this.detail);
        dest.writeString(this.status);
        dest.writeString(this.type);
        dest.writeString(this.title);
    }

    public Earthquake() {
    }

    protected Earthquake(Parcel in) {
        this.magnitude = (Double) in.readValue(Double.class.getClassLoader());
        this.place = in.readString();
        this.time = in.readString();
        this.url = in.readString();
        this.detail = in.readString();
        this.status = in.readString();
        this.type = in.readString();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<Earthquake> CREATOR = new Parcelable.Creator<Earthquake>() {
        @Override
        public Earthquake createFromParcel(Parcel source) {
            return new Earthquake(source);
        }

        @Override
        public Earthquake[] newArray(int size) {
            return new Earthquake[size];
        }
    };
}
