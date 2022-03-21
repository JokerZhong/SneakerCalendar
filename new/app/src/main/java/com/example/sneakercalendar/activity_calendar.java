package com.example.sneakercalendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

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
        list = new ArrayList<>();
        String imgUrl = "https://media.gq.com/photos/5ff89044efcefc18ea841495/master/w_960,c_limit/SP21_Nike_Sportswear_Dunk_27_original%20(1).jpg";
        for (int i = 0; i < 50; i++) {
            list.add(new Menu("Nike dunk" + i,"110",imgUrl));
        }
    }
}