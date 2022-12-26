package com.example.kr2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class activity_menu extends AppCompatActivity {

    //cargar los datos del usaurio aqui o en la pestaña de main

    Button btn_jugar,btn_tienda,btn_vocabulario,btn_listado;
    private SharedPreferences preferences;
    TextView monedas,monedaspagadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        monedaspagadas = (TextView) findViewById(R.id.txt_monedaspagadas);
        monedas = (TextView) findViewById(R.id.txt_monedas);
        preferences = getSharedPreferences("Informacionusuario",MODE_PRIVATE);
        btn_jugar = (Button) findViewById(R.id.btn_jugar);
        btn_listado = (Button) findViewById(R.id.btn_listadok);
        btn_tienda = (Button) findViewById(R.id.btn_tienda);
        btn_vocabulario = (Button) findViewById(R.id.btn_vocabulario);
        Log.d("monedas",""+preferences.getInt("MonedasFisicas",0));
        btn_jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity_menu.this,MenuJugar.class));
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

        monedas.setText(""+preferences.getInt("Monedas",0));
        monedaspagadas.setText(""+preferences.getInt("MonedasFisicas",0));
    }
}