package com.example.penzi_project20;



import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface UserService {

    @POST("post_incoming_messages")
    Call<ActicationResponse> acticateUser(@Body ActicationRequest acticationRequest);

    @POST("post_incoming_messages")
    Call<Post_Incoming_Message_Response> Post_Incoming_Message(@Body Post_Incoming_Message_Request post_incoming_message_request);

    @POST("post_start_message_to_user")
    Call<Post_Start_Message_to_User_Response> Post_start_to_User(@Body Post_start_Message_to_User_Request post_start_message_to_user_request);

    @PUT("update_user_details/{number}")
    Call<Post_Details_Message_to_User_Response> Post_Details_to_User(@Path("number") String number, @Body Post_Details_Message_to_User_Request post_details_message_to_user_request);

    @PUT("update_user_myself/{number}")
    Call<Post_Description_Message_to_User_Response> Post_Description_to_User(@Path("number") String number, @Body Post_Description_Message_to_User_Request post_description_message_to_user_request);


    @GET("describe_by_number/{number}")
    Call<StartUserResponse> startUser(@Path("number") String number);

    @GET("fetch_incoming_messages")
    Call<String> incomingMessageFetch();

    @GET("fetch_incoming_messages_sender")
    Call<Fetch_Incoming_message_Sender> incomingMessage_SenderFetch();



}
