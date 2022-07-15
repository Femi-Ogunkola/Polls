package com.example.polls;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VotePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vote_page);
        Intent intent = getIntent();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);


        TextView vote_title = findViewById(R.id.vote_title);
        TextView vote_description = findViewById(R.id.vote_description);
        TextView positive = findViewById(R.id.positive);
        TextView negative = findViewById(R.id.negative);
        TextView duration = findViewById(R.id.duration);

        vote_title.setText(intent.getStringExtra("title"));
        vote_description.setText(intent.getStringExtra("description"));
        positive.setText(intent.getStringExtra("positive"));
        negative.setText(intent.getStringExtra("negative"));
        duration.setText(intent.getStringExtra("duration"));


        ImageView upVote = findViewById(R.id.positive_button);
        upVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postData(intent.getStringExtra("id"));
            }
        });

        ImageView downVote = findViewById(R.id.negative_button);
        downVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postNegativeData(intent.getStringExtra("id"));
            }
        });



    }

    private void postNegativeData(String id) {
        RetrofitAPI apiService = ApiClient.getClient().create(RetrofitAPI.class);

        Call<String> call = apiService.createNegativePost(Integer.parseInt(id));

        // on below line we are executing our method.
        call.enqueue(new Callback<String>()  {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("TAG",String.valueOf(response));
                TextView positive = findViewById(R.id.negative);
                positive.setText(String.valueOf(response.body()));
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
                Log.d("TAG","Error found is : " + t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void postData(String poll_id) {

        // below line is for displaying our progress bar.
        //loadingPB.setVisibility(View.VISIBLE);

        // on below line we are creating a retrofit
        // builder and passing our base url
        RetrofitAPI apiService = ApiClient.getClient().create(RetrofitAPI.class);

        Call<String> call = apiService.createPost(Integer.parseInt(poll_id));

        // on below line we are executing our method.
        call.enqueue(new Callback<String>()  {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("TAG",String.valueOf(response));
                TextView positive = findViewById(R.id.positive);
                positive.setText(String.valueOf(response.body()));
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
                Log.d("TAG","Error found is : " + t.getMessage());
            }
        });
    }
}
