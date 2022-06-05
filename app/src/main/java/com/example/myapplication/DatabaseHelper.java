package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASENAME = "sahab.db";
    public static final String TABLENAME = "reservation";
    public static final String COL_1 = "ID", COL_2 = "FIRST_NAME", COL_3 = "LAST_NAME",
            COL_4 = "Days", COL_5 = "Persons", COL_6 = "PhoneNo", COL_7 = "Price";

    //this is the constractor
    public DatabaseHelper(Context context)
    {
        super(context, DATABASENAME, null, 1);
    }

    //this function will creat tha datbase according to the spacified name
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " + TABLENAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRST_NAME TEXT, LAST_NAME TEXT, Days TEXT, Persons TEXT, PhoneNo TEXT, Price INTEGER) ");
    }

    //this function will upgrade the table
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
    {
        database.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        onCreate(database);
    }

    //this function will inset the data in tha table
    public boolean insertData(String fname, String lname, String day, String person, String phone, String price)
    {
        //set the data
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, fname);
        contentValues.put(COL_3, lname);
        contentValues.put(COL_4, day);
        contentValues.put(COL_5, person);
        contentValues.put(COL_6, phone);
        contentValues.put(COL_7, price);

        //check if the data inserted or not
        long result = db.insert(TABLENAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    //this function will ubdate all the recoreds in the table
    public boolean updateData(String id, String fname, String lname, String day, String person, String phone, String price)
    {
        //set the data in colmuns
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, fname);
        contentValues.put(COL_3, lname);
        contentValues.put(COL_4, day);
        contentValues.put(COL_5, person);
        contentValues.put(COL_6, phone);
        contentValues.put(COL_7, price);

        //ubdate the data according to spacified id
        db.update(TABLENAME, contentValues, "ID=?", new String[]{id});

        return true;
    }

    //this function to delete the record according to id
    public Integer deleteData(String userID)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete(TABLENAME, "ID=?", new String[]{userID});
    }

    //this function will return all the data in the table
    public Cursor getAllData()
    {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from " + TABLENAME, null);
        return cursor;
    }
}
