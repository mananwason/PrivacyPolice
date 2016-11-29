package com.example.praveen.privacycheck.Adapters;

import android.content.Intent;
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
 * Created by satyamkumar on 29/11/16.
 */

public class DangerousAppsPermissionsAdapter extends RecyclerView.Adapter<DangerousAppsPermissionsAdapter.MyViewHolder> {
    private List<AppData> permissions;

    public DangerousAppsPermissionsAdapter(List<AppData> permissionsList) {
        this.permissions = permissionsList;
    }

    @Override
    public DangerousAppsPermissionsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.permissions_list, parent, false);

        return new DangerousAppsPermissionsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DangerousAppsPermissionsAdapter.MyViewHolder holder, int position) {
        holder.icon.setImageDrawable(permissions.get(position).getIcon());
        holder.name.setText(permissions.get(position).getAppName());
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
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts("package", permissions.get(getAdapterPosition()).getPackageName(), null));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            view.getContext().startActivity(intent);
        }
    }
}
