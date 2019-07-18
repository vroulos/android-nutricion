package com.vroulos.mynutricion.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.vroulos.mynutricion.DatabaseHelper;
import com.vroulos.mynutricion.R;
import com.vroulos.mynutricion.SyncData;
import com.vroulos.mynutricion.WifiReceiver;
import com.vroulos.mynutricion.api.JsonPlaceHolderApi;
import com.vroulos.mynutricion.models.Message;
import com.vroulos.mynutricion.models.MessagesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageActivity2 extends AppCompatActivity  {


    TextView textViewTest;
    TextView textViewMessage;
    private List<Message> messageList;
    private WifiReceiver wifiReceiver;
    private Switch wifiSwitch;
    ListView listView;



    //DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message2);

        textViewMessage = findViewById(R.id.messageidd);
        listView = findViewById(R.id.listview_id);
        wifiSwitch = findViewById(R.id.wifi_switch);
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);


    }

    //synchronize the data from web to android app
    private void syncData(){

        textViewMessage = findViewById(R.id.messageidd);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/MyApi2/Public/")
                //.baseUrl("http://192.168.1.69/MyApi2/Public/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi api = retrofit.create(JsonPlaceHolderApi.class);



        Call<MessagesResponse> call = api.getUserMessages("trokis");

        call.enqueue(new Callback<MessagesResponse>() {
            @Override
            public void onResponse(Call<MessagesResponse> call, Response<MessagesResponse> response) {


                if (!response.isSuccessful()) {
                    textViewMessage.setText("code" + response.code());
                    return;
                }


                MessagesResponse body = response.body();
                final List<Message> messages = body.getMessages();

                if (messages != null){
                    messages.size();
                }


                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Object context;
                        //DBManager dbManager = new DBManager(Context context);
                        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());

                        int z = databaseHelper.insertMessagesFromMysql(messages);
                        if (z > 0){
                            SyncData sync = new SyncData();
                            sync.synchronizeUserMessages();
                        }

                        Toast.makeText(MessageActivity2.this, "data in the base  " +z, Toast.LENGTH_SHORT).show();
                        //databaseHelper.insertMessagesFromMysql(messages);
                    }
                }, 1000);
            }

            @Override
            public void onFailure(Call<MessagesResponse> call, Throwable t) {
                textViewMessage.setText(t.getMessage());
            }
        });

    }




    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(wifiStateReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(wifiStateReceiver);
    }

    private BroadcastReceiver wifiStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int wifiStateExtra = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
                    WifiManager.WIFI_STATE_UNKNOWN);

            switch (wifiStateExtra) {
                case WifiManager.WIFI_STATE_ENABLED:
                    wifiSwitch.setChecked(true);
                    wifiSwitch.setText("WiFi is ON");
                    syncData();
                    break;
                case WifiManager.WIFI_STATE_DISABLED:
                    wifiSwitch.setChecked(false);
                    wifiSwitch.setText("WiFi is OFF");
                    break;
            }
        }
    };

    public void deleteAllMessages(View view) {
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());

        int rowsDeleted = databaseHelper.deleteAllMessages();
        Toast.makeText(this, "The rows is deleted"+rowsDeleted, Toast.LENGTH_SHORT).show();
    }
}




