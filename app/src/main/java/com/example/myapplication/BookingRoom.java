package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class BookingRoom extends AppCompatActivity {

    ImageButton BookBtnDouble, BookBtnMaster, BookBtnSingle, BookBtnSweet, BookBtnTruble;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_room);

        //assign ImageButtons
        BookBtnDouble = (ImageButton) findViewById(R.id.BookBtndouble);
        BookBtnMaster = (ImageButton) findViewById(R.id.BookBtnMaster);
        BookBtnSingle = (ImageButton) findViewById(R.id.BookBtnSingle);
        BookBtnSweet = (ImageButton) findViewById(R.id.BookBtnSweet);
        BookBtnTruble = (ImageButton) findViewById(R.id.BookBtnTruble);

        //this button when the user click double room button
       BookBtnDouble.setOnClickListener(new View.OnClickListener() {
           int reserved = 0;
            @Override
            public void onClick(View v) {
                //go next booking details activity and pass the room price
                Intent intent = new Intent(BookingRoom.this, BookingDetailes.class);
                intent.putExtra("room_Price", 25);
                startActivity(intent);
            }
        });


        //this button when the user click master room button
        BookBtnMaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //go next booking details activity and pass the room price
                Intent intent = new Intent(BookingRoom.this,BookingDetailes.class);
                intent.putExtra("room_Price", 35);
                startActivity(intent);
            }
        });

        //this button when the user click single room button
        BookBtnSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //go next booking details activity and pass the room price
                Intent intent = new Intent(BookingRoom.this,BookingDetailes.class);
                intent.putExtra("room_Price", 13);
                startActivity(intent);
            }
        });

        //this button when the user click sweet room button
        BookBtnSweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //go next booking details activity and pass the room price
                Intent intent = new Intent(BookingRoom.this,BookingDetailes.class);
                intent.putExtra("room_Price", 50);
                startActivity(intent);
            }
        });

        //this button when the user click truble room button
        BookBtnTruble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //go next booking details activity and pass the room price
                Intent intent = new Intent(BookingRoom.this,BookingDetailes.class);
                intent.putExtra("room_Price", 40);
                startActivity(intent);
            }
        });
    }

    public void Message(String Heading,String message)
    {
        AlertDialog.Builder alertBuilder=new AlertDialog.Builder(this);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle(Heading);
        alertBuilder.setMessage(message);
        alertBuilder.show();
    }
}