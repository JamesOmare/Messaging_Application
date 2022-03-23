package com.example.penzi_project20;



import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface UserService {

    @POST("post_incoming_messages")
    Call<ActicationResponse> acticateUser(@Body ActicationRequest acticationRequest);

    @POST("post_incoming_messages")
    Call<Post_Incoming_Message_Response> Post_Incoming_Message(@Body Post_Incoming_Message_Request post_incoming_message_request);

    @GET("describe_by_number/{number}")
    Call<StartUserResponse> startUser(@Path("number") String number);

    @GET("fetch_incoming_messages")
    Call<String> incomingMessageFetch();

    @GET("fetch_incoming_messages_sender")
    Call<Fetch_Incoming_message_Sender> incomingMessage_SenderFetch();



}
