package com.chequp.admin.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chequp.admin.R;
import com.chequp.admin.adapter.PaymentListDoctorAdapter;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;



import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



/**
 * A simple {@link Fragment} subclass.
 */
public class AllCollectionFragment extends Fragment {
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    Context context;
    View view;
    boolean isLoaded = false ;

    public AllCollectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_all_collection, container, false);
        ButterKnife.bind(this, view);

        if (true) {


            PaymentListDoctorAdapter mAdapter = new PaymentListDoctorAdapter(UsersBillFragment.ALL_COLLECTION_WIDTHDRAWL.getBill_details());
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
            recycler_view.setLayoutManager(mLayoutManager);
            recycler_view.setItemAnimator(new DefaultItemAnimator());
            recycler_view.setAdapter(mAdapter);


            isLoaded = true ;
        }





        return view;
    }
}
