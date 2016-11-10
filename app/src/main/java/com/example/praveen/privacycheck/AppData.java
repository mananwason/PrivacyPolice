package com.example.praveen.privacycheck;

import android.graphics.drawable.Drawable;

/**
 * Created by Praveen on 11/10/2016.
 */
public class AppData {
    String appName, packageName, versionName;
    int versionCode = 0;
    Drawable icon;

    public AppData(String appName, String packageName, String versionName, int versionCode, Drawable icon) {
        this.appName = appName;
        this.packageName = packageName;
        this.versionName = versionName;
        this.versionCode = versionCode;
        this.icon = icon;
    }

    public String getAppName() {

        return appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getVersionName() {
        return versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setAppName(String appName) {

        this.appName = appName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
