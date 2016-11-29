package com.example.praveen.privacycheck.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.praveen.privacycheck.Fragments.DangerousPermissionsFragments;
import com.example.praveen.privacycheck.Fragments.PermissionsFragments;
import com.example.praveen.privacycheck.Models.AppData;
import com.example.praveen.privacycheck.Utils.Permissions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by satyamkumar on 29/11/16.
 */

public class DangerousAppsPagerAdapter extends FragmentPagerAdapter {
    private int pageCount;
    private List<String> tabTitles;
    private Context context;
    private HashMap<String, ArrayList<AppData> > appsByDangerousGroups;
    private AppData appData;


    public DangerousAppsPagerAdapter(FragmentManager fm, Context context, HashMap<String, ArrayList<AppData> > appsByDangerousGroups) {
        super(fm);
        this.context = context;
        this.appsByDangerousGroups = appsByDangerousGroups;
        tabTitles = Permissions.DANGEROUS_GROUP_NAMES;
        this.pageCount = appsByDangerousGroups.keySet().size();
    }

    @Override
    public int getCount() {
        return pageCount;
    }

    @Override
    public Fragment getItem(int position) {

        ArrayList<AppData> apps = appsByDangerousGroups.get(tabTitles.get(position));
        ArrayList<String> appNames = new ArrayList<>();
        for (AppData app : apps) {
            appNames.add(app.getAppName());
        }
        return DangerousPermissionsFragments.newInstance(apps);

    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles.get(position);
    }
}
