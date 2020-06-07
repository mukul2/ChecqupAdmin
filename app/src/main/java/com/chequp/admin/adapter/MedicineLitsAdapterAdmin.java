package com.chequp.admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chequp.admin.R;
import com.chequp.admin.model.CommonUserModel;
import com.chequp.admin.model.MedicineModel;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.chequp.admin.data.data.IMAGE_BASE;

/**
 * Created by mukul on 3/10/2019.
 */


public class MedicineLitsAdapterAdmin extends RecyclerView.Adapter<MedicineLitsAdapterAdmin.MyViewHolder> {
    List<MedicineModel> list = new ArrayList<>();

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


    public MedicineLitsAdapterAdmin(List<MedicineModel> lists) {
        this.list = lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.medicine_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final MedicineModel movie = list.get(position);
        context = holder.title.getContext();
        holder.title.setText(movie.getName());
        holder.tv_body.setText(movie.getManufacture());


    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}