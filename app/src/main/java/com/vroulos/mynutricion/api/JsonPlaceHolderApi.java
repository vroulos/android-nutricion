package com.vroulos.mynutricion.api;

import com.vroulos.mynutricion.models.DefaultResponse;
import com.vroulos.mynutricion.models.MessagesResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface JsonPlaceHolderApi {

    @GET("")
    Call<MessagesResponse> getAllMessages();

    @FormUrlEncoded
    @POST("usermessages")
    Call<MessagesResponse> getUserMessages(
            @Field("username") String username
    );

    @FormUrlEncoded
    @PUT("updateSincValue")
    Call<DefaultResponse> updateMessages(
            @Field("username") String username
    );


}
