package com.chequp.admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.chequp.admin.R;
import com.chequp.admin.model.AmbulanceModel;
import com.chequp.admin.model.ServiceNameRate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mukul on 3/10/2019.
 */


public class ServieRateLitsAdapterAdmin extends RecyclerView.Adapter<ServieRateLitsAdapterAdmin.MyViewHolder> {
    List<ServiceNameRate> list = new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, tv_hospitalName, tv_body, tv_lastDegree, tv_epacialist, tv_address;
        RelativeLayout relative_container;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_title);
            tv_body = (TextView) view.findViewById(R.id.tv_body);


        }
    }


    public ServieRateLitsAdapterAdmin(List<ServiceNameRate> lists) {
        this.list = lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.service_rate_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final ServiceNameRate movie = list.get(position);
        context = holder.title.getContext();
        holder.title.setText(movie.getServiceName());
        holder.tv_body.setText(movie.getRate()+" TK per unite");



    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}