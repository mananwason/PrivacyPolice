package com.example.praveen.privacycheck.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.praveen.privacycheck.Adapters.PermissionsAdapter;
import com.example.praveen.privacycheck.Models.AppData;
import com.example.praveen.privacycheck.Utils.Constants;
import com.example.praveen.privacycheck.Utils.DividerItemDecoration;
import com.example.praveen.privacycheck.Utils.Permissions;
import com.example.praveen.privacycheck.R;
import com.example.praveen.privacycheck.Utils.Permissions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Manan Wason on 27/11/16.
 */

public class PermissionsDisplayActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private AppData currentApp;
    private Toolbar toolbar;
    private List<String> permissions;
    private List<String> sortedPermissions;
    private List<String> dangerousSortedPermissions;
    private List<String> normalSortedPermissions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle b = this.getIntent().getExtras();
        permissions = new ArrayList<>();
        dangerousSortedPermissions = new ArrayList<>();
        normalSortedPermissions = new ArrayList<>();
        if (b != null) {
            currentApp = b.getParcelable(Constants.APP_NAME);
            Log.d("ABC", currentApp.getAppName());
            permissions = Arrays.asList(currentApp.getPermissions());
            sortedPermissions = new ArrayList<>();
            Log.d("ABC", permissions.toString());
            for (String permission : permissions) {
                if (permission.contains("android")) {
                    String outputString = "";
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


        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.app_list_recycler_view);
        adapter = new PermissionsAdapter(sortedPermissions, currentApp);
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
