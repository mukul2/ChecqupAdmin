package com.chequp.admin.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chequp.admin.R;
import com.chequp.admin.activity.AllNotificationActivity;
import com.chequp.admin.activity.SendDepartmentNotificationActivity;
import com.chequp.admin.activity.SendDoctorsNotificationActivity;
import com.chequp.admin.activity.SendPatientsNotificationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {
    View view;
    Context context;
    @BindView(R.id.tv_all)
    TextView tv_all;
    @BindView(R.id.tv_all_patient)
    TextView tv_all_patient;
    @BindView(R.id.tv_all_doc)
    TextView tv_all_doc;
    @BindView(R.id.tv_department)
    TextView tv_department;


    public NotificationFragment() {
        // Required empty public constructor
    }

    public static NotificationFragment newInstance() {
        NotificationFragment fragment = new NotificationFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //AllNotificationActivity
        view = inflater.inflate(R.layout.fragment_notification, container, false);
        context = view.getContext();
        ButterKnife.bind(this, view);
        tv_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, AllNotificationActivity.class));
            }
        });
        tv_all_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, SendPatientsNotificationActivity.class));
            }
        });
        tv_all_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, SendDoctorsNotificationActivity.class));
            }
        });
        tv_department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, SendDepartmentNotificationActivity.class));
            }
        });
        return view;
    }

}
