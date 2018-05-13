package com.example.dhwani.loginactivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.apache.http.conn.ConnectTimeoutException;

import java.util.List;

/**
 * Created by Dhwani on 5/9/2018.
 */

public class MyAdaptor1 extends RecyclerView.Adapter<MyAdaptor1.ViewHolder> {

    private List <ListItem1> listItem1s;
    private Context context;

    public MyAdaptor1(List<ListItem1> listItem1s, Context context) {
        this.listItem1s = listItem1s;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item1,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ListItem1 listItem1 = listItem1s.get(position);
        holder.textjob.setText(listItem1.getJob_Name());
        holder.textcompany.setText(listItem1.getCompany_Name());

    }

    @Override
    public int getItemCount() {
        return listItem1s.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textjob;
        public TextView textcompany;

        public ViewHolder(View itemView) {
            super(itemView);

            textjob = (TextView) itemView.findViewById(R.id.textjob);
            textcompany = (TextView) itemView.findViewById(R.id.textcompany);
        }
    }
}
