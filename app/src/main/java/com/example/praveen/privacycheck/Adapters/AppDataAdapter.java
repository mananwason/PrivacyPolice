package com.example.praveen.privacycheck.Adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.praveen.privacycheck.Activity.DangerousAppsDisplayActivity;
import com.example.praveen.privacycheck.Models.AppData;
import com.example.praveen.privacycheck.Utils.Constants;
import com.example.praveen.privacycheck.Activity.PermissionsDisplayActivity;
import com.example.praveen.privacycheck.R;

import java.util.ArrayList;

/**
 * Created by Praveen on 11/10/2016.
 */
public class AppDataAdapter extends RecyclerView.Adapter<AppDataAdapter.MyViewHolder> {
    private ArrayList<AppData> apps;

    public AppDataAdapter(ArrayList<AppData> appList) {
        this.apps = appList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.app_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.icon.setImageDrawable(apps.get(position).getIcon());
        holder.name.setText(apps.get(position).getAppName());

    }

    @Override
    public int getItemCount() {
        return apps.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
//            Intent intent = new Intent(view.getContext(), DangerousAppsDisplayActivity.class);

            Intent intent = new Intent(view.getContext(), PermissionsDisplayActivity.class);
            Bundle bundle = new Bundle();
            AppData clicked = apps.get(getAdapterPosition());
            bundle.putParcelable(Constants.APP_NAME, clicked);
            intent.putExtras(bundle);
            view.getContext().startActivity(intent);
        }
    }
}