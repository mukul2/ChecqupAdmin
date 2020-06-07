package com.chequp.admin.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chequp.admin.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.chequp.admin.data.data.IMAGE_BASE;
import static com.chequp.admin.data.data.commonUserModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class BasicCommonInfoFragment extends Fragment {

    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_display_title)
    TextView tv_display_title;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_email)
    TextView tv_email;
    @BindView(R.id.liner_dr_only)
    LinearLayout liner_dr_only;

    @BindView(R.id.image)
    ImageView image;
    Context context;
    View view;

    public BasicCommonInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_basic_common_info, container, false);
        context = view.getContext();
        ButterKnife.bind(this,view);
        Glide.with(context).load(IMAGE_BASE + commonUserModel.getPhoto()).into(image);
        tv_name.setText(commonUserModel.getName());
        tv_display_title.setText(commonUserModel.getDesignation_title());
        tv_phone.setText(commonUserModel.getPhone());
        tv_name.setText(commonUserModel.getName());
        tv_email.setText(commonUserModel.getEmail());
        if (commonUserModel.getUserType().equals("d")){
            liner_dr_only.setVisibility(View.VISIBLE);
        }else {
            liner_dr_only.setVisibility(View.GONE);

        }

        return view;
    }

}
