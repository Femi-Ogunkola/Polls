package com.example.polls;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PollActivity extends AppCompatActivity implements PollAdapter.onPollListener{
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ArrayList<Poll> Poll;
    PollAdapter pollAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poll_content);
        getData();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        FloatingActionButton create_poll = findViewById(R.id.create_user);
        create_poll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // gotoCreateUser();
                createPoll();
            }
        });



    }

    private void createPoll() {
        Intent intent = new Intent(PollActivity.this, AddPoll.class);
        startActivity(intent);
    }

    private void gotoCreateUser() {
        Intent intent = new Intent(PollActivity.this, AddUser.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getData() {
        recyclerView = findViewById(R.id.pollrecyclerview);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        RetrofitAPI apiService = ApiClient.getClient().create(RetrofitAPI.class);
        Call<ArrayList<Poll>> call = apiService.getPolls();
        call.enqueue(new Callback<ArrayList<Poll>>() {
            @Override
            public void onResponse(Call<ArrayList<Poll>> call, Response<ArrayList<Poll>> response) {

                Poll = response.body();
                Log.d("TAG", Poll.getClass().getSimpleName());
                Log.d("TAG","Response = "+Poll);
                pollAdapter = new PollAdapter(Poll, PollActivity.this::onPollClick);
                recyclerView.setAdapter(pollAdapter);

            }

            @Override
            public void onFailure(Call<ArrayList<Poll>> call, Throwable t) {

                Log.d("TAG","Response = "+t.toString());
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getData();
    }

    @Override
    public void onPollClick(int position) {
        String a = Poll.get(position).getPoll_title();
        Log.d("TAG", String.valueOf(a));
        Intent intent = new Intent(this, VotePage.class);
        intent.putExtra("title", Poll.get(position).getPoll_title());
        intent.putExtra("description", Poll.get(position).getPoll_description());
        intent.putExtra("positive", String.valueOf(Poll.get(position).getPostive_vote_count()));
        intent.putExtra("negative", String.valueOf(Poll.get(position).getNegative_vote_count()));
        intent.putExtra("duration", String.valueOf(Poll.get(position).getDuration()));
        intent.putExtra("id", String.valueOf(Poll.get(position).getId()));



        startActivity(intent);
    }
}
