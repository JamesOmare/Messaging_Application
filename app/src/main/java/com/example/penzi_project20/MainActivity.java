package com.example.penzi_project20;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextInputEditText msg, sender_no, short_code;
    Button button;
    TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msg = findViewById(R.id.message);
        sender_no = findViewById(R.id.sender_number);
        short_code = findViewById(R.id.shortcode);
        button = findViewById(R.id.button_send);
        textView = findViewById(R.id.textview_display);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(sender_no.getText().toString()) || TextUtils.isEmpty(msg.getText().toString()) || TextUtils.isEmpty(short_code.getText().toString()))
                {
                    String message = "All input fields are required ...";
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
                }

                else if(TextUtils.equals(msg.getText().toString(), "penzi")) {

                    ActicationRequest acticationRequest = new ActicationRequest();
                    acticationRequest.setSender_number(sender_no.getText().toString());
                    acticationRequest.setMessage(msg.getText().toString());
                    acticationRequest.setShortcode(Integer.valueOf(short_code.getText().toString()));
                    acticateUser(acticationRequest);
                }

                else
                    {
                        String number = msg.getText().toString();
                        startUser(number);

//                    StartUserResponse startUserResponse = new StartUserResponse();
//                    startUserResponse.getAge().toString();
//                    startUserResponse.getCounty().toString();
//                    startUserResponse.getDescription().toString();
//                    startUserResponse.getEducation_level().toString();
//                    startUserResponse.getGender().toString();
//                    startUserResponse.getId().toString();
//                    startUserResponse.getMarital_status().toString();
//                    startUserResponse.getMatched_by().toString();
//                    startUserResponse.getName().toString();
//                    startUserResponse.getNumber().toString();
//                    startUserResponse.getProfession().toString();
//                    startUserResponse.getReligion().toString();
//                    startUserResponse.getStatus().toString();
//                    startUserResponse.getTime_of_registry().toString();
//                    startUserResponse.getTown().toString();
//                    startUserResponse.getTribe().toString();
//                    startUser(startUserResponse);



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
            }

            @Override
            public void onFailure(Call<StartUserResponse> call, Throwable t) {

                Log.e( "Failure.", t.getLocalizedMessage());

            }
        });

    }

}