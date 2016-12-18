package com.example.arinb.treecaching;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by arinb on 2016-11-08.
 */

public class Species implements Parcelable {

    private String commonName;
    private String scientificName;
    private String otherNames;
    private String leaf;
    private String flower;
    private String fruit;
    private String twig;
    private String bark;
    private String wood;
    private ArrayList<String> facts;
    private ArrayList<String> reference;
    private ArrayList<String> images;
    private ArrayList<String> imageDescription;
    private String spCode;

    @Override
    public void writeToParcel(Parcel out, int flags){
        out.writeString(commonName);
        out.writeString(scientificName);
        out.writeString(otherNames);
        out.writeString(leaf);
        out.writeString(flower);
        out.writeString(fruit);
        out.writeString(twig);
        out.writeString(bark);
        out.writeString(wood);
        out.writeStringList(facts);
        out.writeStringList(reference);
        out.writeStringList(images);
        out.writeStringList(imageDescription);
        out.writeString(spCode);
    }

    private Species(Parcel in){
        commonName = in.readString();
        scientificName = in.readString();
        otherNames = in.readString();
        leaf = in.readString();
        flower = in.readString();
        fruit = in.readString();
        twig = in.readString();
        bark = in.readString();
        wood = in.readString();

        facts = in.createStringArrayList();
        reference = in.createStringArrayList();
        images = in.createStringArrayList();
        imageDescription = in.createStringArrayList();

        spCode = in.readString();
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Parcelable.Creator<Species> CREATOR
            = new Parcelable.Creator<Species>() {

        @Override
        public Species createFromParcel(Parcel in){
            return new Species(in);
        }

        @Override
        public Species[] newArray(int size){
            return new Species[size];
        }
    };


    public Species(String commonName, String scientificName, String otherNames, String leaf,
                       String flower, String fruit, String twig, String bark, String wood,
                       ArrayList<String> facts, ArrayList<String> reference,
                       ArrayList<String> images, ArrayList<String> imageDescription, String spCode) {
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.otherNames = otherNames;
        this.leaf = leaf;
        this.flower = flower;
        this.fruit = fruit;
        this.twig = twig;
        this.bark = bark;
        this.wood = wood;
        this.facts = facts;
        this.reference = reference;
        this.images = images;
        this.imageDescription = imageDescription;
        this.spCode = spCode;
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

    public String getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(String otherNames) {
        this.otherNames = otherNames;
    }

    public String getLeaf() {
        return leaf;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf;
    }

    public String getFlower() {
        return flower;
    }

    public void setFlower(String flower) {
        this.flower = flower;
    }

    public String getFruit() {
        return fruit;
    }

    public void setFruit(String fruit) {
        this.fruit = fruit;
    }

    public String getTwig() {
        return twig;
    }

    public void setTwig(String twig) {
        this.twig = twig;
    }

    public String getBark() {
        return bark;
    }

    public void setBark(String bark) {
        this.bark = bark;
    }

    public String getWood() {
        return wood;
    }

    public void setWood(String wood) {
        this.wood = wood;
    }

    public ArrayList<String> getFacts() {
        return facts;
    }

    public void setFacts(ArrayList<String> facts) {
        this.facts = facts;
    }

    public ArrayList<String> getReference() {
        return reference;
    }

    public void setReference(ArrayList<String> reference) {
        this.reference = reference;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public ArrayList<String> getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(ArrayList<String> imageDescription) {
        this.imageDescription = imageDescription;
    }

    public String getSpCode() {
        return spCode;
    }

    public void setSpCode(String spCode) {
        this.spCode = spCode;
    }

    public String toString(){
        return this.getCommonName().toString().toLowerCase();
    }
}
