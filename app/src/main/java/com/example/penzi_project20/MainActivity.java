package com.example.penzi_project20;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextInputEditText msg, sender_no, short_code;
    Button button;
    TextView textView;
    public static int rem, j, total,i,k;
    public static String gender, match_gender, age1, age2, county;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msg = findViewById(R.id.message);
        sender_no = findViewById(R.id.sender_number);
        short_code = findViewById(R.id.shortcode);
        button = findViewById(R.id.button_send);
        textView = findViewById(R.id.textview_display);

        textView.setMovementMethod(new ScrollingMovementMethod());


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(sender_no.getText().toString()) || TextUtils.isEmpty(msg.getText().toString()) || TextUtils.isEmpty(short_code.getText().toString()))
                {
                    String message = "All input fields are required ...";
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                }

                else if(sender_no.getText().toString().length() != 10)
                {
                    String message = "Invalid number entered, check again and retry";
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();

                }
                else if(!short_code.getText().toString().equals("5501"))
                {
                    String message = "Wrong shortcode entered. Please retry!";
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                }

                else if(TextUtils.equals(msg.getText().toString().toLowerCase(), "penzi")) {

                    post_method();
                    textView.setText("Welcome to our dating service with 6000 potential dating partners! To register " +
                            "sms start#name#age#gender#county#town to 5001 e.g start#mike#25#male#mombasa#changamwe");



//                    ActicationRequest acticationRequest = new ActicationRequest();
//                    acticationRequest.setSender_number(phone_number);
//                    acticationRequest.setMessage(msg.getText().toString());
//                    acticationRequest.setShortcode(Integer.valueOf(short_code.getText().toString()));
//                    acticateUser(acticationRequest);
                }

                else
                    {
                        while (true)
                        {

                            if(TextUtils.equals(msg.getText().toString().toLowerCase().substring(0, 4), "next"))
                            {
                                post_method();
                                MatchNext();



                                break;

                            }
                            else if(TextUtils.equals(msg.getText().toString().toLowerCase().substring(0, 5), "start"))
                            {
                                post_method();
                                Process_MessageFetch_Start();



                                break;

                            }

                            else if(TextUtils.equals(msg.getText().toString().toLowerCase().substring(0, 7), "details"))
                            {

                                post_method();

                                Process_MessageFetch_Details();

                                break;
                            }

                            else if(TextUtils.equals(msg.getText().toString().toLowerCase().substring(0, 6), "myself"))
                            {

                                post_method();

                                Process_MessageFetch_Myself();
                                break;


                            }


                            else if(TextUtils.equals(msg.getText().toString().toLowerCase().substring(0, 5), "match"))
                            {

                                post_method();

                                Match();
                                break;


                            }

//                            else if(TextUtils.equals(msg.getText().toString().toLowerCase().substring(0, 4), "next"))
//                            {
//
//                                post_method();
//
//                                MatchNext();
//                                break;
//
//
//                            }


                            else{

                                textView.setText("Invalid message entered. Please try again");
                                break;

                            }

                        }





                }

            }
        });



    }

    public void acticateUser(ActicationRequest acticationRequest){
        Call<ActicationResponse> acticationResponseCall = ApiClient.getUserService().acticateUser(acticationRequest);
        acticationResponseCall.enqueue(new Callback<ActicationResponse>() {
            @Override
            public void onResponse(Call<ActicationResponse> call, Response<ActicationResponse> response) {

                if(response.isSuccessful()){
                    String message = "Successful ...";
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();

                    TextView textView = findViewById(R.id.textview_display);
                    textView.setText("Welcome to our dating service with 6000 potential dating partners! To register " +
                            "sms start#name#age#gender#county#town to 5001 e.g start#mike#25#male#mombasa#changamwe");

                }

                else{

                    String message = "An error occurred please try again later ...";
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<ActicationResponse> call, Throwable t) {

                Log.e( "Failure.", t.getLocalizedMessage());

            }
        });
    }

    public void startUser(String number){
        Call<StartUserResponse> user_details_list = ApiClient.getUserService().startUser(number);
        user_details_list.enqueue(new Callback<StartUserResponse>() {
            @Override
            public void onResponse(Call<StartUserResponse> call, Response<StartUserResponse> response) {
                if(response.isSuccessful()){
                    Log.e("success", response.body().toString());
                    textView.setText(response.body().toString());
                }
                else
                {
                    String message = "An error occurred please try again later ...";
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<StartUserResponse> call, Throwable t) {

                Log.e( "Failure.", t.getLocalizedMessage());

            }
        });

    }

    public void Post_Incoming_Message(Post_Incoming_Message_Request post_incoming_message_request){
        Call<Post_Incoming_Message_Response> post_incoming_message_responseCall = ApiClient.getUserService().Post_Incoming_Message(post_incoming_message_request);
        post_incoming_message_responseCall.enqueue(new Callback<Post_Incoming_Message_Response>() {
            @Override
            public void onResponse(Call<Post_Incoming_Message_Response> call, Response<Post_Incoming_Message_Response> response) {
                if(response.isSuccessful()){

                    Log.e("success: posted to IM", response.body().toString());
                    String message = "Successful ...";
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();

                }
                else
                {
                    String message = "An error occurred please try again later ...";
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Post_Incoming_Message_Response> call, Throwable t) {

                Log.e( "Failure.", t.getLocalizedMessage());

            }
        });
    }

    public void post_method(){

        String sender_number = sender_no.getText().toString();
        String replacement = "+254";
        String regexTarget = "0";
        String phone_number = sender_number.replaceFirst(regexTarget, replacement);
        Post_Incoming_Message_Request post_incoming_message_request = new Post_Incoming_Message_Request();
        post_incoming_message_request.setSender_number(phone_number);
        post_incoming_message_request.setMessage(msg.getText().toString());
        post_incoming_message_request.setShortcode(Integer.valueOf(short_code.getText().toString()));
        Post_Incoming_Message(post_incoming_message_request);




    }

    public void Post_start_to_User(Post_start_Message_to_User_Request post_start_message_to_user_request)
    {
        Call<Post_Start_Message_to_User_Response> start_message_to_user_responseCall = ApiClient.getUserService().Post_start_to_User(post_start_message_to_user_request);
        start_message_to_user_responseCall.enqueue(new Callback<Post_Start_Message_to_User_Response>() {
            @Override
            public void onResponse(Call<Post_Start_Message_to_User_Response> call, Response<Post_Start_Message_to_User_Response> response) {
                if(response.isSuccessful()){
                    Log.e("success", response.body().toString());
                }

                else
                {
                    String message = "An error occurred please try again later ...";
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Post_Start_Message_to_User_Response> call, Throwable t) {

                Log.e( "Failure.", t.getLocalizedMessage());

            }
        });
    }


    public void Post_Details_to_User(Post_Details_Message_to_User_Request post_details_message_to_user_request)
    {
        String sender_number = sender_no.getText().toString();
        String replacement = "+254";
        String regexTarget = "0";
        String phone_number = sender_number.replaceFirst(regexTarget, replacement);
        Call<Post_Details_Message_to_User_Response> details_message_to_user_responseCall = ApiClient.getUserService().Post_Details_to_User(phone_number, post_details_message_to_user_request);
        details_message_to_user_responseCall.enqueue(new Callback<Post_Details_Message_to_User_Response>() {
            @Override
            public void onResponse(Call<Post_Details_Message_to_User_Response> call, Response<Post_Details_Message_to_User_Response> response) {
                if(response.isSuccessful()){
                    Log.e("success", response.body().toString());
                }

                else
                {
                    String message = "An error occurred please try again later ...";
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Post_Details_Message_to_User_Response> call, Throwable t) {

                Log.e( "Failure.", t.getLocalizedMessage());

            }
        });
    }


    public void Post_Description_to_User(Post_Description_Message_to_User_Request post_description_message_to_user_request)
    {
        String sender_number = sender_no.getText().toString();
        String replacement = "+254";
        String regexTarget = "0";
        String phone_number = sender_number.replaceFirst(regexTarget, replacement);
        Call<Post_Description_Message_to_User_Response> description_message_to_user_responseCall = ApiClient.getUserService().Post_Description_to_User(phone_number, post_description_message_to_user_request);
        description_message_to_user_responseCall.enqueue(new Callback<Post_Description_Message_to_User_Response>() {
            @Override
            public void onResponse(Call<Post_Description_Message_to_User_Response> call, Response<Post_Description_Message_to_User_Response> response) {
                if(response.isSuccessful()){
                    Log.e("success", response.body().toString());
                }

                else
                {
                    String message = "An error occurred please try again later ...";
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Post_Description_Message_to_User_Response> call, Throwable t) {

                Log.e( "Failure.", t.getLocalizedMessage());

            }
        });
    }



    public void Process_MessageFetch_Start(){
        Call<String> get_incoming_message = ApiClient.getUserService().incomingMessageFetch();
        get_incoming_message.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("Response", response.body().toString());
                if(response.isSuccessful()){
                    Log.e("success", response.body().toString());
                    String user_message = response.body().toString();

                    try{
                        String[] arrOfStr = user_message.split("#");
                        String item1, item2, item3, item4, item5, item6;

                        item1 = arrOfStr[0];
                        item2 = arrOfStr[1];
                        item3 = arrOfStr[2];
                        item4 = arrOfStr[3];
                        item5 = arrOfStr[4];
                        item6 = arrOfStr[5];

                        Post_start_Message_to_User_Request post_start_message_to_user_request = new Post_start_Message_to_User_Request();
                        post_start_message_to_user_request.setName(item2);
                        post_start_message_to_user_request.setAge(Integer.valueOf(item3));
                        post_start_message_to_user_request.setGender(item4);
                        post_start_message_to_user_request.setCounty(item5);
                        post_start_message_to_user_request.setTown(item6);
                        String sender_number = sender_no.getText().toString();
//                        String replacement = "+254";
//                        String regexTarget = "0";
                        String phone_number = sender_number.replaceFirst("0", "+254");
                        post_start_message_to_user_request.setNumber(phone_number);
                        Post_start_to_User(post_start_message_to_user_request);

                        Log.e("proces -> user database", response.body().toString());

                        String message = "Start message data posted to database ...";
                        Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();

                        textView.setText("Thank you. SMS details#level of education#profession#marital status#religion#tribe to 5001 "+
                                "e.g details#diploma#accountant#single#christian#mijikenda");


                    }
                    catch (IndexOutOfBoundsException e){

                        String message = "Start message entered is invalid. Please review and retry.";
                        Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                        String blank = "";
                        textView.setText(blank);

                    }
                    catch (Exception e){
                        String message = "A different type of exception occured. Check logs.";
                        Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                        String blank = "";
                        textView.setText(blank);
                    }


                }
                else {

                    String message = "An error occurred please try again later ...";
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Log.e( "Failure.", t.getLocalizedMessage());

            }
        });
    }


    public void Process_MessageFetch_Details(){
        Call<String> get_incoming_message = ApiClient.getUserService().incomingMessageFetch();
        get_incoming_message.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("Response", response.body().toString());
                if(response.isSuccessful()){
                    Log.e("success", response.body().toString());
                    String user_message = response.body().toString();

                    try{

                        String[] arrOfStr = user_message.split("#");
                        String item1, item2, item3, item4, item5, item6;



                        item1 = arrOfStr[0];
                        item2 = arrOfStr[1];
                        item3 = arrOfStr[2];
                        item4 = arrOfStr[3];
                        item5 = arrOfStr[4];
                        item6 = arrOfStr[5];



                        Post_Details_Message_to_User_Request post_details_message_to_user_request = new Post_Details_Message_to_User_Request();
                        post_details_message_to_user_request.setEducation_level(item2);
                        post_details_message_to_user_request.setProfession(item3);
                        post_details_message_to_user_request.setMarital_status(item4);
                        post_details_message_to_user_request.setReligion(item5);
                        post_details_message_to_user_request.setTribe(item6);
                        Post_Details_to_User(post_details_message_to_user_request);



                        Log.e("sent to user database", response.body().toString());

                        String message = "Details message data posted to database ...";
                        Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();

                        textView.setText("This is the last stage of registration. SMS a brief description of yourself to 5001 " +
                                "starting with the word MYSELF e.g MYSELF chocolate, lovely, sexy etc");


                    }
                    catch (IndexOutOfBoundsException e){

                        String message = "Details message entered is invalid. Please review and retry.";
                        Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                        String blank = "";
                        textView.setText(blank);

                    }
                    catch (Exception e){
                        String message = "A different type of exception occured. Check logs.";
                        Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                        String blank = "";
                        textView.setText(blank);
                    }


                }
                else {

                    String message = "An error occurred please try again later ...";
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Log.e( "Failure.", t.getLocalizedMessage());

            }
        });

    }


    public void Process_MessageFetch_Myself(){

        Call<String> get_incoming_message = ApiClient.getUserService().incomingMessageFetch();
        get_incoming_message.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("Response", response.body().toString());
                if(response.isSuccessful()){
                    Log.e("success", response.body().toString());
                    String user_message = response.body().toString();

                    try{

                        String[] arrOfStr = user_message.split(" ", 2);
                        String item1, item2;



                        item1 = arrOfStr[0];
                        item2 = arrOfStr[1];




                        Post_Description_Message_to_User_Request post_description_message_to_user_request = new Post_Description_Message_to_User_Request();
                        post_description_message_to_user_request.setDescription(item2);

                        Post_Description_to_User(post_description_message_to_user_request);



                        Log.e("sent to user database", response.body().toString());

                        String message = "Details message data posted to database ...";
                        Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();

                        textView.setText("You are now registered. Enjoy yourself. To search for a MPENZI" +
                                " SMS Match#age#town to 5001 e.g Match#23-25#Nairobi");


                    }
                    catch (IndexOutOfBoundsException e){

                        String message = "Description message entered is invalid. Please review and retry.";
                        Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                        String blank = "";
                        textView.setText(blank);

                    }
                    catch (Exception e){
                        String message = "A different type of exception occured. Check logs.";
                        Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                        String blank = "";
                        textView.setText(blank);
                    }


                }
                else {

                    String message = "An error occurred please try again later ...";
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Log.e( "Failure.", t.getLocalizedMessage());

            }
        });

    }


    public void GetGender()
    {
        String sender_number = sender_no.getText().toString();
        String replacement = "+254";
        String regexTarget = "0";
        String phone_number = sender_number.replaceFirst(regexTarget, replacement);




        Call<String> user_details_list = ApiClient.getUserService().GetGender(phone_number);
        user_details_list.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    Log.e("GetGender returned =>", response.body().toString());

                    gender = response.body().toString();



                }
                else
                {
                    String message = "An error occurred please try again later ...";
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Log.e( "Failure.", t.getLocalizedMessage());

            }
        });
    }


    public void Match()
    {
        i = 0;
        j = 0;

        GetGender();

        Call<String> get_incoming_message = ApiClient.getUserService().incomingMessageFetch();
        get_incoming_message.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("Response", response.body().toString());
                if(response.isSuccessful()){
                    Log.e("success", response.body().toString());
                    String user_message = response.body().toString();

                    try{

                        String[] arrOfStr = user_message.split("[#-]" );
                        String match_header, age1, age2, county;

                        match_header = arrOfStr[0];
                        age1 = arrOfStr[1];
                        age2 = arrOfStr[2];
                        county = arrOfStr[3];

                        int Age1=Integer.parseInt(age1);
                        int Age2=Integer.parseInt(age2);



                        Call<String> get_incoming_message = ApiClient.getUserService().UserNo(Age1, Age2, county, gender);
                        get_incoming_message.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Log.e("Response", response.body().toString());
                                if(response.isSuccessful()){
                                    Log.e("Returned total is =>", response.body().toString());
                                    total = Integer.parseInt(response.body().toString());

                                    Log.e("total is => ", String.valueOf(total));



                                    Call<List<MatchUserResponse>> get_user_details = ApiClient.getUserService().MatchUser(Age1, Age2, county, gender);
                                    get_user_details.enqueue(new Callback<List<MatchUserResponse>>() {
                                        @Override
                                        public void onResponse(Call<List<MatchUserResponse>> call, Response<List<MatchUserResponse>> body_response) {

                                            if(body_response.isSuccessful()){

                                                String users_json = body_response.body().toString();
                                                JsonArray arr = new Gson().fromJson(users_json, JsonArray.class);


                                                String join = "";
                                                String out_of_bounds = "";
                                                String end_message = "There are no more partners to find a match. Try changing your matching criteria";

                                                String next_message = "";

                                                boolean success = true;

                                                if (total <= 3 && total > 0) {

                                                    try {

                                                        for (; i <= 2; i++) {
                                                            JsonElement str = arr.get(i);
                                                            JsonObject obj = new Gson().fromJson(str, JsonObject.class);
                                                            String name = obj.get("name").toString().replaceAll("^\"|\"$", "");
                                                            String number = obj.get("number").toString().replaceAll("^\"|\"$", "");
                                                            String new_number = number.replaceFirst("254", "0");
                                                            String age = obj.get("age").toString();
                                                            join += String.format("%s, aged %s, %s" + "\n", name, age, new_number);

                                                        }

                                                    } catch (IndexOutOfBoundsException e) {

                                                        success = false;
                                                        out_of_bounds = join + end_message;
//                            System.out.println(out_of_bounds);
                                                        textView.setText(out_of_bounds);

                                                    }

                                                    if (success) {

//                            System.out.println(join + end_message);
                                                        textView.setText(join + end_message);

                                                    }

                                                }

                                                else if (total == 0) {
                                                    //    System.out.println("There are no matches found. Try changing your match criteria");
                                                    textView.setText("There are no matches found. Try changing your match criteria");
                                                }

                                                else {

                                                    while (true) {

                                                        if (i > 2) {
                                                            rem = total - j;
                                                            next_message = String.format("Send NEXT to 5001 to receive details of the remaining %d matches.",
                                                                    rem);
//                                System.out.println(join + next_message);
                                                            textView.setText(join+next_message);


                                                            break;

                                                        }

                                                        else {
                                                            while (true) {

                                                                JsonElement str = arr.get(i);
                                                                JsonObject obj = new Gson().fromJson(str, JsonObject.class);
                                                                String name = obj.get("name").toString().replaceAll("^\"|\"$", "");
                                                                String number = obj.get("number").toString().replaceAll("^\"|\"$", "");
                                                                String new_number = number.replaceFirst("254", "0");
                                                                String age = obj.get("age").toString();
                                                                join += String.format("%s, aged %s, %s" + "\n", name, age, new_number);

                                                                i = i + 1;
                                                                j = j + 1;

                                                                break;

                                                            }

                                                        }

                                                    }

                                                }





                                                String message = "Success ...";
                                                Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();


                                            }
                                            else {

                                                String message = "An error occurred please try again later ...";
                                                Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<List<MatchUserResponse>> call, Throwable t) {

                                            Log.e( "Failure.", t.getLocalizedMessage());


                                        }
                                    });


                                }
                                else {

                                    String message = "An error occurred please try again later ...";
                                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();

                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                                Log.e( "Failure.", t.getLocalizedMessage());

                            }
                        });




                    }
                    catch (IndexOutOfBoundsException e){

                        String message = "Description message entered is invalid. Please review and retry.";
                        Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                        String blank = "";
                        textView.setText(blank);

                    }
                    catch (Exception e){
                        String message = "A different type of exception occured. Check logs.";
                        Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                        String blank = "";
                        textView.setText(blank);
                    }


                }
                else {

                    String message = "An error occurred please try again later ...";
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Log.e( "Failure.", t.getLocalizedMessage());

            }
        });

    }


    public void MatchNext()
    {




        GetGender();

        Call<String> get_incoming_message = ApiClient.getUserService().incomingMessageFetch();
        get_incoming_message.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("Response", response.body().toString());
                if(response.isSuccessful()){
                    Log.e("success", response.body().toString());
                    String user_message = response.body().toString();

                    try{

                        String[] arrOfStr = user_message.split("[#-]" );
                        String match_header, age1, age2, county;

                        match_header = arrOfStr[0];
                        age1 = arrOfStr[1];
                        age2 = arrOfStr[2];
                        county = arrOfStr[3];

                        int Age1=Integer.parseInt(age1);
                        int Age2=Integer.parseInt(age2);



                        Call<String> get_incoming_message = ApiClient.getUserService().UserNo(Age1, Age2, county, gender);
                        get_incoming_message.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Log.e("Response", response.body().toString());
                                if(response.isSuccessful()){
                                    Log.e("Returned total is =>", response.body().toString());
                                    total = Integer.parseInt(response.body().toString());

                                    Log.e("total is => ", String.valueOf(total));



                                    Call<List<MatchUserResponse>> get_user_details = ApiClient.getUserService().MatchUser(Age1, Age2, county, gender);
                                    get_user_details.enqueue(new Callback<List<MatchUserResponse>>() {
                                        @Override
                                        public void onResponse(Call<List<MatchUserResponse>> call, Response<List<MatchUserResponse>> body_response) {

                                            if(body_response.isSuccessful()){

                                                String users_json = body_response.body().toString();
                                                JsonArray arr = new Gson().fromJson(users_json, JsonArray.class);


                                                String join = "";
                                                String out_of_bounds = "";
                                                String end_message = "There are no more partners to find a match. Try changing your matching criteria";

                                                String next_message = "";

                                                boolean success = true;

                                                if (total <= 3 && total > 0) {

                                                    try {

                                                        for (; i <= 2; i++) {
                                                            JsonElement str = arr.get(i);
                                                            JsonObject obj = new Gson().fromJson(str, JsonObject.class);
                                                            String name = obj.get("name").toString().replaceAll("^\"|\"$", "");
                                                            String number = obj.get("number").toString().replaceAll("^\"|\"$", "");
                                                            String new_number = number.replaceFirst("254", "0");
                                                            String age = obj.get("age").toString();
                                                            join += String.format("%s, aged %s, %s" + "\n", name, age, new_number);

                                                        }

                                                    } catch (IndexOutOfBoundsException e) {

                                                        success = false;
                                                        out_of_bounds = join + end_message;
//                            System.out.println(out_of_bounds);
                                                        textView.setText(out_of_bounds);

                                                    }

                                                    if (success) {

//                            System.out.println(join + end_message);
                                                        textView.setText(join + end_message);

                                                    }

                                                }

                                                else if (total == 0) {
                                                    //    System.out.println("There are no matches found. Try changing your match criteria");
                                                    textView.setText("There are no matches found. Try changing your match criteria");
                                                }

                                                else {

                                                    while (true) {

                                                        if (i > 2) {
                                                            rem = total - j;
                                                            next_message = String.format("Send NEXT to 5001 to receive details of the remaining %d matches.",
                                                                    rem);
//                                System.out.println(join + next_message);
                                                            textView.setText(join+next_message);


                                                            break;

                                                        }

                                                        else {
                                                            while (true) {

                                                                JsonElement str = arr.get(i);
                                                                JsonObject obj = new Gson().fromJson(str, JsonObject.class);
                                                                String name = obj.get("name").toString().replaceAll("^\"|\"$", "");
                                                                String number = obj.get("number").toString().replaceAll("^\"|\"$", "");
                                                                String new_number = number.replaceFirst("254", "0");
                                                                String age = obj.get("age").toString();
                                                                join += String.format("%s, aged %s, %s" + "\n", name, age, new_number);

                                                                i = i + 1;
                                                                j = j + 1;

                                                                break;

                                                            }

                                                        }

                                                    }

                                                }





                                                String message = "Success ...";
                                                Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();


                                            }
                                            else {

                                                String message = "An error occurred please try again later ...";
                                                Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<List<MatchUserResponse>> call, Throwable t) {

                                            Log.e( "Failure.", t.getLocalizedMessage());


                                        }
                                    });


                                }
                                else {

                                    String message = "An error occurred please try again later ...";
                                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();

                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                                Log.e( "Failure.", t.getLocalizedMessage());

                            }
                        });




                    }
                    catch (IndexOutOfBoundsException e){

                        String message = "Description message entered is invalid. Please review and retry.";
                        Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                        String blank = "";
                        textView.setText(blank);

                    }
                    catch (Exception e){
                        String message = "A different type of exception occured. Check logs.";
                        Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                        String blank = "";
                        textView.setText(blank);
                    }


                }
                else {

                    String message = "An error occurred please try again later ...";
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Log.e( "Failure.", t.getLocalizedMessage());

            }
        });

//        int Age1=Integer.parseInt(age1);
//        int Age2=Integer.parseInt(age2);
//
//        Call<String> get_incoming_message = ApiClient.getUserService().UserNo(Age1, Age2, county, match_gender);
//        get_incoming_message.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                Log.e("Response", response.body().toString());
//                if(response.isSuccessful()){
//                    Log.e("Returned total is =>", response.body().toString());
//                    total = Integer.parseInt(response.body().toString());
//
//                    Call<List<MatchUserResponse>> get_user_details = ApiClient.getUserService().MatchUser(Age1, Age2, county, match_gender);
//                    get_user_details.enqueue(new Callback<List<MatchUserResponse>>() {
//                        @Override
//                        public void onResponse(Call<List<MatchUserResponse>> call, Response<List<MatchUserResponse>> body_response) {
////                            Log.e("Response", body_response.body().toString());
//                            if(body_response.isSuccessful()){
//
//                                String details_response = body_response.body().toString();
////                                text_view.setText(details_response);
//                                JsonArray arr = new Gson().fromJson(details_response, JsonArray.class);
//
//                                String join = "", out_of_bounds = "";
//                                String end_message = "There are no more partners to find a match. Try changing your matching criteria";
//
//                                String next_message = "";
//                                k = i + 2;
//                                boolean success = true;
//
//                                if (rem <= 3 && rem > 0) {
//
//                                    try {
//
//                                        for (; rem <= 2; i++) {
//
//                                            JsonElement str = arr.get(i);
//                                            JsonObject obj = new Gson().fromJson(str, JsonObject.class);
//                                            String name = obj.get("name").toString().replaceAll("^\"|\"$", "");
//                                            String number = obj.get("number").toString().replaceAll("^\"|\"$", "");
//                                            String new_number = number.replaceFirst("254", "0");
//                                            String age = obj.get("age").toString();
//                                            join += String.format("%s, aged %s, %s" + "\n", name, age, new_number);
//
//                                        }
//
//                                    } catch (IndexOutOfBoundsException e) {
//
//                                        success = false;
//                                        out_of_bounds = join + end_message;
////                                        System.out.println(out_of_bounds);
//                                        textView.setText(out_of_bounds);
//
//                                    }
//
//                                    if (success) {
//
////                                        System.out.println(join + end_message);
//                                        textView.setText(join + end_message);
//
//                                    }
//
//                                }
//
//                                else if (j == 0) {
//
////                                    System.out.println("There are no matches found. Try changing your match criteria");
//                                    textView.setText("There are no matches found. Try changing your match criteria");
//                                }
//
//                                else {
//
//                                    while (true) {
//
//                                        if (i > k) {
//
//                                            rem = total - j;
//
//                                            next_message = String.format("Send NEXT to 5001 to receive details of the remaining %d matches.",
//                                                    rem);
////                                            System.out.println(join + next_message);
//                                            textView.setText(join + next_message);
//
//                                            break;
//
//                                        }
//
//                                        else {
//                                            while (true) {
//
//                                                try {
//
//                                                    JsonElement str = arr.get(i);
//                                                    JsonObject obj = new Gson().fromJson(str, JsonObject.class);
//                                                    String name = obj.get("name").toString().replaceAll("^\"|\"$", "");
//                                                    String number = obj.get("number").toString().replaceAll("^\"|\"$", "");
//                                                    String new_number = number.replaceFirst("254", "0");
//                                                    String age = obj.get("age").toString();
//                                                    join += String.format("%s, aged %s, %s" + "\n", name, age, new_number);
//
//                                                    i = i + 1;
//                                                    j = j + 1;
//
//                                                    break;
//
//                                                }
//
//                                                catch(IndexOutOfBoundsException error)
//                                                {
//                                                    textView.setText("There are no matches found. Try changing your match criteria");
//                                                }
//
//                                            }
//
//                                        }
//
//                                    }
//
//                                }
//
//
//
//
//
//                                String message = "Success ...";
//                                Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
//
//
//                            }
//                            else {
//
//                                String message = "An error occurred please try again later ...";
//                                Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
//
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<List<MatchUserResponse>> call, Throwable t) {
//
//                            Log.e( "Failure.", t.getLocalizedMessage());
//
//
//                        }
//                    });
//
//
//                }
//                else {
//
//                    String message = "An error occurred please try again later ...";
//                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//                Log.e( "Failure.", t.getLocalizedMessage());
//
//            }
//        });

    }



}