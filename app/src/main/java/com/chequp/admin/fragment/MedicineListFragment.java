package com.chequp.admin.fragment;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.ResultReceiver;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.chequp.admin.R;
import com.chequp.admin.Utils.MyProgressBar;
import com.chequp.admin.Utils.doForMe;
import com.chequp.admin.adapter.DrLitsAdapterAdmin;
import com.chequp.admin.adapter.MedicineLitsAdapterAdmin;
import com.chequp.admin.api.Api;
import com.chequp.admin.api.ApiListener;
import com.chequp.admin.model.MedicineModel;
import com.chequp.admin.model.StatusMessage;
import com.chequp.admin.widgets.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.chequp.admin.data.data.TOKEN;

/**
 * A simple {@link Fragment} subclass.
 */
public class MedicineListFragment extends Fragment implements ApiListener.DownloadMedicinesListInfoListener {
    View view;
    Context context;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.floatingButton)
    FloatingActionButton floatingButton;

    public static MedicineListFragment newInstance() {
        MedicineListFragment fragment = new MedicineListFragment();
        return fragment;
    }

    public MedicineListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_medicine_list, container, false);
        context = view.getContext();
        ButterKnife.bind(this, view);
       downLoadMedicines();
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = doForMe.showDialog(context, R.layout.add_medicine_dialog);
                //add_medicine_info
                EditText ed_name = (EditText) dialog.findViewById(R.id.ed_name);
                EditText ed_menufracture = (EditText) dialog.findViewById(R.id.ed_menufracture);
                EditText ed_type = (EditText) dialog.findViewById(R.id.ed_type);
                EditText ed_description = (EditText) dialog.findViewById(R.id.ed_description);
                ImageView save_image = (ImageView) dialog.findViewById(R.id.save_image);

                save_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {




                String name = ed_name.getText().toString().trim();
                String manufacture = ed_menufracture.getText().toString().trim();
                String type = ed_type.getText().toString().trim();
                String description = ed_description.getText().toString().trim();
                if (name.length() > 0) {
                    if (manufacture.length() > 0) {
                        if (type.length() > 0) {
                            if (description.length() > 0) {
                                MyProgressBar.with(context);
                                Api.getInstance().add_medicine_info(TOKEN, name, manufacture, type, description, new ApiListener.MedicicinePostListener() {
                                    @Override
                                    public void onMedicicinePostSuccess(StatusMessage list) {
                                        dialog.dismiss();
                                        downLoadMedicines();
                                        MyProgressBar.dismiss();
                                        if (list.isStatus() == true) {

                                        } else {
                                            Toast.makeText(context, "Error occured.Try again", Toast.LENGTH_SHORT).show();
                                        }

                                    }

                                    @Override
                                    public void onMedicicinePostFailed(String msg) {
                                        MyProgressBar.dismiss();
                                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                                    }
                                });


                            }
                        }
                    }
                }
                    }
                });


            }
        });
        return view;
    }

    public  void  downLoadMedicines(){
        Api.getInstance().getMedicinesList(TOKEN, this);

    }

    @Override
    public void onDownloadMedicinesListInfoSuccess(List<MedicineModel> status) {
        //MedicineLitsAdapterAdmin
        MedicineLitsAdapterAdmin mAdapter = new MedicineLitsAdapterAdmin(status);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDownloadMedicinesListFailed(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }
}
