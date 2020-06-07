package com.chequp.admin.api;


import com.chequp.admin.model.AllCollectionWithdraModel;
import com.chequp.admin.model.AmbulanceModel;
import com.chequp.admin.model.AppointmentModelNew;
import com.chequp.admin.model.BillItem;
import com.chequp.admin.model.BillSummery;
import com.chequp.admin.model.BlogCategoryNameID;
import com.chequp.admin.model.ChatModel;
import com.chequp.admin.model.CommonUserModel;
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
import com.chequp.admin.model.WidthdrawModel;
import com.chequp.admin.model.WitdhdrawFull;


import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


public interface ApiInterface {
    @FormUrlEncoded
    @POST("get_payment_list")
    Call<AllCollectionWithdraModel> get_payment_list(@Header("Authorization") String token,
                                                     @Field("id") String patient_id,
                                                     @Field("user_type") String user_type);


    @POST("all_withdrawal_request")
    Call<List<WitdhdrawFull>> all_withdrawal_request(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("update_withdrawal_request")
    Call<StatusMessage> update_withdrawal_request(@Header("Authorization") String token,
                                                     @Field("id") String id,
                                                     @Field("status") String status);
    @FormUrlEncoded
    @POST("yearlySingleUserBillList")
    Call<List<BillItem>> yearlySingleUserBillList(@Header("Authorization") String s, @Field("user_id") String user_id, @Field("year") String year);

    @FormUrlEncoded
    @POST("yearlySingleUserBillSummery")
    Call<BillSummery> yearlySingleUserBillSummery(@Header("Authorization") String s, @Field("user_id") String user_id, @Field("year") String year);
    @FormUrlEncoded
    @POST("monthlySingleUserBillList")
    Call<List<BillItem>> monthlySingleUserBillList(@Header("Authorization") String s, @Field("user_id") String user_id, @Field("year") String year, @Field("month") String month);
    @GET("serviceNameRate")
    Call<List<ServiceNameRate>> getServiceNameRate(@Header("Authorization") String s);
    @POST("send")
    Call<NotificationResponse> newmsg(@Header("Authorization") String s, @Body NotiModel model);
    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(@Field("email") String mobile,
                              @Field("password") String password);

    @FormUrlEncoded
    @POST("notice_add_all_user")
    Call<StatusMessage> notice_add_all_user(@Header("Authorization") String token,
                                            @Field("message") String message);

    @POST("allBlogCategory")
    Call<List<BlogCategoryNameID>> getBlogChamber(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("notice_add_by_user_type")
    Call<StatusMessage> notice_add_by_user_type(@Header("Authorization") String token,
                                                @Field("user_type") String user_type,
                                                @Field("message") String message);

    @FormUrlEncoded
    @POST("addBlogCategory")
    Call<StatusMessage> addBlogCategory(@Header("Authorization") String token,
                                        @Field("name") String name);

    @FormUrlEncoded
    @POST("notice_add_by_department_type")
    Call<StatusMessage> notice_add_by_department_type(@Header("Authorization") String token,
                                                      @Field("department") String department,
                                                      @Field("message") String message);


    @FormUrlEncoded
    @POST("add-medicine-info")
    Call<StatusMessage> add_medicine_info(@Header("Authorization") String token,
                                          @Field("name") String name,
                                          @Field("manufacture") String manufacture,
                                          @Field("type") String type,
                                          @Field("description") String description);


    @FormUrlEncoded
    @POST("user-list-by-user-type")
    Call<List<CommonUserModel>> downloadUsersByType(@Header("Authorization") String token, @Field("user_type") String user_type);

    @FormUrlEncoded
    @POST("get_doctors_documents")
    Call<List<DocumentModel>> getAllDocumentsBySingleDoctor(@Header("Authorization") String token,
                                                            @Field("dr_id") String dr_id);

    @FormUrlEncoded
    @POST("add-department")
    Call<StatusMessage> addDepartment(@Header("Authorization") String token, @Field("name") String name);

    @FormUrlEncoded
    @POST("view-public-query-by-id")
    Call<List<QueryModel>> getMyQueries(@Header("Authorization") String token,
                                        @Field("message_sender_id") String message_sender_id,
                                        @Field("message_receiver_id") String message_receiver_id);

    @FormUrlEncoded
    @POST("view-online-doctor-service")
    Call<List<DrOnlineServiceModel>> getAllServiceByDr(@Header("Authorization") String token,
                                                       @Field("doctor_id") String doctor_id);

    @FormUrlEncoded
    @POST("doctor-education-chamber-info")
    Call<DrEduChInfoModel> getSkillChamberEdu(@Header("Authorization") String token,
                                              @Field("dr_id") String dr_id);

    @POST("all-online-service")
    Call<List<ServiceNameInfo>> getAllOnlineServices(@Header("Authorization") String token);

    @POST("department-list")
    Call<List<DeptModel>> getDepartments(@Header("Authorization") String token);

    @GET("view-latest-public-query-by-admin")
    Call<List<PublicLatestQueryModel>> getlatestQuery(@Header("Authorization") String token);

    @POST("view-public-query-by-id")
    Call<List<ChatModel>> getChat(@Header("Authorization") String token, @Query("message_sender_id") String message_sender_id, @Query("message_receiver_id") String message_receiver_id);

    @FormUrlEncoded
    @POST("view-video-call-summary")
    Call<List<VideoCallHistoryModel>> getVideoCallSummery(@Header("Authorization") String token,
                                                          @Field("id") String id,
                                                          @Field("user_type") String user_type);

    @FormUrlEncoded
    @POST("get-appointment-list")
    Call<List<AppointmentModelNew>> getAppointmentsList(@Header("Authorization") String token,
                                                        @Field("user_type") String user_type,
                                                        @Field("id") String id,
                                                        @Field("status") String status);


    @FormUrlEncoded
    @POST("view-prescription-request")
    Call<List<PrescriptionRequestModel>> getServedprescriptionRequest(@Header("Authorization") String token,
                                                                      @Field("id") String id,
                                                                      @Field("user_type") String user_type);

    @FormUrlEncoded
    @POST("get_my_reviewed_recheck_requests")
    Call<List<PrescriptionReviewModel>> getMyReviewedRecheckRequests(@Header("Authorization") String token,
                                                                     @Field("id") String id,
                                                                     @Field("user_type") String user_type);

    @FormUrlEncoded
    @POST("update_user_status")
    Call<StatusMessage> update_user_status(@Header("Authorization") String token,
                                           @Field("id") String id,
                                           @Field("status") String status);

    @FormUrlEncoded
    @POST("get_user_status")
    Call<Status> get_user_status(@Header("Authorization") String token,
                                 @Field("id") String id);

    @GET("all-medicine-list")
    Call<List<MedicineModel>> getMedicine(@Header("Authorization") String token);

    @GET("view-district")
    Call<List<DistrictModel>> getDistrictList(@Header("Authorization") String token);

    @GET("view-ambulance")
    Call<List<AmbulanceModel>> getAmbulanceList(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("add-public-query")
    Call<StatusMessage> addPublicQuery(@Header("Authorization") String token,
                                       @Field("message_body") String message_body,
                                       @Field("message_sender_id") String message_sender_id,
                                       @Field("message_receiver_id") String message_receiver_id,
                                       @Field("status") String status);

    @FormUrlEncoded
    @POST("add-ambulance")
    Call<StatusMessage> addAmbulance(@Header("Authorization") String token,
                                     @Field("district") String district,
                                     @Field("phone") String phone,
                                     @Field("area") String area,
                                     @Field("address") String address,
                                     @Field("Title") String Title);

    @FormUrlEncoded
    @POST("add-district")
    Call<StatusMessage> addArea(@Header("Authorization") String token,
                                @Field("name") String name);

}