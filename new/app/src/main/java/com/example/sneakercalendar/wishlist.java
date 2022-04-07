package com.example.sneakercalendar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class wishlist extends AppCompatActivity {
    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private List<Menu> list;

    public wishlist() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wishlist);
        
        initView();
    }

    private void initView() {
    }
}