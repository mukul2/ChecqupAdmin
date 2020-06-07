package com.chequp.admin.fragment;


import android.content.Context;
import android.content.ReceiverCallNotAllowedException;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chequp.admin.R;
import com.chequp.admin.adapter.AmbulanceLitsAdapterAdmin;
import com.chequp.admin.adapter.ServieRateLitsAdapterAdmin;
import com.chequp.admin.api.Api;
import com.chequp.admin.api.ApiListener;
import com.chequp.admin.model.ServiceNameRate;
import com.chequp.admin.widgets.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.chequp.admin.data.data.TOKEN;

/**
 * A simple {@link Fragment} subclass.
 */
public class RatesFragment extends Fragment implements ApiListener.ServiceNameRateDownloadListener {
    View view;
    Context context;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    public RatesFragment() {
        // Required empty public constructor
    }

    public static RatesFragment newInstance() {
        RatesFragment fragment = new RatesFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_rates, container, false);
        context = view.getContext();
        ButterKnife.bind(this, view);
        Api.getInstance().getServiceNameRate(TOKEN, this);
        return view;
    }

    @Override
    public void onServiceNameRateDownloadSuccess(List<ServiceNameRate> list) {
        ServieRateLitsAdapterAdmin mAdapter = new ServieRateLitsAdapterAdmin(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onServiceNameRateDownloadFailed(String msg) {

    }
}
