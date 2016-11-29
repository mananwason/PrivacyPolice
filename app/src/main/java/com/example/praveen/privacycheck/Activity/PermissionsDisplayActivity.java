package com.example.praveen.privacycheck.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.praveen.privacycheck.Adapters.PagerAdapter;
import com.example.praveen.privacycheck.Models.AppData;
import com.example.praveen.privacycheck.R;
import com.example.praveen.privacycheck.Utils.Constants;
import com.example.praveen.privacycheck.Utils.Permissions;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Manan Wason on 27/11/16.
 */

public class PermissionsDisplayActivity extends AppCompatActivity {
    private AppData currentApp;
    private Toolbar toolbar;
    private ArrayList<String> permissions;
    private ArrayList<String> sortedPermissions;
    private ArrayList<String> dangerousSortedPermissions;
    private ArrayList<String> normalSortedPermissions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permissions_tab_layout);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        Bundle b = this.getIntent().getExtras();
        dangerousSortedPermissions = new ArrayList<>();
        normalSortedPermissions = new ArrayList<>();

        if (b != null) {
            currentApp = b.getParcelable(Constants.APP_NAME);
            permissions = new ArrayList<>(Arrays.asList(currentApp.getPermissions()));
            sortedPermissions = new ArrayList<>();
            for (String permission : permissions) {
                if (permission.contains("android")) {
                    int position = 0;
                    for (int i = 0; i < permission.length(); i++) {
                        char c = permission.charAt(i);
                        if (Character.isUpperCase(c)) {
                            position = i;
                            break;
                        }
                    }
                    sortedPermissions.add(permission.substring(position));
                }
            }
            for (String permission : sortedPermissions) {
                if(Permissions.DANGEROUS_PERMISSIONS.contains(permission)) {
                    dangerousSortedPermissions.add(permission);
                    Log.d("dangerous permission", permission);
                } else {
                    normalSortedPermissions.add(permission);
                    Log.d("normal permission", permission);
                }
            }

        }
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(),
                PermissionsDisplayActivity.this, sortedPermissions, dangerousSortedPermissions, normalSortedPermissions, currentApp));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);


    }
}
