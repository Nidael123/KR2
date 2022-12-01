package com.example.kr2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_menu extends AppCompatActivity {

    //cargar los datos del usaurio aqui o en la pestaña de main

    Button btn_jugar,btn_tienda,btn_vocabulario,btn_listado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btn_jugar = (Button) findViewById(R.id.btn_jugar);
        btn_listado = (Button) findViewById(R.id.btn_listadok);
        btn_tienda = (Button) findViewById(R.id.btn_tienda);
        btn_vocabulario = (Button) findViewById(R.id.btn_vocabulario);

        btn_jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity_menu.this,activity_jugar.class));
            }
        });
        btn_listado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity_menu.this,activity_listadok.class));
            }
        });
        btn_tienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity_menu.this,activity_tienda.class));
            }
        });
        btn_vocabulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity_menu.this,activity_vocabulario.class));
            }
        });

    }
}