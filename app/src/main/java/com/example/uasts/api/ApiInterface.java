package com.example.uasts.api;


import com.example.uasts.model.delete.Delete;
import com.example.uasts.model.login.Login;
import com.example.uasts.model.logout.Logout;
import com.example.uasts.model.register.Register;
import com.example.uasts.model.update.Update;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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
    @POST("update.php")
    Call<Update> updateResponse(
            @Field("currentUsername") String currentUsername,
            @Field("username") String Newusername,
            @Field("name") String name,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("logout.php")
    Call<Logout> logoutResponse(
            @Field("username") String username
    );

}
