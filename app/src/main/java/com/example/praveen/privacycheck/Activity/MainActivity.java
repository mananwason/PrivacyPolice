package com.example.praveen.privacycheck.Activity;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.praveen.privacycheck.Adapters.AppDataAdapter;
import com.example.praveen.privacycheck.Models.AppData;
import com.example.praveen.privacycheck.Utils.DividerItemDecoration;
import com.example.praveen.privacycheck.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.app_list_recycler_view);


        List<PackageInfo> apps = getPackageManager().getInstalledPackages(0);
        List<ApplicationInfo> packages = getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
        ArrayList<AppData> appsData = new ArrayList<>();
        for (int i = 0; i < apps.size(); i++) {
            // to get only launchable apps data
            // to get all apps remove this if condition and also remove the packages list
            if (getPackageManager().getLaunchIntentForPackage(packages.get(i).packageName) != null) {
                try {

                    PackageInfo p = apps.get(i);
                    PackageInfo pi = getPackageManager().getPackageInfo(apps.get(i).packageName, PackageManager.GET_PERMISSIONS);

                    AppData newInfo = new AppData(p.applicationInfo.loadLabel(getPackageManager()).toString(),
                            p.packageName,
                            p.versionName,
                            p.versionCode,
                            p.applicationInfo.loadIcon(getPackageManager()),
                            pi.requestedPermissions);
                    appsData.add(newInfo);
                    // to get application permissions
                } catch (PackageManager.NameNotFoundException e) {
                    Log.d("Get Permissions", "Application Name not found");
                }
            }
        }
        Collections.sort(appsData);
        adapter = new AppDataAdapter(appsData);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext()));
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(200);
        itemAnimator.setRemoveDuration(200);
        recyclerView.setItemAnimator(itemAnimator);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
