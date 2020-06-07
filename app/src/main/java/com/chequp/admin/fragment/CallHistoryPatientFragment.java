package com.chequp.admin.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chequp.admin.R;
import com.chequp.admin.adapter.CallLogHistoryAdapter;
import com.chequp.admin.api.Api;
import com.chequp.admin.api.ApiListener;
import com.chequp.admin.model.VideoCallHistoryModel;
import com.chequp.admin.widgets.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.chequp.admin.data.data.TOKEN;
import static com.chequp.admin.data.data.commonUserModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class CallHistoryPatientFragment extends Fragment implements ApiListener.VideoCallHistoryDownloadListenerPatient {
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.callTimeCount)
    TextView callTimeCount;
    Context contex;
    View view;


    public CallHistoryPatientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_call_history_patient, container, false);
        contex = view.getContext();
        ButterKnife.bind(this, view);
        Api.getInstance().getVideoCallSummery(TOKEN, "" + commonUserModel.getId(), "patient", this);

        return view;
    }

    @Override
    public void onVideoCallHistoryDownloadSuccess(List<VideoCallHistoryModel> data) {
        int count =0;
        if (data.size()>0) {
            for (int i = 0; i < data.size(); i++) {
                count += Integer.parseInt(data.get(i).getDuration());
            }
            callTimeCount.setText(String.valueOf(count)+" minutes");
            CallLogHistoryAdapter mAdapter = new CallLogHistoryAdapter(data);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(contex);
            recycler_view.setLayoutManager(mLayoutManager);
            recycler_view.setItemAnimator(new DefaultItemAnimator());
            recycler_view.addItemDecoration(new DividerItemDecoration(contex, LinearLayoutManager.VERTICAL, false));
            recycler_view.setAdapter(mAdapter);
        }

    }

    @Override
    public void onVideoCallHistoryDownloadFailed(String msg) {
        Toast.makeText(contex, msg, Toast.LENGTH_SHORT).show();


    }

}
