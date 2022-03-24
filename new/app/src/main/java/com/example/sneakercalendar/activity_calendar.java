package com.example.sneakercalendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.sneakercalendar.DataServer.DBOpenHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class activity_calendar extends AppCompatActivity {
    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private List<Menu> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        initView();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.nav_calendar);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_community:
                        startActivity(new Intent(getApplicationContext()
                                ,activity_community.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_calendar:
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
    private void initView(){
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        //声明为瀑布流的布局，2列，垂直方向
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager); //recyclerView设置布局管理器
        initData();
        adapter = new RecyclerViewAdapter(this,list);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void initData(){

        DBOpenHelper dbsqlLiteOpenHelper = new DBOpenHelper(activity_calendar.this,"my.db",null,1);
        final SQLiteDatabase db = dbsqlLiteOpenHelper.getReadableDatabase();

       // Cursor cursor = db.query("sneaker",new String[]{"title","price","date","image","link"},"title=?",new String[]{"title"},null,null,null);
        Cursor cursor = db.query("sneaker", new String[]{"title","price","date","image","link"}, null, null, null, null, null);
        list = new ArrayList<>();
        while (cursor.moveToNext()){
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
            @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));
            @SuppressLint("Range") int price = cursor.getInt(cursor.getColumnIndex("price"));
            @SuppressLint("Range") String image = cursor.getString(cursor.getColumnIndex("image"));
            @SuppressLint("Range") String link = cursor.getString(cursor.getColumnIndex("link"));
            String priceStr = Integer.toString(price);
                list.add(new Menu(title,priceStr ,image));


       }


    }
}