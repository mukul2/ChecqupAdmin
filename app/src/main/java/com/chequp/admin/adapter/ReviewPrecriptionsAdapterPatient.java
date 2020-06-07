package com.chequp.admin.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chequp.admin.R;
import com.chequp.admin.data.data;
import com.chequp.admin.model.PrescriptionReviewModel;
import com.chequp.admin.widgets.DividerItemDecoration;


import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.chequp.admin.data.data.IMAGE_BASE;


/**
 * Created by mukul on 3/10/2019.
 */


public class ReviewPrecriptionsAdapterPatient extends RecyclerView.Adapter<ReviewPrecriptionsAdapterPatient.MyViewHolder> {
    List<PrescriptionReviewModel> list = new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_DrName, tv_date, tv_time, tv_lastDegree, tv_epacialist, tv_address,tv_comment,tv_reply;
        ImageView circleImageView,img_reviewd;
        RelativeLayout relative_container;
        RecyclerView recycler_view;
        CircleImageView proPic;


        public MyViewHolder(View view) {
            super(view);
            tv_DrName = (TextView) view.findViewById(R.id.tv_DrName);
            tv_reply = (TextView) view.findViewById(R.id.tv_reply);
            tv_date = (TextView) view.findViewById(R.id.tv_date);
            tv_comment = (TextView) view.findViewById(R.id.tv_comment);
            recycler_view = (RecyclerView) view.findViewById(R.id.recycler_view);
            proPic = (CircleImageView) view.findViewById(R.id.proPic);
            img_reviewd = (ImageView) view.findViewById(R.id.img_reviewd);


        }
    }



    public ReviewPrecriptionsAdapterPatient(List<PrescriptionReviewModel > lists) {
        this.list = lists;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recheck_item_patient, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final PrescriptionReviewModel  date = list.get(position);
        context = holder.tv_DrName.getContext();
        holder.tv_DrName.setText(date.getDrInfo().getName());
        holder.tv_date.setText(data.changeDateformate2(date.getCreatedAt()));
        Glide.with(context).load(IMAGE_BASE+date.getDrInfo().getPhoto()).into(holder.proPic);

        //write recycler here
        MedicinesListAdapter mAdapter = new MedicinesListAdapter(date.getMedicineInfo());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        holder.recycler_view.setLayoutManager(mLayoutManager);
        holder.recycler_view.setItemAnimator(new DefaultItemAnimator());
        holder.recycler_view.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL, false));
        holder.recycler_view.setAdapter(mAdapter);
        holder.recycler_view.setClickable(false);

       /*
        holder.tv_reply.setClickListener(new MedicinesListAdapter.ClickListener() {
            @Override
            public void onClicked() {
               // GeneralListener.needRefresh.doRefresh(position);
                Toast.makeText(context, "clicked review", Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, PrescriptionReviewReplyActivity.class));


            }
        });
        */
        if (date.getPatient_comment()!=null){
            holder.tv_comment.setText(date.getPatient_comment());
        }else {
            holder.tv_comment.setText("NO comment");

        }



        if (date.getIsReviewed()==1){
            holder.img_reviewd.setVisibility(View.VISIBLE);
        }else {
            holder.img_reviewd.setVisibility(View.GONE);

        }




    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}