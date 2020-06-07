package com.chequp.admin.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chequp.admin.R;
import com.chequp.admin.Utils.GeneralListener;
import com.chequp.admin.adapter.PendingAppointmentAdapterDoctor;
import com.chequp.admin.adapter.ReviewPrecriptionsAdapterPatient;
import com.chequp.admin.api.Api;
import com.chequp.admin.api.ApiListener;
import com.chequp.admin.model.AppointmentModelNew;
import com.chequp.admin.model.PrescriptionReviewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.chequp.admin.data.data.TOKEN;
import static com.chequp.admin.data.data.commonUserModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrescriptionReviewedFragment extends Fragment implements ApiListener.ReviewRequestDownloadListener {
    Context context;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.pendingCount)
    TextView pendingCount;
    View view;

    public PrescriptionReviewedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_dr_pending_appointments, container, false);
        ButterKnife.bind(this, view);
        context = view.getContext();

        String userType;
        if (commonUserModel.getUserType().equals("d")) {
            userType = "doctor";


        } else {
            userType = "patient";

        }
        Api.getInstance().getReviewedRequests(TOKEN, "" + commonUserModel.getId(), userType, this);


        return view;
    }

    @Override
    public void onReviewRequestDownloadSuccess(List<PrescriptionReviewModel> response) {
        if (response!=null&&response.size()>0) {
            ReviewPrecriptionsAdapterPatient mAdapter = new ReviewPrecriptionsAdapterPatient(response);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
            StaggeredGridLayoutManager _sGridLayoutManager = new StaggeredGridLayoutManager(2,
                    StaggeredGridLayoutManager.VERTICAL);
            recycler_view.setLayoutManager(_sGridLayoutManager);
            recycler_view.setItemAnimator(new DefaultItemAnimator());
            recycler_view.setAdapter(mAdapter);
            pendingCount.setText(""+response.size());
        }

    }

    @Override
    public void onReviewRequestDownloadFailed(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }


}
