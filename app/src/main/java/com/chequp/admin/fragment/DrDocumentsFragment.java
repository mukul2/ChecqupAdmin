package com.chequp.admin.fragment;


import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.chequp.admin.R;
import com.chequp.admin.adapter.DocumentLitsAdapterDoctor;
import com.chequp.admin.api.Api;
import com.chequp.admin.api.ApiListener;
import com.chequp.admin.model.DocumentModel;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;
import static com.chequp.admin.data.data.TOKEN;
import static com.chequp.admin.data.data.commonUserModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrDocumentsFragment extends Fragment implements ApiListener.DoctorDocDownloadListener {
    View view;

    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    Context context;


    public DrDocumentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_dr_documents, container, false);
        ButterKnife.bind(this, view);
        context = view.getContext();
        Api.getInstance().getAllDocumentOFSingleDoc(TOKEN, "" + commonUserModel.getId(), this);
        return view;
    }


    @Override
    public void onDoctorDocDownloadSuccess(List<DocumentModel> data) {
        //DocumentLitsAdapterDoctor


        //

        LinearLayoutManager layoutManager
                = new GridLayoutManager(context, 2);
        recycler_view.setLayoutManager(layoutManager);
        DocumentLitsAdapterDoctor mAdapter = new DocumentLitsAdapterDoctor(data);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(mAdapter);
    }

    @Override
    public void onDoctorDocDownloadFailed(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }
}
