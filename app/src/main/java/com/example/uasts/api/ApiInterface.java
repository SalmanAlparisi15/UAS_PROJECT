package com.example.uasts.api;


import com.example.uasts.model.delete.Delete;
import com.example.uasts.model.deleterumour.DeleteRumour;
import com.example.uasts.model.login.Login;
import com.example.uasts.model.logout.Logout;
import com.example.uasts.model.register.Register;
import com.example.uasts.model.rumourfile.RumourFile;
import com.example.uasts.model.transferfile.TransferFile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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


}
