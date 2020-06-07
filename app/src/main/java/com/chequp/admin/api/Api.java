package com.chequp.admin.api;


import androidx.annotation.NonNull;

import com.chequp.admin.model.AllCollectionWithdraModel;
import com.chequp.admin.model.AmbulanceModel;
import com.chequp.admin.model.AppointmentModelNew;
import com.chequp.admin.model.BillItem;
import com.chequp.admin.model.BillSummery;
import com.chequp.admin.model.BlogCategoryNameID;
import com.chequp.admin.model.ChatModel;
import com.chequp.admin.model.CommonUserModel;
import com.chequp.admin.model.Data;
import com.chequp.admin.model.DeptModel;
import com.chequp.admin.model.DistrictModel;
import com.chequp.admin.model.DocumentModel;
import com.chequp.admin.model.DrEduChInfoModel;
import com.chequp.admin.model.DrOnlineServiceModel;
import com.chequp.admin.model.LoginResponse;
import com.chequp.admin.model.MedicineModel;
import com.chequp.admin.model.NotiModel;
import com.chequp.admin.model.NotificationResponse;
import com.chequp.admin.model.PrescriptionRequestModel;
import com.chequp.admin.model.PrescriptionReviewModel;
import com.chequp.admin.model.PublicLatestQueryModel;
import com.chequp.admin.model.QueryModel;
import com.chequp.admin.model.ServiceNameInfo;
import com.chequp.admin.model.ServiceNameRate;
import com.chequp.admin.model.Status;
import com.chequp.admin.model.StatusMessage;
import com.chequp.admin.model.VideoCallHistoryModel;
import com.chequp.admin.model.WitdhdrawFull;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created By TAOHID on 9/6/18.
 */
public class Api {

    private static Api dataManager = null;
    String fcmKey = "key=AAAA0EpRwPY:APA91bHBbBup11jcpJ65yZKqUqkUK5IPDUN9O51ade_qcoFKZdqyUuiK07v3mFSUmrA2ZAEP1M0zV09a794SZPOlmvbvDAOHN5cNdKNst0aCMq4WJIKbhDMWPK0ks-obO7rUd_vgTGIn";

    public static Api getInstance() {

        if (dataManager == null) {
            dataManager = new Api();
        }

        return dataManager;
    }
    public void get_payment_list(String token, String id, String user_type, final ApiListener.PaymentListDownloadListener listener) {
        ApiClient.getApiInterface().get_payment_list(token, id, user_type).enqueue(new Callback<AllCollectionWithdraModel>() {
            @Override
            public void onResponse(@NonNull Call<AllCollectionWithdraModel> call, @NonNull Response<AllCollectionWithdraModel> response) {
                if (response != null) {
                    listener.onPaymentListDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<AllCollectionWithdraModel> call, @NonNull Throwable t) {
                listener.onPaymentListDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void all_withdrawal_request(String token, final ApiListener.AllWitdhdrawListtListDownloadListener listener) {
        ApiClient.getApiInterface().all_withdrawal_request(token).enqueue(new Callback<List<WitdhdrawFull>>() {
            @Override
            public void onResponse(@NonNull Call<List<WitdhdrawFull>> call, @NonNull Response<List<WitdhdrawFull>> response) {
                if (response != null) {
                    listener.onAllWitdhdrawListDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<WitdhdrawFull>> call, @NonNull Throwable t) {
                listener.onAllWitdhdrawListDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void update_withdrawal_request(String token, String id, String status, final ApiListener.BasicApiListener listener) {
        ApiClient.getApiInterface().update_withdrawal_request(token, id, status).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onBasicAPISuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onBasicAPIFailed(t.getLocalizedMessage());
            }
        });
    }
    public void getADoctorsServices(String token, String id, final ApiListener.DrServicesDownloadListener listener) {

        ApiClient.getApiInterface().getAllServiceByDr(token, id).enqueue(new Callback<List<DrOnlineServiceModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<DrOnlineServiceModel>> call, @NonNull Response<List<DrOnlineServiceModel>> response) {
                if (response != null) {
                    listener.onDrServicesDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<DrOnlineServiceModel>> call, @NonNull Throwable t) {
                listener.onDrServicesDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getAllServices(String token, final ApiListener.AllServiceDownloadListener listener) {

        ApiClient.getApiInterface().getAllOnlineServices(token).enqueue(new Callback<List<ServiceNameInfo>>() {
            @Override
            public void onResponse(@NonNull Call<List<ServiceNameInfo>> call, @NonNull Response<List<ServiceNameInfo>> response) {
                if (response != null) {
                    listener.onAllServiceDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<ServiceNameInfo>> call, @NonNull Throwable t) {
                listener.onAllServiceDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getEduSKillChamber(String key, String id, final ApiListener.drChamberEduSkillDownloadListener listener) {
        ApiClient.getApiInterface().getSkillChamberEdu(key, id).enqueue(new Callback<DrEduChInfoModel>() {
            @Override
            public void onResponse(Call<DrEduChInfoModel> call, Response<DrEduChInfoModel> response) {
                listener.onChamberEduSkillDownloadSuccess(response.body());

            }

            @Override
            public void onFailure(Call<DrEduChInfoModel> call, Throwable t) {
                listener.onChamberEduSkillDownloadFailed(t.getLocalizedMessage());

            }
        });

    }

    public void getAllDocumentOFSingleDoc(String KEY, String id, final ApiListener.DoctorDocDownloadListener listener) {

        ApiClient.getApiInterface().getAllDocumentsBySingleDoctor(KEY, id).enqueue(new Callback<List<DocumentModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<DocumentModel>> call, @NonNull Response<List<DocumentModel>> response) {
                if (response != null) {
                    listener.onDoctorDocDownloadSuccess(response.body());


                }

            }

            @Override
            public void onFailure(@NonNull Call<List<DocumentModel>> call, @NonNull Throwable t) {
                listener.onDoctorDocDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void loginUser(String mobile, String password, final ApiListener.LoginListener listener) {

        ApiClient.getApiInterface().login(mobile, password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                if (response != null) {
                    listener.onLoginSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                listener.onLoginFailed(t.getLocalizedMessage());
            }
        });
    }

    public void appNotification(String targetuser, String title, String body, String intent, String image, String targetUserType) {
        String recepent = "/topics/" + targetuser;


        Data data = new Data(title, body, intent, image, targetUserType);
        NotiModel notificationModel = new NotiModel(recepent, data);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fcm.googleapis.com/fcm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        apiInterface.newmsg(fcmKey, notificationModel).enqueue(new Callback<NotificationResponse>() {
            @Override
            public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {
                // MyDialog.getInstance().with(ChatActivity.this).message("success msg"+response.message()).show();
            }

            @Override
            public void onFailure(Call<NotificationResponse> call, Throwable t) {
                // MyDialog.getInstance().with(findLawyerActivity).message("error msg "+t.getMessage()).show();


            }
        });
    }

    public void notice_add_all_user(String token, String msg, final ApiListener.NotificationPostListener listener) {

        ApiClient.getApiInterface().notice_add_all_user(token, msg).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onNotificationPostSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onNotificationPostFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getServiceNameRate(String token, final ApiListener.ServiceNameRateDownloadListener listener) {

        ApiClient.getApiInterface().getServiceNameRate(token).enqueue(new Callback<List<ServiceNameRate>>() {
            @Override
            public void onResponse(@NonNull Call<List<ServiceNameRate>> call, @NonNull Response<List<ServiceNameRate>> response) {
                if (response != null) {
                    listener.onServiceNameRateDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<ServiceNameRate>> call, @NonNull Throwable t) {
                listener.onServiceNameRateDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void yearlySingleUserBillList(String token, String user_id, String year, final ApiListener.UserBillDownloadListener listener) {

        ApiClient.getApiInterface().yearlySingleUserBillList(token, user_id, year).enqueue(new Callback<List<BillItem>>() {
            @Override
            public void onResponse(@NonNull Call<List<BillItem>> call, @NonNull Response<List<BillItem>> response) {
                if (response != null) {
                    listener.onUserBillDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<BillItem>> call, @NonNull Throwable t) {
                listener.onUserBillDownloadFailed(t.getLocalizedMessage());
            }
        });
    }
    public void yearlySingleUserBillSummery(String token, String user_id, String year, final ApiListener.UserBillSummeryDownloadListener listener) {

        ApiClient.getApiInterface().yearlySingleUserBillSummery(token, user_id, year).enqueue(new Callback<BillSummery>() {
            @Override
            public void onResponse(@NonNull Call<BillSummery> call, @NonNull Response<BillSummery> response) {
                if (response != null) {
                    listener.onUserBillSummeryDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<BillSummery> call, @NonNull Throwable t) {
                listener.onUserBillSummeryDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void notice_add_by_user_type(String token, String user_type, String msg, final ApiListener.NotificationPostListener listener) {

        ApiClient.getApiInterface().notice_add_by_user_type(token, user_type, msg).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onNotificationPostSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onNotificationPostFailed(t.getLocalizedMessage());
            }
        });
    }

    public void addBlogCategory(String token, String name, final ApiListener.BlogCategoryPostListener listener) {

        ApiClient.getApiInterface().addBlogCategory(token, name).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onBlogCategoryPostSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onBlogCategoryPostFailed(t.getLocalizedMessage());
            }
        });
    }

    public void notice_add_by_department_type(String token, String dept_id, String msg, final ApiListener.NotificationPostListener listener) {

        ApiClient.getApiInterface().notice_add_by_department_type(token, dept_id, msg).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onNotificationPostSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onNotificationPostFailed(t.getLocalizedMessage());
            }
        });
    }

    public void BlogCategoryNameID(String token, final ApiListener.BlogCategoryDownloadListener doctorSearchListener) {
        ApiClient.getApiInterface().getBlogChamber(token).enqueue(new Callback<List<BlogCategoryNameID>>() {
            @Override
            public void onResponse(Call<List<BlogCategoryNameID>> call, Response<List<BlogCategoryNameID>> response) {
                doctorSearchListener.onBlogCategoryDownloadSuccess(response.body());

            }

            @Override
            public void onFailure(Call<List<BlogCategoryNameID>> call, Throwable t) {
                doctorSearchListener.onBlogCategoryDownloadFailed(t.getLocalizedMessage());

            }
        });

    }

    public void add_medicine_info(String token, String name, String manufacture, String type, String description, final ApiListener.MedicicinePostListener listener) {

        ApiClient.getApiInterface().add_medicine_info(token, name, manufacture, type, description).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onMedicicinePostSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onMedicicinePostFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getUsersList(String token, String userType, final ApiListener.UserDownloadListener listener) {

        ApiClient.getApiInterface().downloadUsersByType(token, userType).enqueue(new Callback<List<CommonUserModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<CommonUserModel>> call, @NonNull Response<List<CommonUserModel>> response) {
                if (response != null) {
                    listener.onUserDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<CommonUserModel>> call, @NonNull Throwable t) {
                listener.onUserDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void addDepartment(String token, String addDepartment, final ApiListener.DepartmentAddListener listener) {

        ApiClient.getApiInterface().addDepartment(token, addDepartment).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onDepartmentAddSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onDepartmentAddFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getMyAllQuery(String token, String message_sender_id, String message_receiver_id, final ApiListener.publicQueryDownloadListenerPatient listener) {

        ApiClient.getApiInterface().getMyQueries(token, message_sender_id, message_receiver_id).enqueue(new Callback<List<QueryModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<QueryModel>> call, @NonNull Response<List<QueryModel>> response) {
                if (response != null) {
                    listener.onPublicQueryDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<QueryModel>> call, @NonNull Throwable t) {
                listener.onPublicQueryDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getReviewedRequests(String token, String id, String type, final ApiListener.ReviewRequestDownloadListener listener) {

        ApiClient.getApiInterface().getMyReviewedRecheckRequests(token, id, type).enqueue(new Callback<List<PrescriptionReviewModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<PrescriptionReviewModel>> call, @NonNull Response<List<PrescriptionReviewModel>> response) {
                if (response != null) {
                    listener.onReviewRequestDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<PrescriptionReviewModel>> call, @NonNull Throwable t) {
                listener.onReviewRequestDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getMyReviewedRecheckRequests(String token, String id, String status, final ApiListener.ReviewRequestDownloadListener listener) {

        ApiClient.getApiInterface().getMyReviewedRecheckRequests(token, id, status).enqueue(new Callback<List<PrescriptionReviewModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<PrescriptionReviewModel>> call, @NonNull Response<List<PrescriptionReviewModel>> response) {
                if (response != null) {
                    listener.onReviewRequestDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<PrescriptionReviewModel>> call, @NonNull Throwable t) {
                listener.onReviewRequestDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void update_user_status(String token, String id, String status, final ApiListener.ProfileUpdateListener listener) {

        ApiClient.getApiInterface().update_user_status(token, id, status).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onProfileUpdateSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onProfileUpdateFailed(t.getLocalizedMessage());
            }
        });
    }

    public void get_user_status(String token, String id, final ApiListener.StatusDownloadListener listener) {

        ApiClient.getApiInterface().get_user_status(token, id).enqueue(new Callback<Status>() {
            @Override
            public void onResponse(@NonNull Call<Status> call, @NonNull Response<Status> response) {
                if (response != null) {
                    listener.onStatusDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<Status> call, @NonNull Throwable t) {
                listener.onStatusDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getMyPrescriptionRequest(String token, String userID, String userType, final ApiListener.MyPrescriptionRequestDownloadListener listener) {

        ApiClient.getApiInterface().getServedprescriptionRequest(token, userID, userType).enqueue(new Callback<List<PrescriptionRequestModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<PrescriptionRequestModel>> call, @NonNull Response<List<PrescriptionRequestModel>> response) {
                if (response != null) {
                    listener.onMyPrescriptionRequestDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<PrescriptionRequestModel>> call, @NonNull Throwable t) {
                listener.onMyPrescriptionRequestDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void publicQueryPOst(String token, String message_body, String message_sender_id, String message_receiver_id, final ApiListener.publicQueryPostListenerPatient listener) {

        ApiClient.getApiInterface().addPublicQuery(token, message_body, message_sender_id, message_receiver_id, "0").enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onPublicQueryPostSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onPublicQueryPostFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getVideoCallSummery(String token, String id, String userType, final ApiListener.VideoCallHistoryDownloadListenerPatient listener) {

        ApiClient.getApiInterface().getVideoCallSummery(token, id, userType).enqueue(new Callback<List<VideoCallHistoryModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<VideoCallHistoryModel>> call, @NonNull Response<List<VideoCallHistoryModel>> response) {
                if (response != null) {
                    listener.onVideoCallHistoryDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<VideoCallHistoryModel>> call, @NonNull Throwable t) {
                listener.onVideoCallHistoryDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getAppointments(String token, String type, String userID, String status, final ApiListener.appoinetmentsDownloadListener listener) {

        ApiClient.getApiInterface().getAppointmentsList(token, type, userID, status).enqueue(new Callback<List<AppointmentModelNew>>() {
            @Override
            public void onResponse(@NonNull Call<List<AppointmentModelNew>> call, @NonNull Response<List<AppointmentModelNew>> response) {
                if (response != null) {
                    listener.onAppointmentDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<AppointmentModelNew>> call, @NonNull Throwable t) {
                listener.onAppointmentDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void addAmbulance(String token, String district, String phone, String area, String address, String Title, final ApiListener.AddAmbulanceListenerPatient listener) {

        ApiClient.getApiInterface().addAmbulance(token, district, phone, area, address, Title).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onAddAreaPostSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onAddAmbulancePostFailed(t.getLocalizedMessage());
            }
        });
    }

    public void addArea(String token, String area, final ApiListener.AddAreaPostListenerPatient listener) {

        ApiClient.getApiInterface().addArea(token, area).enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(@NonNull Call<StatusMessage> call, @NonNull Response<StatusMessage> response) {
                if (response != null) {
                    listener.onAddAreaPostSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<StatusMessage> call, @NonNull Throwable t) {
                listener.onAddAreaPostFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getDepList(String KEY, final ApiListener.DeptDownloadListener listener) {

        ApiClient.getApiInterface().getDepartments(KEY).enqueue(new Callback<List<DeptModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<DeptModel>> call, @NonNull Response<List<DeptModel>> response) {
                if (response != null) {
                    listener.onDepartmentDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<DeptModel>> call, @NonNull Throwable t) {
                listener.onDepartmentDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getLatestQuery(String KEY, final ApiListener.LatestQueryDownloadListener listener) {

        ApiClient.getApiInterface().getlatestQuery(KEY).enqueue(new Callback<List<PublicLatestQueryModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<PublicLatestQueryModel>> call, @NonNull Response<List<PublicLatestQueryModel>> response) {
                if (response != null) {
                    listener.onLatestQueryDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<PublicLatestQueryModel>> call, @NonNull Throwable t) {
                listener.onLatestQueryDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getChatQuery(String KEY, String sender, String receiver, final ApiListener.ChatQueryDownloadListener listener) {

        ApiClient.getApiInterface().getChat(KEY, sender, receiver).enqueue(new Callback<List<ChatModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<ChatModel>> call, @NonNull Response<List<ChatModel>> response) {
                if (response != null) {
                    listener.onChatQueryDownloadSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<ChatModel>> call, @NonNull Throwable t) {
                listener.onChatQueryDownloadFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getMedicinesList(String KEY, final ApiListener.DownloadMedicinesListInfoListener listener) {

        ApiClient.getApiInterface().getMedicine(KEY).enqueue(new Callback<List<MedicineModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<MedicineModel>> call, @NonNull Response<List<MedicineModel>> response) {
                if (response != null) {
                    listener.onDownloadMedicinesListInfoSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<MedicineModel>> call, @NonNull Throwable t) {
                listener.onDownloadMedicinesListFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getDistrictList(String KEY, final ApiListener.DownloadDistrictListInfoListener listener) {

        ApiClient.getApiInterface().getDistrictList(KEY).enqueue(new Callback<List<DistrictModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<DistrictModel>> call, @NonNull Response<List<DistrictModel>> response) {
                if (response != null) {
                    listener.onDownloadDistrictListInfoSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<DistrictModel>> call, @NonNull Throwable t) {
                listener.onDownloadDistrictListFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getAmbulanceList(String KEY, final ApiListener.DownloadAmbulanceListInfoListener listener) {

        ApiClient.getApiInterface().getAmbulanceList(KEY).enqueue(new Callback<List<AmbulanceModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<AmbulanceModel>> call, @NonNull Response<List<AmbulanceModel>> response) {
                if (response != null) {
                    listener.onDownloadAmbulanceListInfoSuccess(response.body());

                }

            }

            @Override
            public void onFailure(@NonNull Call<List<AmbulanceModel>> call, @NonNull Throwable t) {
                listener.onDownloadAmbulanceListFailed(t.getLocalizedMessage());
            }
        });
    }
}
