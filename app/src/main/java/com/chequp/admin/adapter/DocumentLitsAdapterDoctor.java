package com.chequp.admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chequp.admin.R;
import com.chequp.admin.model.DocumentModel;


import java.util.ArrayList;
import java.util.List;

import static com.chequp.admin.data.data.IMAGE_BASE;


/**
 * Created by mukul on 3/10/2019.
 */


public class DocumentLitsAdapterDoctor extends RecyclerView.Adapter<DocumentLitsAdapterDoctor.MyViewHolder> {
    List<DocumentModel> list = new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title;
        RelativeLayout relative_container;
        ImageView imageView;


        public MyViewHolder(View view) {
            super(view);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            imageView = (ImageView) view.findViewById(R.id.image);


        }
    }


    public DocumentLitsAdapterDoctor(List<DocumentModel> lists) {
        this.list = lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doctor_document_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final DocumentModel movie = list.get(position);
        context = holder.tv_title.getContext();
        holder.tv_title.setText(movie.getTitle());
        Glide.with(context).load(IMAGE_BASE + movie.getPhoto()).into(holder.imageView);


    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}