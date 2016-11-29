package com.example.praveen.privacycheck.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.praveen.privacycheck.Adapters.DangerousAppsPermissionsAdapter;
import com.example.praveen.privacycheck.Adapters.PermissionsAdapter;
import com.example.praveen.privacycheck.Models.AppData;
import com.example.praveen.privacycheck.R;
import com.example.praveen.privacycheck.Utils.Constants;
import com.example.praveen.privacycheck.Utils.DividerItemDecoration;

import java.util.ArrayList;

/**
 * Created by satyamkumar on 29/11/16.
 */

public class DangerousPermissionsFragments extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<AppData> sortedPermissions;
    private AppData appData;

    public static DangerousPermissionsFragments newInstance(ArrayList<AppData> apps) {
        Bundle args = new Bundle();
        args.putSerializable(Constants.LIST, apps);
        DangerousPermissionsFragments fragment = new DangerousPermissionsFragments();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sortedPermissions = (ArrayList<AppData>) getArguments().getSerializable(Constants.LIST);
        appData = getArguments().getParcelable(Constants.APP_DAT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_permissions, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.list_permissions);
        adapter = new DangerousAppsPermissionsAdapter(sortedPermissions);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext()));
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(200);
        itemAnimator.setRemoveDuration(200);
        recyclerView.setItemAnimator(itemAnimator);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }
}
