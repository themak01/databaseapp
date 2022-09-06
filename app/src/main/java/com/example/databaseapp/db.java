package com.example.databaseapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class db extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Table";

    public db(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public void onCreate(SQLiteDatabase db){
        String CREATE_USER_TABLE="CREATE TABLE empdetails(ID INTEGER PRIMARY KEY AUTOINCREMENT,empidentification TEXT NOT NULL,empname TEXT NOT NULL,empdept TEXT NOT NULL)";
        db.execSQL(CREATE_USER_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        String DELETE_USER_TABLE="DELETE FROM empdetails WHERE ID=1";
        db.execSQL(DELETE_USER_TABLE);
    }
}