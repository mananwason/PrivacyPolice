package com.example.praveen.privacycheck.Models;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Praveen on 11/10/2016.
 */
public class AppData implements Parcelable, Comparable<AppData> {
    private String appName, packageName, versionName;
    private int versionCode = 0;
    private Drawable icon;
    private String[] permissions;
    private double score;

    public AppData(String appName, String packageName, String versionName, int versionCode, Drawable icon, String[] permissions, double score) {
        this.appName = appName;
        this.packageName = packageName;
        this.versionName = versionName;
        this.versionCode = versionCode;
        this.icon = icon;
        this.permissions = permissions;
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    protected AppData(Parcel in) {
        appName = in.readString();
        packageName = in.readString();
        versionName = in.readString();
        versionCode = in.readInt();
        permissions = in.createStringArray();
        score = in.readDouble();
    }

    public static final Creator<AppData> CREATOR = new Creator<AppData>() {
        @Override
        public AppData createFromParcel(Parcel in) {
            return new AppData(in);
        }

        @Override
        public AppData[] newArray(int size) {
            return new AppData[size];
        }
    };

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(appName);
        parcel.writeString(packageName);
        parcel.writeString(versionName);
        parcel.writeInt(versionCode);
        parcel.writeStringArray(permissions);
        parcel.writeDouble(score);
    }

    @Override
    public int compareTo(AppData appData) {
        return appName.toLowerCase().compareTo(appData.getAppName().toLowerCase());
    }
}