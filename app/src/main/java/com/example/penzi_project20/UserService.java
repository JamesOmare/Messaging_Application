package com.example.penzi_project20;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("post_incoming_messages")
    Call<ActicationResponse> acticateUser(@Body ActicationRequest acticationRequest);

}
