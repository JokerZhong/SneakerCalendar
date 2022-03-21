package com.example.sneakercalendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class activity_home extends AppCompatActivity {
    private Button btn_log,btn_sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_log=findViewById(R.id.button_login);
        btn_sign=findViewById(R.id.button_signup);

        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(activity_home.this, activity_login.class);
                startActivity(intent);
            }
        });
        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(activity_home.this, activity_signup.class);
                startActivity(intent);
            }
        });
    }
}