package com.chequp.admin.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.chequp.admin.R;
import com.chequp.admin.api.Api;
import com.chequp.admin.api.ApiListener;
import com.chequp.admin.fragment.BasicCommonInfoFragment;
import com.chequp.admin.fragment.CallHistoryPatientFragment;
import com.chequp.admin.fragment.ChamberFragment;
import com.chequp.admin.fragment.DrConfirmedAppointmentsFragment;
import com.chequp.admin.fragment.DrDocumentsFragment;
import com.chequp.admin.fragment.DrPendingAppointmentsFragment;
import com.chequp.admin.fragment.DrServedAppointmentsFragment;
import com.chequp.admin.fragment.DrTestRecommendedAppointmentsFragment;
import com.chequp.admin.fragment.EducationFragment;
import com.chequp.admin.fragment.OnlineFragmentDr;
import com.chequp.admin.fragment.PrescriptionReviewedFragment;
import com.chequp.admin.fragment.ServedPrescriptionRequestsFragment;
import com.chequp.admin.fragment.SkillInfo;
import com.chequp.admin.fragment.SpecialSkillFragment;
import com.chequp.admin.fragment.UsersBillFragment;
import com.chequp.admin.model.ChamberInfo;
import com.chequp.admin.model.DrEduChInfoModel;
import com.chequp.admin.model.EducationInfo;
import com.chequp.admin.model.Status;
import com.chequp.admin.model.StatusMessage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.chequp.admin.data.data.TOKEN;
import static com.chequp.admin.data.data.commonUserModel;

public class DoctorProfileActivity extends AppCompatActivity implements ApiListener.drChamberEduSkillDownloadListener, ApiListener.StatusDownloadListener {
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.status)
    Switch status;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    Context context = this;
    public static List<SkillInfo> SKILLS = new ArrayList<>();
    public static List<EducationInfo> EDUCATION = new ArrayList<>();
    public static List<ChamberInfo> CHAMBERLIST = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);
        ButterKnife.bind(this);
        // commonUserModel
        int selectedColor = context.getResources().getColor(R.color.black);
        int normal = context.getResources().getColor(R.color.textText);
        tabs.setTabTextColors(normal, selectedColor);
        tabs.setSelectedTabIndicatorColor(context.getResources().getColor(R.color.colorPrimary));


        Api.getInstance().getEduSKillChamber(TOKEN, "" + commonUserModel.getId(), this);
        Api.getInstance().get_user_status(TOKEN, "" + commonUserModel.getId(), this);


    }

    @Override
    public void onChamberEduSkillDownloadSuccess(DrEduChInfoModel list) {
        Gson gson = new Gson();
        SKILLS = list.getSkillInfo();
        EDUCATION = list.getEducationInfo();
        CHAMBERLIST = list.getChamberInfo();
        setupViewPager(viewPager);
        tabs.setupWithViewPager(viewPager);


    }

    @Override
    public void onChamberEduSkillDownloadFailed(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new BasicCommonInfoFragment(), "Basic Info");
        if (commonUserModel.getUserType().equals("d")) {
            adapter.addFragment(new DrDocumentsFragment(), "Documents");
            adapter.addFragment(new EducationFragment(), "Education");
            adapter.addFragment(new SpecialSkillFragment(), "Skill");
            adapter.addFragment(new ChamberFragment(), "Chamber");
            adapter.addFragment(new OnlineFragmentDr(), "Online Services");
            adapter.addFragment(new DrPendingAppointmentsFragment(), "Pending Appointments");
            adapter.addFragment(new DrConfirmedAppointmentsFragment(), "Confirmed Appointments");
            adapter.addFragment(new DrTestRecommendedAppointmentsFragment(), "Test recomended Appointments");
            adapter.addFragment(new DrServedAppointmentsFragment(), "Served Appointments");
        } else {

            adapter.addFragment(new CallHistoryPatientFragment(), "Call History");

        }
        adapter.addFragment(new ServedPrescriptionRequestsFragment(), "Served Prescription Requests");
        adapter.addFragment(new PrescriptionReviewedFragment(), "Reviewd Prescription");
        adapter.addFragment(new UsersBillFragment(), "Bill Report");


        viewPager.setAdapter(adapter);
    }

    @Override
    public void onStatusDownloadSuccess(Status response) {
        if (response.isStatus() == 1) {
            status.setChecked(true);
        } else {
            status.setChecked(false);
        }
        ApiListener.ProfileUpdateListener listener = new ApiListener.ProfileUpdateListener() {
            @Override
            public void onProfileUpdateSuccess(StatusMessage response) {

            }

            @Override
            public void onProfileUpdateFailed(String msg) {

            }
        };

        status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    Api.getInstance().update_user_status(TOKEN, "" + commonUserModel.getId(), "1", listener);
                } else {
                    Api.getInstance().update_user_status(TOKEN, "" + commonUserModel.getId(), "0", listener);

                }
            }
        });
    }

    @Override
    public void onStatusDownloadFailed(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

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
