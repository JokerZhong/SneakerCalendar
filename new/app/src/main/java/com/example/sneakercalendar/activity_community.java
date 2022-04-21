package com.example.sneakercalendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Comment;

import java.util.List;
import com.example.sneakercalendar.DataServer.DBOpenHelper;

public class activity_community extends AppCompatActivity {

    ImageView imgPost,imgUserPost,imgCurrentUser;
    TextView txtPostDesc,txtPostDateName,txtPostTitle;
    EditText editTextComment;
    Button btnAddComment;
    String PostKey;
    RecyclerView RvComment;

    List<Comment> listComment;
    static String COMMENT_KEY = "Comment" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.nav_community);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_community:

                        return true;
                    case R.id.nav_calendar:
                        startActivity(new Intent(getApplicationContext()
                                ,activity_calendar.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_me:
                        startActivity(new Intent(getApplicationContext()
                                ,activity_me.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });
    }
}