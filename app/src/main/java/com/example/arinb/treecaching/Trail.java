package com.example.arinb.treecaching;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arinb on 2016-10-24.
 */

public class Trail implements Parcelable {

    private String location;
    private double latitude;
    private double longitude;
    private ArrayList<Tree> trees;

    @Override
    public void writeToParcel(Parcel out, int flags){
        out.writeString(location);
        out.writeDouble(latitude);
        out.writeDouble(longitude);
        out.writeTypedList(trees);
    }

    private Trail(Parcel in){
        location = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        trees = new ArrayList();
        in.readTypedList(trees, Tree.CREATOR);

    }

    public int describeContents(){
        return 0;
    }

    public static final Parcelable.Creator<Trail> CREATOR =
                            new Parcelable.Creator<Trail>() {
        @Override
        public Trail createFromParcel(Parcel in){
            return new Trail(in);
        }

        @Override
        public Trail[] newArray(int size){
            return new Trail[size];
        }
    };


    public Trail(String location, double latitude, double longitude, ArrayList<Tree> trees) {
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.trees = trees;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public ArrayList<Tree> getTrees() {
        return trees;
    }

    public void setTrees(ArrayList<Tree> trees) {
        this.trees = trees;
    }
}
