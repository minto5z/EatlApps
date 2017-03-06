package com.blogspot.larn4android.eatlapps.modelclass;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Minto on 11/14/2016.
 */
public class apartment implements Parcelable{


    private String district;
    private String thana;
    private String rentsale;
    private String name;
    private String phone;
    private String avilablefrom;
    private String details;
    private String picture;
    private String taka;
    private String squrefit;

    public apartment() {
    }

    public apartment(String district, String thana, String rentsale, String name, String phone, String avilablefrom, String details, String picture, String taka, String squrefit) {
        this.district = district;
        this.thana = thana;
        this.rentsale = rentsale;
        this.name = name;
        this.phone = phone;
        this.avilablefrom = avilablefrom;
        this.details = details;
        this.picture = picture;
        this.taka = taka;
        this.squrefit = squrefit;
    }

    public apartment(Parcel in) {

        String[] data = new String[10];

        in.readStringArray(data);
        this.district = data[0];
        this.thana = data[1];
        this.rentsale = data[2];
        this.name =data[3];
        this.phone = data[4];
        this.avilablefrom = data[5];
        this.details = data[6];
        this.picture = data[7];
        this.taka = data[8];
        this.squrefit = data[9];
    }
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getThana() {
        return thana;
    }

    public void setThana(String thana) {
        this.thana = thana;
    }

    public String getRentsale() {
        return rentsale;
    }

    public void setRentsale(String rentsale) {
        this.rentsale = rentsale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvilablefrom() {
        return avilablefrom;
    }

    public void setAvilablefrom(String avilablefrom) {
        this.avilablefrom = avilablefrom;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
    public String getSqurefit() {
        return squrefit;
    }

    public void setSqurefit(String squrefit) {
        this.squrefit = squrefit;
    }

    public String getTaka() {
        return taka;
    }

    public void setTaka(String taka) {
        this.taka = taka;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeStringArray(new String[] {
                this.district,
                this.thana,
                this.rentsale,
                this.name,
                this.phone,
                this.avilablefrom,
                this.details,
                this.picture,
                this.taka,
                this.squrefit
        });
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public apartment createFromParcel(Parcel in) {
            return new apartment(in);
        }

        public apartment[] newArray(int size) {
            return new apartment[size];
        }
    };
}
