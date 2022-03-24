package com.example.sneakercalendar;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.database.*;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sneakercalendar.DataServer.DBOpenHelper;

public class activity_signup extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        DBOpenHelper dbsqlLiteOpenHelper = new DBOpenHelper(activity_signup.this,"my.db",null,1);
        final SQLiteDatabase db = dbsqlLiteOpenHelper.getWritableDatabase();

        Button button_signup = findViewById(R.id.button_signup);
        EditText userName = findViewById(R.id.userName);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);
        EditText phoneNumber = findViewById(R.id.phoneNumber);

        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("name",userName.getText().toString());
                values.put("email",etEmail.getText().toString());
                values.put("password",etPassword.getText().toString());
                values.put("phone",phoneNumber.getText().toString());
                db.insert("user",null,values);

                Context context = getApplicationContext();
                CharSequence text = "Success!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                Intent intent =new Intent(activity_signup.this, activity_login.class);
                startActivity(intent);
            }
        });
    }
}