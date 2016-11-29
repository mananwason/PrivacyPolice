package com.example.praveen.privacycheck.Activity;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.praveen.privacycheck.Adapters.AppDataAdapter;
import com.example.praveen.privacycheck.Adapters.DangerousAppsPagerAdapter;
import com.example.praveen.privacycheck.Adapters.PagerAdapter;
import com.example.praveen.privacycheck.Models.AppData;
import com.example.praveen.privacycheck.R;
import com.example.praveen.privacycheck.Utils.Constants;
import com.example.praveen.privacycheck.Utils.DividerItemDecoration;
import com.example.praveen.privacycheck.Utils.Permissions;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.example.praveen.privacycheck.Activity.MainActivity.dangerousApps;

/**
 * Created by satyamkumar on 29/11/16.
 */

public class DangerousAppsDisplayActivity extends AppCompatActivity {

    private AppData currentApp;
    private Toolbar toolbar;
    private HashMap<String, ArrayList<AppData> > appsByDangerousGroups;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permissions_tab_layout);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        appsByDangerousGroups = MainActivity.dangerousApps;
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new DangerousAppsPagerAdapter(getSupportFragmentManager(),
                DangerousAppsDisplayActivity.this, appsByDangerousGroups));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);


    }
}
