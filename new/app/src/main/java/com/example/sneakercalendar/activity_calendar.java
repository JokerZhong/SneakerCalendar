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

public class activity_calendar extends AppCompatActivity {
    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private List<Menu> list;
    private LikeButton like_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        initView();


        DBOpenHelper dbsqlLiteOpenHelper = new DBOpenHelper(activity_calendar.this,"my.db",null,1);
        final SQLiteDatabase db = dbsqlLiteOpenHelper.getReadableDatabase();


        //get position from Adapter,jump to official website directly
        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemclick(View view, int position) {
                int id = position+1;
                Cursor cursor = db.rawQuery("select * from sneaker where sneakerID=?",new String[]{Integer.toString(id)});
                if(cursor.moveToFirst()) {
                    @SuppressLint("Range") String url = cursor.getString(cursor.getColumnIndex("link"));
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(url);
                    intent.setData(content_url);
                    startActivity(intent);
                }
            }
        });


        adapter.setOnlikeClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemclick(View view, int position) {
                int id = position+1;
                        Cursor cursor = db.rawQuery("update sneaker set likes = 1 where sneakerID=?",new String[]{Integer.toString(id)});
                        cursor.moveToFirst();

            }
        });

        adapter.setOnunlikeClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemclick(View view, int position) {
                int id = position+1;
                Cursor cursor = db.rawQuery("update sneaker set likes = 0 where sneakerID=?",new String[]{Integer.toString(id)});
                cursor.moveToFirst();

            }
        });





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
        //??????????????????????????????2??????????????????
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager); //recyclerView?????????????????????
        initData();
        adapter = new RecyclerViewAdapter(this,list);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);




    }

    private void initData(){

        DBOpenHelper dbsqlLiteOpenHelper = new DBOpenHelper(activity_calendar.this,"my.db",null,1);
        final SQLiteDatabase db = dbsqlLiteOpenHelper.getReadableDatabase();


       // Cursor cursor = db.query("sneaker",new String[]{"title","price","date","image","link"},"title=?",new String[]{"title"},null,null,null);
        Cursor cursor = db.query("sneaker", new String[]{"title","price","date","image","link","likes"}, null, null, null, null, null);
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