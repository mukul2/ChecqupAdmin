package com.chequp.admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;


import com.chequp.admin.R;
import com.chequp.admin.api.Api;
import com.chequp.admin.api.ApiListener;
import com.chequp.admin.model.ServiceWithBoolean;
import com.chequp.admin.model.StatusMessage;

import java.util.ArrayList;
import java.util.List;

import static com.chequp.admin.data.data.CURRENCY_USD_SIGN;
import static com.chequp.admin.data.data.TOKEN;
import static com.chequp.admin.fragment.OnlineFragmentDr.SERVICES_LIST;


/**
 * Created by mukul on 3/10/2019.
 */


public class DrServicesListAdapter extends RecyclerView.Adapter<DrServicesListAdapter.MyViewHolder> {

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name,tv_fees;
        CheckBox checkbox;


        public MyViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_fees = (TextView) view.findViewById(R.id.tv_fees);
            checkbox = (CheckBox) view.findViewById(R.id.checkbox);


        }
    }


    public DrServicesListAdapter() {

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dr_services_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final ServiceWithBoolean data = SERVICES_LIST.get(position);
        context = holder.tv_name.getContext();
        holder.tv_name.setText(data.getServiceName().getName());
        holder.tv_fees.setText(""+data.getFees()+CURRENCY_USD_SIGN);
        if (data.isSelected() == true) {
            holder.checkbox.setChecked(true);
        } else {
            holder.checkbox.setChecked(false);

        }



    }


    @Override
    public int getItemCount() {
        return SERVICES_LIST.size();
    }
}