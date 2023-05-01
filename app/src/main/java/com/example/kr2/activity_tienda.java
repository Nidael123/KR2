package com.example.kr2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.kr2.Clases.AdapterTienda;

public class activity_tienda extends AppCompatActivity {

    RecyclerView recicler;
    AdapterTienda adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);

        recicler = findViewById(R.id.recicler_tienda_item);

        recicler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter = new AdapterTienda(8);

    }
}