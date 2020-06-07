package com.chequp.admin.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chequp.admin.R;
import com.chequp.admin.activity.DoctorProfileActivity;
import com.chequp.admin.model.CommonUserModel;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.chequp.admin.data.data.IMAGE_BASE;
import static com.chequp.admin.data.data.commonUserModel;

/**
 * Created by mukul on 3/10/2019.
 */


public class DrLitsAdapterAdmin extends RecyclerView.Adapter<DrLitsAdapterAdmin.MyViewHolder> {
    List<CommonUserModel> list = new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, tv_hospitalName, tv_time, tv_lastDegree, tv_epacialist, tv_address;
        RelativeLayout relative_container;
        CircleImageView profile;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_title);
            tv_hospitalName = (TextView) view.findViewById(R.id.tv_department);
            profile = (CircleImageView) view.findViewById(R.id.profile);


        }
    }


    public DrLitsAdapterAdmin(List<CommonUserModel> lists) {
        this.list = lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_dr_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final CommonUserModel movie = list.get(position);
        context = holder.title.getContext();
        holder.title.setText(movie.getName());
        holder.tv_hospitalName.setText(movie.getDesignation_title());
        Glide.with(context).load(IMAGE_BASE + movie.getPhoto()).into(holder.profile);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commonUserModel = movie;
                Toast.makeText(context, ""+movie.getId(), Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, DoctorProfileActivity.class));

            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}