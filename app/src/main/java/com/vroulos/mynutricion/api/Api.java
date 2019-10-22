package com.vroulos.mynutricion.api;

import com.vroulos.mynutricion.models.MessagesResponse;
import com.vroulos.mynutricion.models.UserRegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("createuser")
    Call<UserRegisterResponse> createUser(
            @Field("email") String email,
            @Field("password") String password,
            @Field("name") String name

    );


   // @Headers("Content-Type: application/json")
   @FormUrlEncoded
    @POST("usermessages")
    Call<MessagesResponse> getmessages(
            @Field("username") String username
    );






}
