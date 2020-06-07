package com.chequp.admin.fragment;


import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chequp.admin.R;
import com.chequp.admin.adapter.DrLitsAdapterAdmin;
import com.chequp.admin.adapter.latestQueryLitsAdapterAdmin;
import com.chequp.admin.api.Api;
import com.chequp.admin.api.ApiListener;
import com.chequp.admin.model.PublicLatestQueryModel;
import com.chequp.admin.widgets.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.chequp.admin.data.data.loginResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public class QueryFragment extends Fragment implements ApiListener.LatestQueryDownloadListener{
    View view;
    Context context;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public static QueryFragment newInstance() {
        QueryFragment fragment = new QueryFragment();
        return fragment;
    }
    public QueryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_query, container, false);
        context=view.getContext();
        ButterKnife.bind(this,view);
        Api.getInstance().getLatestQuery("Bearer " + loginResponse.getAccessToken(),this);
        return view;
    }

    @Override
    public void onLatestQueryDownloadSuccess(List<PublicLatestQueryModel> list) {
        latestQueryLitsAdapterAdmin mAdapter = new latestQueryLitsAdapterAdmin(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onLatestQueryDownloadFailed(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }
}
