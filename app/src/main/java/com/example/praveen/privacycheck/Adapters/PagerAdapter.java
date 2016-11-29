package com.example.praveen.privacycheck.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.praveen.privacycheck.Fragments.PermissionsFragments;
import com.example.praveen.privacycheck.Models.AppData;

import java.util.ArrayList;

/**
 * Created by Manan Wason on 29/11/16.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "All Permissions", "Dangerous Permissions", "Normal Permissions" };
    private Context context;
    private ArrayList<String> allPermissions = new ArrayList<>();
    private ArrayList<String> dangPermissions = new ArrayList<>();
    private ArrayList<String> normalPermissions = new ArrayList<>();
    private AppData appData;


    public PagerAdapter(FragmentManager fm, Context context, ArrayList<String> allPermissions, ArrayList<String> dangPermissions, ArrayList<String> normalPermissions, AppData appData) {
        super(fm);
        this.context = context;
        this.dangPermissions = dangPermissions;
        this.allPermissions = allPermissions;
        this.normalPermissions = normalPermissions;
        this.appData = appData;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return PermissionsFragments.newInstance(allPermissions, appData);
            case 1:
                return PermissionsFragments.newInstance(dangPermissions, appData);
            case 2:
                return PermissionsFragments.newInstance(normalPermissions, appData);
            default:
                return PermissionsFragments.newInstance(allPermissions, appData);

        }

    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
