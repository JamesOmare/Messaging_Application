package com.example.penzi_project20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
                else {

                    ActicationRequest acticationRequest = new ActicationRequest();
                    acticationRequest.setSender_number(sender_no.getText().toString());
                    acticationRequest.setMessage(msg.getText().toString());
                    acticationRequest.setShortcode(Integer.valueOf(short_code.getText().toString()));

//                    acticationRequest.setShortcode(short_code.getText().toString());
                    acticateUser(acticationRequest);
                }

            }
        });



    }

    public void acticateUser(ActicationRequest acticationRequest){
        Call<ActicationResponse> acticationResponseCall = ApiClient.getService().acticateUser(acticationRequest);
        acticationResponseCall.enqueue(new Callback<ActicationResponse>() {
            @Override
            public void onResponse(Call<ActicationResponse> call, Response<ActicationResponse> response) {

                if(response.isSuccessful()){
                    String message = "Successful ...";
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();


                }

                else{

                    String message = "An error occurred please try again later ...";
                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<ActicationResponse> call, Throwable t) {

            }
        });
    }

}