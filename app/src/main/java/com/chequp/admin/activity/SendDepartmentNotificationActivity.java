package com.chequp.admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.chequp.admin.R;
import com.chequp.admin.Utils.MyProgressBar;
import com.chequp.admin.adapter.DepartmentsAdapter;
import com.chequp.admin.api.Api;
import com.chequp.admin.api.ApiListener;
import com.chequp.admin.model.DeptModel;
import com.chequp.admin.model.StatusMessage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.chequp.admin.data.data.TOKEN;
import static com.chequp.admin.data.data.loginResponse;

public class SendDepartmentNotificationActivity extends AppCompatActivity implements ApiListener.DeptDownloadListener {
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.ed_body)
    EditText ed_body;
    @BindView(R.id.cardSubmit)
    CardView cardSubmit;
    Context context = this;
    List<DeptModel> all = new ArrayList<>();
    String selected_dept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_department_notification);
        ButterKnife.bind(this);
        downloadDepartments();

    }

    private void downloadDepartments() {
        Api.getInstance().getDepList(TOKEN, this);

    }

    @Override
    public void onDepartmentDownloadSuccess(List<DeptModel> list) {
        all = list;
        List<String> departments = new ArrayList<>();
        departments.add("Select");
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                departments.add(list.get(i).getName());

            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, departments);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(dataAdapter);


            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i > 0) {
                        selected_dept = "" + all.get(i - 1).getId();
                    } else {
                        selected_dept = null;
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
        init_button();


    }

    private void init_button() {
        cardSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String body = ed_body.getText().toString().trim();
                if (body.length() > 0) {
                    if (selected_dept != null) {
                        MyProgressBar.with(context);
                        Api.getInstance().notice_add_by_department_type(TOKEN, selected_dept, body, new ApiListener.NotificationPostListener() {
                            @Override
                            public void onNotificationPostSuccess(StatusMessage list) {
                                MyProgressBar.dismiss();
                                if (list != null) {
                                    Toast.makeText(context, list.getMessage(), Toast.LENGTH_SHORT).show();
                                    onBackPressed();
                                } else {
                                    Toast.makeText(context, "Error occured", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onNotificationPostFailed(String msg) {
                                MyProgressBar.dismiss();

                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                }
            }
        });
    }

    @Override
    public void onDepartmentDownloadFailed(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }
}
