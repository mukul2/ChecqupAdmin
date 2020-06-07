package com.chequp.admin.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chequp.admin.R;
import com.chequp.admin.Utils.GeneralListener;
import com.chequp.admin.Utils.MyProgressBar;
import com.chequp.admin.Utils.doForMe;
import com.chequp.admin.api.Api;
import com.chequp.admin.api.ApiListener;
import com.chequp.admin.model.StatusMessage;
import com.chequp.admin.model.WidthdrawModel;


import java.util.ArrayList;
import java.util.List;

import static com.chequp.admin.data.data.CURRENCY_USD_SIGN;
import static com.chequp.admin.data.data.TOKEN;


/**
 * Created by mukul on 3/10/2019.
 */


public class WidhdrawListDoctorAdapter extends RecyclerView.Adapter<WidhdrawListDoctorAdapter.MyViewHolder> {
    List<WidthdrawModel> list = new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_rate, tv_status, tv_date, tv_ac;


        public MyViewHolder(View view) {
            super(view);
            tv_rate = (TextView) view.findViewById(R.id.tv_rate);
            tv_status = (TextView) view.findViewById(R.id.tv_status);
            tv_date = (TextView) view.findViewById(R.id.tv_date);
            tv_ac = (TextView) view.findViewById(R.id.tv_ac);


        }
    }


    public WidhdrawListDoctorAdapter(List<WidthdrawModel> lists) {
        this.list = lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.widhdraw_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final WidthdrawModel movie = list.get(position);
        context = holder.tv_rate.getContext();
        holder.tv_rate.setText("" + movie.getAmount() + CURRENCY_USD_SIGN);
        holder.tv_date.setText("" + movie.getCreated_at());
        holder.tv_ac.setText("ASKED INTO : " + movie.getBankinfo());
        String status = "Pending";
        if (movie.getStatus() == 1) {
            status = "Compleated";
        } else if (movie.getStatus() == 2) {
            status = "Refused";
        }
        holder.tv_status.setText(status);

        holder.itemView.setOnClickListener((View view) -> {
            Dialog dialog = doForMe.showDialog(context, R.layout.witdhdraw_response_dialog);
            CardView cardPending = (CardView) dialog.findViewById(R.id.cardPending);
            CardView cardCompleate = (CardView) dialog.findViewById(R.id.cardCompleate);
            CardView cardRefused = (CardView) dialog.findViewById(R.id.cardRefused);

            if (movie.getStatus() == 0) {
                cardPending.setCardBackgroundColor(context.getResources().getColor(R.color.gray));
                cardCompleate.setCardBackgroundColor(context.getResources().getColor(R.color.white));
                cardRefused.setCardBackgroundColor(context.getResources().getColor(R.color.white));
            } else if (movie.getStatus() == 1) {
                cardCompleate.setCardBackgroundColor(context.getResources().getColor(R.color.white));
                cardCompleate.setCardBackgroundColor(context.getResources().getColor(R.color.gray));
                cardRefused.setCardBackgroundColor(context.getResources().getColor(R.color.white));

            } else if (movie.getStatus() == 2) {
                cardRefused.setCardBackgroundColor(context.getResources().getColor(R.color.white));
                cardCompleate.setCardBackgroundColor(context.getResources().getColor(R.color.white));
                cardRefused.setCardBackgroundColor(context.getResources().getColor(R.color.gray));

            }
            ApiListener.BasicApiListener listener = new ApiListener.BasicApiListener() {
                @Override
                public void onBasicAPISuccess(StatusMessage response) {
                    MyProgressBar.dismiss();
                    dialog.dismiss();
                    GeneralListener.needRefresh.doRefresh(0);


                }

                @Override
                public void onBasicAPIFailed(String msg) {
                    MyProgressBar.dismiss();

                }
            };


            cardPending.setOnClickListener((View v) -> {
                MyProgressBar.with(context);
                Api.getInstance().update_withdrawal_request(TOKEN, "" + movie.getId(), "0", listener);

            });


            cardCompleate.setOnClickListener((View v) -> {
                MyProgressBar.with(context);

                Api.getInstance().update_withdrawal_request(TOKEN, "" + movie.getId(), "1", listener);

            });

            cardRefused.setOnClickListener((View v) -> {
                MyProgressBar.with(context);

                Api.getInstance().update_withdrawal_request(TOKEN, "" + movie.getId(), "2", listener);

            });

        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}