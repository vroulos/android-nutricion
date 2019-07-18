//package com.vroulos.mynutricion.activities;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.widget.TextView;
//
//import com.vroulos.mynutricion.R;
//import com.vroulos.mynutricion.api.JsonPlaceHolderApi;
//import com.vroulos.mynutricion.models.Message;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class MessageActivity1 extends AppCompatActivity {
//
//
//    private TextView textViewMessage ;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_message1);
//
//
//
//
//       textViewMessage = findViewById(R.id.messageidd);
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.43.200/MyApi2/public/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        JsonPlaceHolderApi api = retrofit.create(JsonPlaceHolderApi.class);
//
//        Call<List<Message>> call = api.getAllMessages();
//
//        call.enqueue(new Callback<List<Message>>() {
//            @Override
//            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
//
//                if(!response.isSuccessful()){
//                    textViewMessage.setText("code" +response.code());
//                    return;
//                }
//
//                List<Message> messages = response.body();
//
//                for (Message message  : messages){
//                    String content = "";
//                    content += "title "+ message.getMessage()+" \n";
//                    textViewMessage.append(content);
//
//                }
////                finish();
//////                return;
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Message>> call, Throwable t) {
//                textViewMessage.setText(t.getMessage());
//            }
//        });
//
//
//
////        // set up the RecyclerView
////        RecyclerView recyclerView = findViewById(R.id.rvAnimals);
////        recyclerView.setLayoutManager(new LinearLayoutManager(this));
////        adapter = new testRecycler(this, animalNames);
////        adapter.setClickListener(this);
////        recyclerView.setAdapter(adapter);
//
//    }
//
//
////    @Override
////    public void onItemClick(View view, int position) {
////        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
////    }
//}