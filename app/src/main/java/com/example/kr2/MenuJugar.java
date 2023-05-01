package com.example.kr2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuJugar extends AppCompatActivity {

    Button btn_jugar,btn_practicar,btn_prueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_jugar);

        btn_jugar = (Button) findViewById(R.id.btn_jugar1);
        btn_practicar = (Button) findViewById(R.id.btn_practicar_mj);
        btn_prueba = (Button) findViewById(R.id.btn_mj_pruebas);

        btn_jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuJugar.this,activity_jugar.class));
            }
        });
        btn_practicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuJugar.this,activity_practicar.class));
            }
        });
        btn_jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuJugar.this,Pruebas.class));
            }
        });

    }
}