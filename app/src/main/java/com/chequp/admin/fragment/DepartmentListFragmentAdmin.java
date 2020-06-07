package com.chequp.admin.fragment;


import android.app.Dialog;
import android.content.Context;
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
import com.chequp.admin.adapter.DepartmentsAdapter;
import com.chequp.admin.adapter.DrLitsAdapterAdmin;
import com.chequp.admin.api.Api;
import com.chequp.admin.api.ApiListener;
import com.chequp.admin.model.DeptModel;
import com.chequp.admin.model.StatusMessage;
import com.chequp.admin.widgets.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.chequp.admin.data.data.TOKEN;
import static com.chequp.admin.data.data.loginResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public class DepartmentListFragmentAdmin extends Fragment implements ApiListener.DeptDownloadListener {
    View view;
    Context context;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.floatingButton)
    FloatingActionButton floatingButton;

    public static DepartmentListFragmentAdmin newInstance() {
        DepartmentListFragmentAdmin fragment = new DepartmentListFragmentAdmin();
        return fragment;
    }

    public DepartmentListFragmentAdmin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_department_list_fragment_admin, container, false);
        ButterKnife.bind(this,view);

        context = view.getContext();
        downloadDepartments();
       floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Dialog dialog= doForMe.showDialog(context,R.layout.department_add);
                EditText ed_name=(EditText)dialog.findViewById(R.id.ed_name);
                TextView tv_cancel=(TextView)dialog.findViewById(R.id.tv_cancel);
                TextView tv_save=(TextView)dialog.findViewById(R.id.tv_save);

                tv_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                tv_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name=ed_name.getText().toString().trim();
                        if (name.length()>0){
                            MyProgressBar.with(context);
                            Api.getInstance().addDepartment(TOKEN, name, new ApiListener.DepartmentAddListener() {
                                @Override
                                public void onDepartmentAddSuccess(StatusMessage data) {
                                    MyProgressBar.dismiss();
                                    if (data.isStatus()==true){
                                        Toast.makeText(context, "new Department is successfully added", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                        downloadDepartments();

                                    }else {
                                        Toast.makeText(context, "Error occured.Try agan", Toast.LENGTH_SHORT).show();

                                    }
                                }

                                @Override
                                public void onDepartmentAddFailed(String msg) {
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

    private void downloadDepartments() {
        Api.getInstance().getDepList("Bearer " + loginResponse.getAccessToken(), this);

    }

    @Override
    public void onDepartmentDownloadSuccess(List<DeptModel> list) {
        DepartmentsAdapter mAdapter = new DepartmentsAdapter(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
       // recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDepartmentDownloadFailed(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }
}
