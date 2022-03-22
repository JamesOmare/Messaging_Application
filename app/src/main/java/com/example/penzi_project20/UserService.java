package com.example.penzi_project20;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @POST("post_incoming_messages")
    Call<ActicationResponse> acticateUser(@Body ActicationRequest acticationRequest);

    @GET("describe_by_number/{number}")
    Call<StartUserResponse> startUser(@Path("number") String number);



}
