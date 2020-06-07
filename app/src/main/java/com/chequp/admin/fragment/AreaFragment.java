package com.chequp.admin.fragment;


import android.app.Dialog;
import android.content.Context;
import android.icu.util.ValueIterator;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.chequp.admin.R;
import com.chequp.admin.Utils.MyProgressBar;
import com.chequp.admin.Utils.doForMe;
import com.chequp.admin.adapter.AreaLitsAdapterAdmin;
import com.chequp.admin.adapter.DepartmentsAdapter;
import com.chequp.admin.api.Api;
import com.chequp.admin.api.ApiListener;
import com.chequp.admin.model.AmbulanceModel;
import com.chequp.admin.model.DistrictModel;
import com.chequp.admin.model.StatusMessage;
import com.chequp.admin.widgets.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.chequp.admin.data.data.TOKEN;

/**
 * A simple {@link Fragment} subclass.
 */
public class AreaFragment extends Fragment implements ApiListener.DownloadDistrictListInfoListener {
    View view;
    Context context;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.floatingButton)
    FloatingActionButton floatingButton;


    public AreaFragment() {
        // Required empty public constructor
    }

    public static AreaFragment newInstance() {
        AreaFragment fragment = new AreaFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_area, container, false);
        ButterKnife.bind(this, view);
        context = view.getContext();
        downloadArea();
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = doForMe.showDialog(context, R.layout.area_add_dialog);
                EditText ed_name = (EditText) dialog.findViewById(R.id.ed_name);
                TextView tv_cancel = (TextView) dialog.findViewById(R.id.tv_cancel);
                TextView tv_save = (TextView) dialog.findViewById(R.id.tv_save);

                tv_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                tv_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = ed_name.getText().toString().trim();
                        if (name.length() > 0) {
                            MyProgressBar.with(context);
                            Api.getInstance().addArea(TOKEN, name, new ApiListener.AddAreaPostListenerPatient() {
                                @Override
                                public void onAddAreaPostSuccess(StatusMessage data) {
                                    MyProgressBar.dismiss();
                                    if (data.isStatus() == true) {
                                        Toast.makeText(context, "new Area is successfully added", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                        downloadArea();

                                    } else {
                                        Toast.makeText(context, "Error occured.Try agan", Toast.LENGTH_SHORT).show();

                                    }
                                }

                                @Override
                                public void onAddAreaPostFailed(String msg) {
                                    MyProgressBar.dismiss();
                                    Toast.makeText(context, "Error occured.Try agan", Toast.LENGTH_SHORT).show();


                                }
                            });
                        }
                    }
                });


            }
        });


        return view;
    }

    private void downloadArea() {
        Api.getInstance().getDistrictList(TOKEN, this);

    }


    @Override
    public void onDownloadDistrictListInfoSuccess(List<DistrictModel> status) {
        AreaLitsAdapterAdmin mAdapter = new AreaLitsAdapterAdmin(status);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onDownloadDistrictListFailed(String msg) {

    }
}
