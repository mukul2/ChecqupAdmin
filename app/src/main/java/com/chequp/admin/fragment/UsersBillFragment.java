package com.chequp.admin.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chequp.admin.R;
import com.chequp.admin.Utils.GeneralListener;
import com.chequp.admin.adapter.AmbulanceLitsAdapterAdmin;
import com.chequp.admin.adapter.BillsLitsAdapterAdmin;
import com.chequp.admin.api.Api;
import com.chequp.admin.api.ApiListener;
import com.chequp.admin.model.AllCollectionWithdraModel;
import com.chequp.admin.model.BillItem;
import com.chequp.admin.model.BillSummery;
import com.chequp.admin.widgets.DividerItemDecoration;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.chequp.admin.data.data.CURRENCY_USD_SIGN;
import static com.chequp.admin.data.data.TOKEN;
import static com.chequp.admin.data.data.commonUserModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersBillFragment extends Fragment  {
    View v;

    @BindView(R.id.tv_all_col)
    TextView tv_all_col;
    @BindView(R.id.cardTwo)
    CardView cardTwo;
    @BindView(R.id.cardThree)
    CardView cardThree;
    @BindView(R.id.tv_all_withd)
    TextView tv_all_withd;
    @BindView(R.id.tv_wallet)
    TextView tv_wallet;
    @BindView(R.id.tv_three)
    TextView tv_three;
    @BindView(R.id.tv_two)
    TextView tv_two;
    @BindView(R.id.tv_one)
    TextView tv_one;
    Context context;

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tabs)
    TabLayout tabs;

    public  static  AllCollectionWithdraModel ALL_COLLECTION_WIDTHDRAWL ;
    public UsersBillFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //yearlySingleUserBillList
        v = inflater.inflate(R.layout.fragment_users_bill, container, false);
        context = v.getContext();
        ButterKnife.bind(this, v);


        GeneralListener.setNeedRefresh(new GeneralListener.youNeedRefresh() {
            @Override
            public void doRefresh(int value) {
                initAPIhit();

            }
        });
        initAPIhit();

        return v;
    }

    private void initAPIhit() {
        if (commonUserModel.getUserType().equals("d")){
            tv_one.setText("Earnings");
            tv_two.setText("Withdrawed");
            tv_three.setText("In Wallet");
        }else {
            tv_one.setText("Total Spent");
        }
        String USER_TYPE_ = "";
        if (commonUserModel.getUserType().equals("d")) {
            USER_TYPE_ = "doctor";
        } else {
            cardThree.setVisibility(View.GONE);
            cardTwo.setVisibility(View.GONE);
            USER_TYPE_ = "patient";
        }
        Api.getInstance().get_payment_list(TOKEN, "" + commonUserModel.getId(), USER_TYPE_, new ApiListener.PaymentListDownloadListener() {
            @Override
            public void onPaymentListDownloadSuccess(AllCollectionWithdraModel response) {
                tv_all_col.setText(""+response.getTotal_bill()+ CURRENCY_USD_SIGN);
                if (commonUserModel.getUserType().equals("d")) {
                    tv_all_withd.setText(""+response.getAll_widthdraw()+ CURRENCY_USD_SIGN);
                    tv_wallet.setText(""+(response.getTotal_bill()-response.getAll_widthdraw())+ CURRENCY_USD_SIGN);
                }

                ALL_COLLECTION_WIDTHDRAWL = response ;
                setupViewPager(viewpager);
                int selectedColor=context.getResources().getColor(R.color.black);
                int normal=context.getResources().getColor(R.color.textText);
                tabs.setTabTextColors(normal,selectedColor);
                tabs.setupWithViewPager(viewpager);
                //Toast.makeText(context, ""+response.getTotal_bill(), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onPaymentListDownloadFailed(String msg) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();


            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

        if (commonUserModel.getUserType().equals("d")) {
            adapter.addFragment(new AllCollectionFragment(), "Collections");
            adapter.addFragment(new AllWidthdrawFragment(), "Withdrawal");
        }else {
            adapter.addFragment(new AllCollectionFragment(), "Payments");
        }

        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
