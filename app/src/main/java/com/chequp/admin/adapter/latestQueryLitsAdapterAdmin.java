package com.chequp.admin.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chequp.admin.R;
import com.chequp.admin.activity.PublicQueryChatActivity;
import com.chequp.admin.model.CommonUserModel;
import com.chequp.admin.model.PublicLatestQueryModel;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.chequp.admin.data.data.IMAGE_BASE;
import static com.chequp.admin.data.data.QUERY_MODEL;

/**
 * Created by mukul on 3/10/2019.
 */


public class latestQueryLitsAdapterAdmin extends RecyclerView.Adapter<latestQueryLitsAdapterAdmin.MyViewHolder> {
    List<PublicLatestQueryModel> list = new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, tv_hospitalName, tv_body, tv_lastDegree, tv_epacialist, tv_address;
        RelativeLayout relative_container;
        CircleImageView profile;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_title);
            tv_body = (TextView) view.findViewById(R.id.tv_body);
            profile = (CircleImageView) view.findViewById(R.id.profile);


        }
    }


    public latestQueryLitsAdapterAdmin(List<PublicLatestQueryModel> lists) {
        this.list = lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.latest_query_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final PublicLatestQueryModel movie = list.get(position);
        context = holder.title.getContext();
        holder.title.setText(movie.getSenderProfile().getName());
        holder.tv_body.setText(movie.getMessageBody());
        Glide.with(context).load(IMAGE_BASE + movie.getSenderProfile().getPhoto()).into(holder.profile);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QUERY_MODEL=movie;
                context.startActivity(new Intent(context, PublicQueryChatActivity.class));
            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}