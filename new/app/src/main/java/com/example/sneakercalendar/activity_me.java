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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sneakercalendar.DataServer.DBOpenHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.like.LikeButton;
import com.like.OnLikeListener;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class activity_me extends AppCompatActivity {

    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private List<Menu> list;
    private ImageView img_fwler,img_fol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        initView();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.nav_me);

         img_fwler = findViewById(R.id.follower);
         img_fol = findViewById(R.id.following);

         img_fwler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_me.this,follower_page.class);
                startActivity(intent);
            }
        });

         img_fol.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_me.this,following_page.class);
                startActivity(intent);
            }
        });

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
                        startActivity(new Intent(getApplicationContext()
                                ,activity_calendar.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_me:

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

        DBOpenHelper dbsqlLiteOpenHelper = new DBOpenHelper(activity_me.this,"my.db",null,1);
        final SQLiteDatabase db = dbsqlLiteOpenHelper.getReadableDatabase();


        // Cursor cursor = db.query("sneaker",new String[]{"title","price","date","image","link"},"title=?",new String[]{"title"},null,null,null);
       // Cursor cursor = db.query("sneaker", new String[]{"title","price","date","image","link","likes"}, null, null, null, null, null);
        Cursor cursor = db.rawQuery("select * from sneaker where likes=?",new String[]{Integer.toString(1)});
        list = new ArrayList<>();
        while (cursor.moveToNext()){
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
            @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));
            @SuppressLint("Range") int price = cursor.getInt(cursor.getColumnIndex("price"));
            @SuppressLint("Range") String image = cursor.getString(cursor.getColumnIndex("image"));
            @SuppressLint("Range") String link = cursor.getString(cursor.getColumnIndex("link"));
            @SuppressLint("Range") int likes = cursor.getInt(cursor.getColumnIndex("likes"));
            String priceStr = Integer.toString(price);
            list.add(new Menu(title,priceStr,image,date,likes));


        }


    }



}