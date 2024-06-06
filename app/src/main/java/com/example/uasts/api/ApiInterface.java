package com.example.uasts.api;


import com.example.uasts.model.delete.Delete;
import com.example.uasts.model.deleterumour.DeleteRumour;
import com.example.uasts.model.login.Login;
import com.example.uasts.model.logout.Logout;
import com.example.uasts.model.postrumour.PostRumour;
import com.example.uasts.model.register.Register;
import com.example.uasts.model.rumourfile.RumourFile;
import com.example.uasts.model.transferfile.TransferFile;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("login.php")
    Call<Login> loginResponse(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<Register> registerResponse(
            @Field("username") String username,
            @Field("password") String password,
            @Field("name") String name

    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<Delete> deleteResponse(
            @Field("username") String username

    );


    @FormUrlEncoded
    @POST("logout.php")
    Call<Logout> logoutResponse(
            @Field("username") String username
    );

    @GET("transfer.php")
    Call<TransferFile> getTransferFile(

    );

    @GET("rumour.php")
    Call<RumourFile> getRumourFile(

    );

    @FormUrlEncoded
    @POST("deleterumour.php")
    Call<DeleteRumour> deleteRumour(
            @Field("id") int id
    );

    @Multipart
    @POST("postrumour.php")
    Call<PostRumour> postRumour(
            @Part("player_name") RequestBody playername,
            @Part MultipartBody.Part playerphoto,
            @Part("player_position") RequestBody playerposition,
            @Part("club_name") RequestBody clubname,
            @Part MultipartBody.Part clubphoto,
            @Part("price") RequestBody price

    );


}
