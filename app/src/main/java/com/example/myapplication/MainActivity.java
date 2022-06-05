package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    //declear
    Button goToBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign the variables
        goToBooking = (Button) findViewById(R.id.goToBooking);

        goToBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, BookingRoom.class);
                startActivity(intent);
            }
        });
    }
}