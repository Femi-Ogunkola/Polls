package com.example.polls;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitAPI {
    // as we are making get request so we are displaying
    // GET as annotation.
    // and inside we are passing last parameter for our url.
    @Headers("Content-Type: application/json")
    @GET("get_users")
    // as we are calling data from array so we are calling
    // it with array list and naming that method as getAllCourses();
    Call<ArrayList<ModelClass>> getUsers();

    @GET("get_polls")
    Call<ArrayList<Poll>> getPolls();

    @POST("/cast_vote/positive")
    Call<String> createPost(@Query("poll_id") int poll_id);

    @POST("/cast_vote/negative")
    Call<String> createNegativePost(@Query("poll_id") int poll_id);

    @POST("/create_user")
    Call<String> createUser(
            @Query("username") String username,
            @Query("email") String email,
            @Query("password") String password,
            @Query("is_admin") Boolean admin);

    @POST("/create_poll")
    //db=db, duration=100, pollTitle="test", pollDescription="test", positive_vote_count=1, negative_vote_count=1, is_active=True, author_id=1)
    Call<String> createPoll(
            @Query("pollTitle") String title,
            @Query("pollDescription") String description,
            @Query("duration") int duration,
            @Query("is_active") Boolean admin);

    @POST("/delete_poll")
        //db=db, duration=100, pollTitle="test", pollDescription="test", positive_vote_count=1, negative_vote_count=1, is_active=True, author_id=1)
    Call<String> deletePoll(@Query("poll_id") int id);


}
