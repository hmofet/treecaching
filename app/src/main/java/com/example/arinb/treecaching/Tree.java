package com.example.arinb.treecaching;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by arinb on 2016-10-24.
 */

public class Tree implements Parcelable {
    private String webcode;
    private String location;
    private String treeCode;
    private String commonName;
    private String scientificName;
    private double dbh;
    private double cw1;
    private double cw2;
    private double height;
    private int year;
    private String comment;
    private double utmn;
    private double utme;
    private double latitude;
    private double longitude;
    private double kgc;
    private double kgco2;
    private String webpages;
    private String treeSurroundings;


    @Override
    public void writeToParcel(Parcel out, int flags){
        out.writeString(webcode);
        out.writeString(location);
        out.writeString(treeCode);
        out.writeString(commonName);
        out.writeString(scientificName);
        out.writeDouble(dbh);
        out.writeDouble(cw1);
        out.writeDouble(cw2);
        out.writeDouble(height);
        out.writeInt(year);
        out.writeString(comment);
        out.writeDouble(utmn);
        out.writeDouble(utme);
        out.writeDouble(latitude);
        out.writeDouble(longitude);
        out.writeDouble(kgc);
        out.writeDouble(kgco2);
        out.writeString(webpages);
        out.writeString(treeSurroundings);
    }

    private Tree(Parcel in){
        webcode = in.readString();
        location = in.readString();
        treeCode = in.readString();
        commonName = in.readString();
        scientificName = in.readString();
        dbh = in.readDouble();
        cw1 = in.readDouble();
        cw2 = in.readDouble();
        height = in.readDouble();
        year = in.readInt();
        comment = in.readString();
        utmn = in.readDouble();
        utme = in.readDouble();
        latitude = in.readDouble();
        longitude = in.readDouble();
        kgc = in.readDouble();
        kgco2 = in.readDouble();
        webpages = in.readString();
        treeSurroundings = in.readString();
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Parcelable.Creator<Tree> CREATOR
            = new Parcelable.Creator<Tree>(){

        @Override
        public Tree createFromParcel(Parcel in){
            return new Tree(in);
        }

        @Override
        public Tree[] newArray(int size){
            return new Tree[size];
        }
    };


    public Tree(String webcode, String location, String treeCode, String commonName, String scientificName,
                double dbh, double cw1, double cw2, double height, int year, String comment, double utmn,
                double utme, double latitude, double longitude, double kgc, double kgco2,
                String webpages, String treeSurroundings) {
        this.webcode = webcode;
        this.location = location;
        this.treeCode = treeCode;
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.dbh = dbh;
        this.cw1 = cw1;
        this.cw2 = cw2;
        this.height = height;
        this.year = year;
        this.comment = comment;
        this.utmn = utmn;
        this.utme = utme;
        this.latitude = latitude;
        this.longitude = longitude;
        this.kgc = kgc;
        this.kgco2 = kgco2;
        this.webpages = webpages;
        this.treeSurroundings = treeSurroundings;
    }

    public String getWebcode() {
        return webcode;
    }

    public void setWebcode(String webcode) {
        this.webcode = webcode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTreeCode() {
        return treeCode;
    }

    public void setTreeCode(String treeCode) {
        this.treeCode = treeCode;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public double getDbh() {
        return dbh;
    }

    public void setDbh(double dbh) {
        this.dbh = dbh;
    }

    public double getCw1() {
        return cw1;
    }

    public void setCw1(double cw1) {
        this.cw1 = cw1;
    }

    public double getCw2() {
        return cw2;
    }

    public void setCw2(double cw2) {
        this.cw2 = cw2;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getUtmn() {
        return utmn;
    }

    public void setUtmn(double utmn) {
        this.utmn = utmn;
    }

    public double getUtme() {
        return utme;
    }

    public void setUtme(double utme) {
        this.utme = utme;
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

    public double getKgc() {
        return kgc;
    }

    public void setKgc(double kgc) {
        this.kgc = kgc;
    }

    public double getKgco2() {
        return kgco2;
    }

    public void setKgco2(double kgco2) {
        this.kgco2 = kgco2;
    }

    public String getWebpages() {
        return webpages;
    }

    public void setWebpages(String webpages) {
        this.webpages = webpages;
    }

    public String getTreeSurroundings() {
        return treeSurroundings;
    }

    public void setTreeSurroundings(String treeSurroundings) {
        this.treeSurroundings = treeSurroundings;
    }


}
