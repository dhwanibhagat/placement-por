package com.example.dhwani.loginactivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Dhwani on 4/29/2018.
 */

public class MyAdaptor extends RecyclerView.Adapter<MyAdaptor.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;

    public MyAdaptor(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);

        holder.textjob.setText(listItem.getJobName());
        holder.textcompany.setText(listItem.getCompanyName());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textjob;
        public TextView textcompany;

        public ViewHolder(View itemView) {
            super(itemView);

            textjob = (TextView) itemView.findViewById(R.id.textjob);
            textcompany = (TextView) itemView.findViewById(R.id.textcompany);
        }
    }
}
