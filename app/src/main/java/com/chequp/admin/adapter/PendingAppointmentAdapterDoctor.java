package com.chequp.admin.adapter;

import android.app.Activity;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.chequp.admin.R;
import com.chequp.admin.model.AppointmentModelNew;


import java.util.ArrayList;
import java.util.List;



/**
 * Created by mukul on 3/10/2019.
 */


public class PendingAppointmentAdapterDoctor extends RecyclerView.Adapter<PendingAppointmentAdapterDoctor.MyViewHolder> {
    List<AppointmentModelNew> list = new ArrayList<>();

    Context context;
    int triggeredItem = 0;
    List<String> TestList = new ArrayList<>();
    int pos;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name, tv_problem, tv_date, tv_confirm;
        ImageView circleImageView;
        RelativeLayout relative_container;
        TextView cardPrescribeTest, tv_serial, tv_prescription;


        public MyViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_problem = (TextView) view.findViewById(R.id.tv_problem);
            tv_date = (TextView) view.findViewById(R.id.tv_date);
            tv_serial = (TextView) view.findViewById(R.id.tv_serial);
            tv_date = (TextView) view.findViewById(R.id.tv_date);
            tv_prescription = (TextView) view.findViewById(R.id.tv_prescription);
            tv_confirm = (TextView) view.findViewById(R.id.tv_confirm);


        }
    }


    public PendingAppointmentAdapterDoctor(List<AppointmentModelNew> lists) {
        this.list = lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pending_appointment_dr, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final AppointmentModelNew movie = list.get(position);
        context = holder.tv_name.getContext();
        holder.tv_name.setText(movie.getName());
        holder.tv_serial.setText("" + movie.getId());
        holder.tv_problem.setText(movie.getProblems());
        holder.tv_date.setText(movie.getDate());



    }









    public boolean removeItem(int position) {
        if (list.size() >= position + 1) {
            list.remove(position);
            return true;
        }
        return false;
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}