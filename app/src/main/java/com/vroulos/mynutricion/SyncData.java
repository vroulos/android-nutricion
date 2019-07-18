package com.vroulos.mynutricion;

import android.content.Context;

import com.vroulos.mynutricion.api.JsonPlaceHolderApi;
import com.vroulos.mynutricion.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SyncData   {

    protected Context context;

    public SyncData() {

    }

    //DatabaseHelper databaseHelper = new DatabaseHelper(context);

    public void synchronizeUserMessages(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/MyApi2/Public/")
                //.baseUrl("http://192.168.1.69/MyApi2/Public/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi api = retrofit.create(JsonPlaceHolderApi.class);



        Call<DefaultResponse> call = api.updateMessages("trokis");

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                String body = response.body().getMsg();
                //final List<Message> messages = body.getMessages();
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });


    }
}
