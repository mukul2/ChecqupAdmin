package com.chequp.admin.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.chequp.admin.R;
import com.chequp.admin.Utils.MyProgressBar;
import com.chequp.admin.api.Api;
import com.chequp.admin.api.ApiListener;
import com.chequp.admin.model.StatusMessage;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.chequp.admin.data.data.TOKEN;

public class AllNotificationActivity extends AppCompatActivity {
    @BindView(R.id.cardSubmit)
    CardView cardSubmit;
    @BindView(R.id.ed_body)
    EditText ed_body;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_notification);
        ButterKnife.bind(this);

        cardSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String body = ed_body.getText().toString().trim();
                if (body.length() > 0) {
                    MyProgressBar.with(context);
                    Api.getInstance().notice_add_all_user(TOKEN, body, new ApiListener.NotificationPostListener() {
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
        });
    }


}
