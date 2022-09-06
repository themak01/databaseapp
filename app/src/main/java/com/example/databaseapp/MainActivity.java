package com.example.databaseapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText edd1,edd2,edd3;
    Button btn1,btn2,btn3;
    TextView tvv1,tvv2,tvv3;
    SQLiteDatabase db1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db objdatabase = new db(this);
        db1 =objdatabase.getWritableDatabase();

        setContentView(R.layout.activity_main);
        edd1=(EditText) findViewById(R.id.ed1);
        edd2=(EditText) findViewById(R.id.ed2);
        edd3=(EditText) findViewById(R.id.ed3);

        tvv1=(TextView)findViewById(R.id.tv2);
        tvv2=(TextView)findViewById(R.id.tv3);
        tvv3=(TextView)findViewById(R.id.tv4);

        btn1=(Button)findViewById(R.id.b);
        btn2=(Button)findViewById(R.id.b1);
        btn3=(Button)findViewById(R.id.b2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });



        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = openOrCreateDatabase("Table",MODE_PRIVATE,null);
                String sQ= "SELECT * from empdetails";
                Cursor cursor=db.rawQuery(sQ,null);
                if(cursor != null){
                    cursor.moveToFirst();
                    startManagingCursor(cursor);
                    while(!cursor.isAfterLast()){
                        String no=cursor.getString(1);
                        String n=cursor.getString(2);
                        String de =cursor.getString(3);

                        String Ob1 ="Employee Number" + no;
                        String Ob2 ="Employee Number" + n;
                        String Ob3 ="Employee Number" + de;
                        tvv1.setText(Ob1);
                        tvv2.setText(Ob2);
                        tvv3.setText(Ob3);
                        Toast.makeText(MainActivity.this,"Data Displayed",Toast.LENGTH_LONG).show();
                        cursor.moveToNext();
                    }
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser();
            }
        });
    }
    public void registerUser(){
        String empid,empname,empdept;
        empid = edd1.getText().toString();
        empname=edd2.getText().toString();
        empdept =edd3.getText().toString();

        ContentValues values=new ContentValues();
        values.put("empidentification",empid);
        values.put("empname",empname);
        values.put("empdepartment",empdept);

        long ID= db1.insert("empdetails",null,values);
        Toast.makeText(MainActivity.this,"No of record"+ID,Toast.LENGTH_LONG).show();


    }

    public void deleteUser(){
        SQLiteDatabase db1;
        db objdatabase = new db(this);
        db1 =objdatabase.getWritableDatabase();
        int ID=1;
        long delID = db1.delete("empdetails","id="+ID,null);
        objdatabase.close();
        Toast.makeText(MainActivity.this,"Record Deleted",Toast.LENGTH_LONG).show();

    }
}