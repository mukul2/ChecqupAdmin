package com.chequp.admin.activity;

import android.content.Context;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chequp.admin.R;
import com.chequp.admin.Utils.MyProgressBar;
import com.chequp.admin.adapter.ChatAdapterSupportTeam;
import com.chequp.admin.api.Api;
import com.chequp.admin.api.ApiListener;
import com.chequp.admin.model.QueryModel;
import com.chequp.admin.model.StatusMessage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.chequp.admin.data.data.QUERY_MODEL;
import static com.chequp.admin.data.data.TOKEN;
import static com.chequp.admin.data.data.loginResponse;


public class PublicQueryChatActivity extends AppCompatActivity implements ApiListener.publicQueryPostListenerPatient, ApiListener.publicQueryDownloadListenerPatient {
    @BindView(R.id.ed_message)
    EditText ed_message;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    Context context = this;

    List<QueryModel> queryModels = new ArrayList<>();
    int OPPOSITE_ID;

    ChatAdapterSupportTeam mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_query_chat);
        ButterKnife.bind(this);
        //publicQueryPOst

        initRecycler();
        Api.getInstance().getMyAllQuery(TOKEN, "" + QUERY_MODEL.getMessageSenderId(), "" + QUERY_MODEL.getMessageReceiverId(), this);
    }

    private void initRecycler() {
        mAdapter = new ChatAdapterSupportTeam(context, queryModels);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(mAdapter);
    }

    public void back(View view) {
        onBackPressed();
    }

    public void send(View view) {
        String message = ed_message.getText().toString().trim();
        if (message.length() > 0) {
            MyProgressBar.with(context);
            Api.getInstance().publicQueryPOst(TOKEN, message, "" + loginResponse.getUserInfo().getId(), "" + OPPOSITE_ID, this);

        }

    }

    @Override
    public void onPublicQueryPostSuccess(StatusMessage data) {
        MyProgressBar.dismiss();

        // Toast.makeText(context, data.getMessage(), Toast.LENGTH_SHORT).show();
        Api.getInstance().appNotification(String.valueOf(OPPOSITE_ID),"Xplore BD","Reply From admin","adminResponse","null","not_matter");
        ed_message.setText("");


        Api.getInstance().getMyAllQuery(TOKEN, "" + loginResponse.getUserInfo().getId(), "" + OPPOSITE_ID, this);

    }

    @Override
    public void onPublicQueryPostFailed(String msg) {
        MyProgressBar.dismiss();
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPublicQueryDownloadSuccess(List<QueryModel> data) {
        queryModels.clear();
        queryModels.addAll(data);
        mAdapter.notifyDataSetChanged();
        recycler_view.smoothScrollToPosition(data.size() - 1);


        if (data.get(0).getMessage_receiver_id() == loginResponse.getUserInfo().getId()) {
            OPPOSITE_ID = data.get(0).getMessage_sender_id();
        } else {
            OPPOSITE_ID = data.get(0).getMessage_receiver_id();

        }

    }

    @Override
    public void onPublicQueryDownloadFailed(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }
}
