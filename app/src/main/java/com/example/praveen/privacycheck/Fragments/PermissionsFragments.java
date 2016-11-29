package com.example.praveen.privacycheck.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.praveen.privacycheck.Adapters.PermissionsAdapter;
import com.example.praveen.privacycheck.Models.AppData;
import com.example.praveen.privacycheck.R;
import com.example.praveen.privacycheck.Utils.Constants;
import com.example.praveen.privacycheck.Utils.DividerItemDecoration;

import java.util.ArrayList;

/**
 * Created by Manan Wason on 29/11/16.
 */

public class PermissionsFragments extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<String> sortedPermissions;
    private AppData appData;

    public static PermissionsFragments newInstance(ArrayList<String> PermissionsList, AppData appData) {
        Bundle args = new Bundle();
        args.putSerializable(Constants.LIST, PermissionsList);
        if(appData != null)
            args.putParcelable(Constants.APP_DAT, appData);
        PermissionsFragments fragment = new PermissionsFragments();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sortedPermissions = (ArrayList<String>) getArguments().getSerializable(Constants.LIST);
        appData = getArguments().getParcelable(Constants.APP_DAT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_permissions, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.list_permissions);
        adapter = new PermissionsAdapter(sortedPermissions, appData);
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