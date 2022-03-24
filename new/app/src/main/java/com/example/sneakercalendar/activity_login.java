package com.example.sneakercalendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sneakercalendar.DataServer.DBOpenHelper;

public class activity_login extends AppCompatActivity {

    private Button btn_log;
    private String email,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DBOpenHelper dbsqlLiteOpenHelper = new DBOpenHelper(activity_login.this,"my.db",null,1);
        final SQLiteDatabase db = dbsqlLiteOpenHelper.getReadableDatabase();

        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);

        btn_log=findViewById(R.id.button_login);

        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = etEmail.getText().toString().trim();
                pass = etPassword.getText().toString().trim();


                if (!email.isEmpty() && !pass.isEmpty()) {
                    Cursor cursor = db.query("user", new String[]{"email", "password"}, "email=?", new String[]{email}, null, null, "password");
                    if (cursor != null && cursor.getCount() >= 1) {
                        while (cursor.moveToNext()) {
                            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));
                            if (pass.equals(password)) {
                                Context context = getApplicationContext();
                                CharSequence text = "Success!";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                                Intent intent =new Intent(activity_login.this, activity_calendar.class);
                                startActivity(intent);
                                break;
                            }else{
                                Context context = getApplicationContext();
                                CharSequence text = "Wrong password or email!";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                            }

                        }
                    }
                }



            }
        });
    }
}