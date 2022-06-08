package com.example.polls;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ArrayList<ModelClass> User;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_content);
        getData();

        FloatingActionButton PollButton = findViewById(R.id.got);
        PollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
    }

    public void sendMessage() {
        Intent intent = new Intent(this, PollActivity.class);
        startActivity(intent);
    }

    private void getData() {
        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        customAdapter = new CustomAdapter(User);

        RetrofitAPI apiService = ApiClient.getClient().create(RetrofitAPI.class);
        Call<ArrayList<ModelClass>> call = apiService.getUsers();
        call.enqueue(new Callback<ArrayList<ModelClass>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<ArrayList<ModelClass>> call, Response<ArrayList<ModelClass>> response) {
                User = response.body();
                Log.d("TAG", User.getClass().getSimpleName());
                Log.d("TAG","Response = "+User);
                customAdapter = new CustomAdapter(User);
                recyclerView.setAdapter(customAdapter);

                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<ModelClass>> call, Throwable t) {

                Log.d("TAG","Response = "+t.toString());
            }
        });

    }




//    private void initRecyclerView() {
//        recyclerView = findViewById(R.id.recyclerView);
//        linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        customAdapter = new CustomAdapter(user);
//        recyclerView.setAdapter(customAdapter);
//        customAdapter.notifyDataSetChanged();
//    }
}