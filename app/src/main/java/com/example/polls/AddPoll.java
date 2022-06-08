package com.example.polls;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPoll extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    private Boolean isChecked;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_poll);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);


        Button submit = findViewById(R.id.create_poll_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitUser();
            }
        });
        SwitchCompat isAdmin = findViewById(R.id.create_poll_active);
        isAdmin.setOnCheckedChangeListener(this);
    }

    private void submitUser() {
        EditText pollTitle = findViewById(R.id.create_poll_title);
        EditText description = findViewById(R.id.create_poll_description);
        EditText duration = findViewById(R.id.create_poll_duration);



        Log.d("TAG", String.valueOf(pollTitle.getText()));
        Log.d("TAG",String.valueOf(description.getText()));
        Log.d("TAG", String.valueOf(Integer.parseInt(String.valueOf(duration.getText()))));
        Log.d("TAG",String.valueOf(isChecked));



        RetrofitAPI apiService = ApiClient.getClient().create(RetrofitAPI.class);

        Call<String> call = apiService.createPoll(String.valueOf(pollTitle.getText()),String.valueOf(description.getText()),Integer.parseInt(String.valueOf(duration.getText())),isChecked);

        // on below line we are executing our method.
        call.enqueue(new Callback<String>()  {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast user_created = Toast.makeText(AddPoll.this, "User Created", Toast.LENGTH_LONG);
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
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        isChecked = b;
    }
}
