package com.example.praveen.privacycheck.Adapters;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.praveen.privacycheck.Models.AppData;
import com.example.praveen.privacycheck.R;

import java.util.List;

/**
 * Created by Manan Wason on 27/11/16.
 */

public class PermissionsAdapter extends RecyclerView.Adapter<PermissionsAdapter.MyViewHolder> {
    private List<String> permissions;
    private AppData currentApp;

    public PermissionsAdapter(List<String> permissionsList, AppData currentApp) {
        this.permissions = permissionsList;
        this.currentApp = currentApp;
    }

    @Override
    public PermissionsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.permissions_list, parent, false);

        return new PermissionsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PermissionsAdapter.MyViewHolder holder, int position) {
//        holder.icon.setImageDrawable();
        PackageManager mPackageManager = holder.icon.getContext().getPackageManager();
        PermissionInfo permissionInfo = null;
        PermissionGroupInfo groupInfo = null;
        Drawable drawable = null;
        try {
            permissionInfo = mPackageManager.getPermissionInfo("android.permission." + permissions.get(position), 0);
            groupInfo = mPackageManager.getPermissionGroupInfo(permissionInfo.group, 0);
            drawable = mPackageManager.getResourcesForApplication("android").getDrawable(groupInfo.icon);
            holder.icon.setImageDrawable(drawable);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        holder.name.setText(permissions.get(position));
    }


    @Override
    public int getItemCount() {
        return permissions.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView name;
        public ImageView icon;

        public MyViewHolder(View view) {
            super(view);

            icon = (ImageView) view.findViewById(R.id.app_icon);
            name = (TextView) view.findViewById(R.id.app_name);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d("Intent", "Permission Adapter");
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts("package", currentApp.getPackageName() , null));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            view.getContext().startActivity(intent);
        }
    }
}