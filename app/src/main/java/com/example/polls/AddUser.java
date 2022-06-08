package com.example.polls;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.google.android.material.internal.CheckableGroup;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUser extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    private Boolean isChecked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_users);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);


        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitUser();
            }
        });
        SwitchCompat isAdmin = findViewById(R.id.submit_admin);
        isAdmin.setOnCheckedChangeListener(this);
    }

    private void submitUser() {
        EditText username = findViewById(R.id.submit_user);
        EditText email = findViewById(R.id.submit_email);
        EditText password = findViewById(R.id.submit_password);



        Log.d("TAG", String.valueOf(username.getText()));
        Log.d("TAG",String.valueOf(email.getText()));
        Log.d("TAG",String.valueOf(password.getText()));
        Log.d("TAG",String.valueOf(isChecked));



        RetrofitAPI apiService = ApiClient.getClient().create(RetrofitAPI.class);

        Call<String> call = apiService.createUser(String.valueOf(username.getText()),String.valueOf(email.getText()),String.valueOf(password.getText()),isChecked);

        // on below line we are executing our method.
        call.enqueue(new Callback<String>()  {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast user_created = Toast.makeText(AddUser.this, "User Created", Toast.LENGTH_LONG);
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
