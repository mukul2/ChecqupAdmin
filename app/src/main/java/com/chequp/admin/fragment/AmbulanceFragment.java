package com.chequp.admin.fragment;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.chequp.admin.adapter.AmbulanceLitsAdapterAdmin;
import com.chequp.admin.adapter.MedicineLitsAdapterAdmin;
import com.chequp.admin.api.Api;
import com.chequp.admin.api.ApiListener;
import com.chequp.admin.model.AmbulanceModel;
import com.chequp.admin.model.DistrictModel;
import com.chequp.admin.model.StatusMessage;
import com.chequp.admin.widgets.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.chequp.admin.data.data.TOKEN;

/**
 * A simple {@link Fragment} subclass.
 */
public class AmbulanceFragment extends Fragment implements ApiListener.DownloadAmbulanceListInfoListener {
    View view;
    Context context;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.floatingButton)
    FloatingActionButton floatingButton;

    String selcted_district;

    public AmbulanceFragment() {
        // Required empty public constructor
    }

    public static AmbulanceFragment newInstance() {
        AmbulanceFragment fragment = new AmbulanceFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ambulance, container, false);
        ButterKnife.bind(this, view);
        context = view.getContext();

        download();

        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Api.getInstance().getDistrictList(TOKEN, new ApiListener.DownloadDistrictListInfoListener() {
                    @Override
                    public void onDownloadDistrictListInfoSuccess(List<DistrictModel> status) {
                        selcted_district = "";

                        Dialog dialog = doForMe.showDialog(context, R.layout.add_ambulance_dialog);
                        Spinner spinner = (Spinner) dialog.findViewById(R.id.spinner);
                        EditText phone = (EditText) dialog.findViewById(R.id.ed_phone);
                        EditText title = (EditText) dialog.findViewById(R.id.ed_title);
                        EditText area = (EditText) dialog.findViewById(R.id.ed_area);
                        EditText address = (EditText) dialog.findViewById(R.id.ed_address);
                        TextView tv_save = (TextView) dialog.findViewById(R.id.tv_save);
                        TextView tv_cancel = (TextView) dialog.findViewById(R.id.tv_cancel);
                        List<String> distructs = new ArrayList<>();
                        distructs.add("Select Area");
                        for (int i = 0; i < status.size(); i++) {
                            distructs.add(status.get(i).getName());

                        }

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, distructs);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(dataAdapter);
                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                if (i > 0) {
                                    selcted_district = "" + status.get(i - 1).getId();
                                } else
                                    selcted_district = "";

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        tv_cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });

                        tv_save.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String phone_ = phone.getText().toString().trim();
                                String title_ = title.getText().toString().trim();
                                String area_ = area.getText().toString().trim();
                                String address_ = address.getText().toString().trim();
                                if (phone_.length() > 0) {
                                    if (selcted_district.length() > 0) {
                                        if (title_.length() > 0) {
                                            if (area_.length() > 0) {
                                                if (address_.length() > 0) {

                                                    MyProgressBar.with(context);
                                                    Api.getInstance().addAmbulance(TOKEN, selcted_district, phone_, title_, area_, address_, new ApiListener.AddAmbulanceListenerPatient() {
                                                        @Override
                                                        public void onAddAreaPostSuccess(StatusMessage data) {
                                                            MyProgressBar.dismiss();
                                                            if (data.isStatus() == true) {
                                                                Toast.makeText(context, data.getMessage(), Toast.LENGTH_SHORT).show();
                                                                dialog.dismiss();
                                                                download();

                                                            } else {
                                                                Toast.makeText(context, "Error occured.try again later", Toast.LENGTH_SHORT).show();
                                                            }


                                                        }

                                                        @Override
                                                        public void onAddAmbulancePostFailed(String msg) {
                                                            MyProgressBar.dismiss();
                                                            Toast.makeText(context, "Error occured.try again later", Toast.LENGTH_SHORT).show();


                                                        }
                                                    });
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        });


                    }

                    @Override
                    public void onDownloadDistrictListFailed(String msg) {

                    }
                });

            }
        });


        return view;
    }

    private void download() {
        Api.getInstance().getAmbulanceList(TOKEN, this);

    }

    @Override
    public void onDownloadAmbulanceListInfoSuccess(List<AmbulanceModel> status) {
        AmbulanceLitsAdapterAdmin mAdapter = new AmbulanceLitsAdapterAdmin(status);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDownloadAmbulanceListFailed(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }
}
