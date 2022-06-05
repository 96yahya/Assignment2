package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.*;
import android.database.Cursor;
import android.os.*;
import android.view.*;
import android.widget.*;


public class BookingDetailes extends AppCompatActivity {

    //declear DatabaseHelper mDb;
    DatabaseHelper dbHelper;

    //declear the variables
    EditText fName, sName,days, persons, phoneNO, id;
    Button calculate, update, delete, read, clear, close, back;

    //declear the textView
    TextView message;

    //declear variable
    int price, roomPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_detailes);

        dbHelper = new DatabaseHelper(this);
        //get the room price from booking room activity
        Intent intent = getIntent();
        roomPrice = intent.getIntExtra("room_Price", 0);


        //assign the textView
        message = (TextView) findViewById(R.id.BookingDetailesMessage);

        //assign the edittexts
        fName = (EditText) findViewById(R.id.BookingDetailesFname);
        sName = (EditText) findViewById(R.id.BookingDetailesSname);
        days = (EditText) findViewById(R.id.BookingDetailesNumDay);
        persons = (EditText) findViewById(R.id.BookingDetailesNumPerson);
        phoneNO = (EditText) findViewById(R.id.BookingDetailesPhoneOn);
        id = (EditText) findViewById(R.id.BookingDetailesID);

        //assign the buttons
        calculate = (Button) findViewById(R.id.BookingDetailesCalculate);
        update = (Button) findViewById(R.id.BookingDetailesUpdate);
        delete = (Button) findViewById(R.id.BookingDetailesDelete);
        read = (Button) findViewById(R.id.BookingDetailesRead);
        clear = (Button) findViewById(R.id.BookingDetailesClear);
        close = (Button) findViewById(R.id.BookingDetailesClose);
        back = (Button) findViewById(R.id.BookingDetailesBacktoBooking);

        //declear the function
        calculateData();
        updateDate();
        deleteDate();
        readDate();
        clearDate();
        closeApp();


        //this button to go back to booking page and selste a new reservation
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(BookingDetailes.this, BookingRoom.class);
                startActivity(intent);
            }
        });
    }

    //this function will take the days that user wants to book and
    // multibly the room price to find total price and insert the price in database
    public void calculateData()
    {
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //calculate price and display it for user
                int dayofReservation = Integer.parseInt(days.getText().toString());
                price = roomPrice * dayofReservation;
                message.setText("The total price is: " + price + " OR");

                //insert the user information in database
                boolean insert = dbHelper.insertData(fName.getText().toString(), sName.getText().toString(), days.getText().toString(), persons.getText().toString(), phoneNO.getText().toString(), String.valueOf(price));

                //display toast based on insertion if it done or not
                if(insert == true)
                    Toast.makeText(BookingDetailes.this, "The data inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(BookingDetailes.this, "The data not inserted",Toast.LENGTH_LONG).show();
            }
        });
    }

    //this function will update the user information in the database
    public void updateDate()
    {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //calculate price and display it for user
                int dayofReservation = Integer.parseInt(days.getText().toString());
                price = roomPrice * dayofReservation;
                message.setText("The total price is: " + price + " OR");

                //pass the values to the update function
                boolean update = dbHelper.updateData(id.getText().toString(), fName.getText().toString(), sName.getText().toString(), days.getText().toString(), persons.getText().toString(), phoneNO.getText().toString(), String.valueOf(price));

                //display toast based on update if it done or not
                if(update == true)
                    Toast.makeText(BookingDetailes.this, "The data updated",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(BookingDetailes.this, "The data not updated",Toast.LENGTH_LONG).show();
            }
        });
    }

    //this function will delete all records from the database
    public void deleteDate()
    {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //pass the id to the delete function
                Integer delete = dbHelper.deleteData(id.getText().toString());

                //display toast based on delete function if it done or not
                if(delete > 0)
                    Toast.makeText(BookingDetailes.this, "The data deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(BookingDetailes.this, "The data not deleted",Toast.LENGTH_LONG).show();
            }
        });
    }

    //this function will read all records from the database and show it for the user
    public void readDate()
    {
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor read = dbHelper.getAllData();

                //error message if no recordes to display
                if(read.getCount() == 0)
                {
                    message.setText("No data found");
                    return;
                }

                //creat the buffer object
                StringBuffer buffer = new StringBuffer();

                //display recordes while there is
                while (read.moveToNext())
                {
                    buffer.append("ID: " + read.getString(0) + "\n");
                    buffer.append("First name: " + read.getString(1) + "\n");
                    buffer.append("Second name: " + read.getString(2) + "\n");
                    buffer.append("Days: " + read.getString(3) + "\n");
                    buffer.append("Persons: " + read.getString(4) + "\n");
                    buffer.append("Phone no: " + read.getString(5) + "\n");
                    buffer.append("Price: " + read.getInt(6) + "\n");
                }

                //display the records in the message
                showMessage("Reservation Details",buffer.toString());
            }
        });
    }

    //this function will clear all fields in the activity
    public void clearDate()
    {
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //clear all fields
                fName.setText("");
                sName.setText("");
                days.setText("");
                persons.setText("");
                phoneNO.setText("");
                id.setText("");
            }
        });
    }

    //this function will close the application
    public void closeApp()
    {
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.exit(1);
            }
        });
    }

    //this function will helps to display the message and records
    public void showMessage(String Heading,String message)
    {
        AlertDialog.Builder alertBuilder=new AlertDialog.Builder(this);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle(Heading);
        alertBuilder.setMessage(message);
        alertBuilder.show();
    }


}