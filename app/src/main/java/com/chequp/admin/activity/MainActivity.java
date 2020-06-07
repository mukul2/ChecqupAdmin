package com.chequp.admin.activity;


import android.os.Bundle;

import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.chequp.admin.R;
import com.chequp.admin.fragment.AmbulanceFragment;
import com.chequp.admin.fragment.AreaFragment;
import com.chequp.admin.fragment.BlogCategoryFragments;
import com.chequp.admin.fragment.DepartmentListFragmentAdmin;
import com.chequp.admin.fragment.DrListFragmentAdmin;
import com.chequp.admin.fragment.MedicineListFragment;
import com.chequp.admin.fragment.NotificationFragment;
import com.chequp.admin.fragment.PatientListFragmentAdmin;
import com.chequp.admin.fragment.QueryFragment;
import com.chequp.admin.fragment.RatesFragment;
import com.chequp.admin.fragment.WitdhdrawRequestAllFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.linearDoc)
    LinearLayout linearDoc;
    @BindView(R.id.linearRates)
    LinearLayout linearRates;
    @BindView(R.id.linearBlogCategory)
    LinearLayout linearBlogCategory;
    @BindView(R.id.linearNotification)
    LinearLayout linearNotification;
    @BindView(R.id.linearpatient)
    LinearLayout linearpatient;
    @BindView(R.id.linearDepartment)
    LinearLayout linearDepartment;
    @BindView(R.id.linearQuery)
    LinearLayout linearQuery;
    @BindView(R.id.linearmedicine)
    LinearLayout linearmedicine;
    @BindView(R.id.linearAmbulance)
    LinearLayout linearAmbulance;
    @BindView(R.id.linearArea)
    LinearLayout linearArea;
    @BindView(R.id.linearWithdraw)
    LinearLayout linearWithdraw;
    float disAbleAlpha = 0.3f;
    float enAbleAlpha = 1.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        linearDepartment.setOnClickListener(this);
        linearpatient.setOnClickListener(this);
        linearDoc.setOnClickListener(this);
        linearQuery.setOnClickListener(this);
        linearmedicine.setOnClickListener(this);
        linearAmbulance.setOnClickListener(this);
        linearArea.setOnClickListener(this);
        linearNotification.setOnClickListener(this);
        linearBlogCategory.setOnClickListener(this);
        linearRates.setOnClickListener(this);
        linearWithdraw.setOnClickListener(this);
        initial_fragment();
    }

    private void initial_fragment() {
        Fragment selectedFragment = null;
        selectedFragment = DrListFragmentAdmin.newInstance();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, selectedFragment);
        transaction.commit();
        linearDoc.setAlpha(enAbleAlpha);
        linearpatient.setAlpha(disAbleAlpha);
        linearDepartment.setAlpha(disAbleAlpha);
        linearQuery.setAlpha(disAbleAlpha);
        linearmedicine.setAlpha(disAbleAlpha);
        linearAmbulance.setAlpha(disAbleAlpha);
        linearArea.setAlpha(disAbleAlpha);
        linearNotification.setAlpha(disAbleAlpha);
        linearBlogCategory.setAlpha(disAbleAlpha);
        linearRates.setAlpha(disAbleAlpha);
        linearWithdraw.setAlpha(disAbleAlpha);
    }

    @Override
    public void onClick(View view) {
        Fragment selectedFragment = null;
        switch (view.getId()) {
            case R.id.linearDoc:
                selectedFragment = DrListFragmentAdmin.newInstance();
                linearDoc.setAlpha(enAbleAlpha);
                linearpatient.setAlpha(disAbleAlpha);
                linearDepartment.setAlpha(disAbleAlpha);
                linearQuery.setAlpha(disAbleAlpha);
                linearmedicine.setAlpha(disAbleAlpha);
                linearAmbulance.setAlpha(disAbleAlpha);
                linearArea.setAlpha(disAbleAlpha);
                linearNotification.setAlpha(disAbleAlpha);
                linearRates.setAlpha(disAbleAlpha);
                linearWithdraw.setAlpha(disAbleAlpha);


                break;
            case R.id.linearpatient:
                selectedFragment = PatientListFragmentAdmin.newInstance();
                linearDoc.setAlpha(disAbleAlpha);
                linearpatient.setAlpha(enAbleAlpha);
                linearDepartment.setAlpha(disAbleAlpha);
                linearQuery.setAlpha(disAbleAlpha);
                linearmedicine.setAlpha(disAbleAlpha);
                linearAmbulance.setAlpha(disAbleAlpha);
                linearArea.setAlpha(disAbleAlpha);
                linearNotification.setAlpha(disAbleAlpha);
                linearBlogCategory.setAlpha(disAbleAlpha);
                linearRates.setAlpha(disAbleAlpha);
                linearWithdraw.setAlpha(disAbleAlpha);


                break;
            case R.id.linearDepartment:
                selectedFragment = DepartmentListFragmentAdmin.newInstance();
                linearDoc.setAlpha(disAbleAlpha);
                linearpatient.setAlpha(disAbleAlpha);
                linearDepartment.setAlpha(enAbleAlpha);
                linearQuery.setAlpha(disAbleAlpha);
                linearmedicine.setAlpha(disAbleAlpha);
                linearAmbulance.setAlpha(disAbleAlpha);
                linearArea.setAlpha(disAbleAlpha);
                linearNotification.setAlpha(disAbleAlpha);
                linearBlogCategory.setAlpha(disAbleAlpha);
                linearRates.setAlpha(disAbleAlpha);
                linearWithdraw.setAlpha(disAbleAlpha);


                break;
            case R.id.linearQuery:
                selectedFragment = QueryFragment.newInstance();
                linearDoc.setAlpha(disAbleAlpha);
                linearpatient.setAlpha(disAbleAlpha);
                linearDepartment.setAlpha(disAbleAlpha);
                linearQuery.setAlpha(enAbleAlpha);
                linearmedicine.setAlpha(disAbleAlpha);
                linearAmbulance.setAlpha(disAbleAlpha);
                linearArea.setAlpha(disAbleAlpha);
                linearNotification.setAlpha(disAbleAlpha);
                linearBlogCategory.setAlpha(disAbleAlpha);
                linearRates.setAlpha(disAbleAlpha);
                linearWithdraw.setAlpha(disAbleAlpha);


                break;
            case R.id.linearmedicine:
                selectedFragment = MedicineListFragment.newInstance();
                linearDoc.setAlpha(disAbleAlpha);
                linearpatient.setAlpha(disAbleAlpha);
                linearDepartment.setAlpha(disAbleAlpha);
                linearQuery.setAlpha(disAbleAlpha);
                linearmedicine.setAlpha(enAbleAlpha);
                linearAmbulance.setAlpha(disAbleAlpha);
                linearArea.setAlpha(disAbleAlpha);
                linearNotification.setAlpha(disAbleAlpha);
                linearBlogCategory.setAlpha(disAbleAlpha);
                linearRates.setAlpha(disAbleAlpha);
                linearWithdraw.setAlpha(disAbleAlpha);


                break;
            case R.id.linearAmbulance:
                selectedFragment = AmbulanceFragment.newInstance();
                linearDoc.setAlpha(disAbleAlpha);
                linearpatient.setAlpha(disAbleAlpha);
                linearDepartment.setAlpha(disAbleAlpha);
                linearQuery.setAlpha(disAbleAlpha);
                linearmedicine.setAlpha(disAbleAlpha);
                linearAmbulance.setAlpha(enAbleAlpha);
                linearArea.setAlpha(disAbleAlpha);
                linearNotification.setAlpha(disAbleAlpha);
                linearBlogCategory.setAlpha(disAbleAlpha);
                linearRates.setAlpha(disAbleAlpha);
                linearWithdraw.setAlpha(disAbleAlpha);


                break;

            case R.id.linearArea:
                selectedFragment = AreaFragment.newInstance();
                linearDoc.setAlpha(disAbleAlpha);
                linearpatient.setAlpha(disAbleAlpha);
                linearDepartment.setAlpha(disAbleAlpha);
                linearQuery.setAlpha(disAbleAlpha);
                linearmedicine.setAlpha(disAbleAlpha);
                linearAmbulance.setAlpha(disAbleAlpha);
                linearArea.setAlpha(enAbleAlpha);
                linearNotification.setAlpha(disAbleAlpha);
                linearBlogCategory.setAlpha(disAbleAlpha);
                linearRates.setAlpha(disAbleAlpha);
                linearWithdraw.setAlpha(disAbleAlpha);


                break;
            case R.id.linearNotification:
                selectedFragment = NotificationFragment.newInstance();
                linearDoc.setAlpha(disAbleAlpha);
                linearpatient.setAlpha(disAbleAlpha);
                linearDepartment.setAlpha(disAbleAlpha);
                linearQuery.setAlpha(disAbleAlpha);
                linearmedicine.setAlpha(disAbleAlpha);
                linearAmbulance.setAlpha(disAbleAlpha);
                linearArea.setAlpha(disAbleAlpha);
                linearNotification.setAlpha(enAbleAlpha);
                linearBlogCategory.setAlpha(disAbleAlpha);
                linearRates.setAlpha(disAbleAlpha);
                linearWithdraw.setAlpha(disAbleAlpha);


                break;
            case R.id.linearBlogCategory:
                selectedFragment = BlogCategoryFragments.newInstance();
                linearDoc.setAlpha(disAbleAlpha);
                linearpatient.setAlpha(disAbleAlpha);
                linearDepartment.setAlpha(disAbleAlpha);
                linearQuery.setAlpha(disAbleAlpha);
                linearmedicine.setAlpha(disAbleAlpha);
                linearAmbulance.setAlpha(disAbleAlpha);
                linearArea.setAlpha(disAbleAlpha);
                linearNotification.setAlpha(disAbleAlpha);
                linearBlogCategory.setAlpha(enAbleAlpha);
                linearRates.setAlpha(disAbleAlpha);
                linearWithdraw.setAlpha(disAbleAlpha);


                break;
            case R.id.linearRates:
                selectedFragment = RatesFragment.newInstance();
                linearDoc.setAlpha(disAbleAlpha);
                linearpatient.setAlpha(disAbleAlpha);
                linearDepartment.setAlpha(disAbleAlpha);
                linearQuery.setAlpha(disAbleAlpha);
                linearmedicine.setAlpha(disAbleAlpha);
                linearAmbulance.setAlpha(disAbleAlpha);
                linearArea.setAlpha(disAbleAlpha);
                linearNotification.setAlpha(disAbleAlpha);
                linearBlogCategory.setAlpha(disAbleAlpha);
                linearRates.setAlpha(enAbleAlpha);
                linearWithdraw.setAlpha(enAbleAlpha);


                break;

            case R.id.linearWithdraw:
                selectedFragment = WitdhdrawRequestAllFragment.newInstance();
                linearDoc.setAlpha(disAbleAlpha);
                linearpatient.setAlpha(disAbleAlpha);
                linearDepartment.setAlpha(disAbleAlpha);
                linearQuery.setAlpha(disAbleAlpha);
                linearmedicine.setAlpha(disAbleAlpha);
                linearAmbulance.setAlpha(disAbleAlpha);
                linearArea.setAlpha(disAbleAlpha);
                linearNotification.setAlpha(disAbleAlpha);
                linearBlogCategory.setAlpha(disAbleAlpha);
                linearRates.setAlpha(disAbleAlpha);
                linearWithdraw.setAlpha(enAbleAlpha);


                break;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, selectedFragment);
        transaction.commit();

    }
}
