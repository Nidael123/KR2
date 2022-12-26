package com.example.kr2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class activity_login extends AppCompatActivity {

    Button btn_guardar,btn_volver;
    EditText txt_nombre,txt_apellido,txt_nick,txt_clave,txt_correo;
    String api_user;
    RequestQueue n_requerimiento;
    JsonObjectRequest jsonobject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        n_requerimiento = Volley.newRequestQueue(this);
        api_user = getString(R.string.api_users);
        btn_guardar = (Button) findViewById(R.id.btn_actualizar);
        btn_volver = (Button) findViewById(R.id.btn_volver);
        txt_nombre = (EditText) findViewById(R.id.text_nombre);
        txt_apellido = (EditText)findViewById(R.id.text_apellido);
        txt_nick = (EditText) findViewById(R.id.text_nick);
        txt_clave = (EditText)findViewById(R.id.txt_clave);
        txt_correo = (EditText)findViewById(R.id.txt_correo);

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validar si esta vacio
                guardarusuario();
            }
        });
        btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void guardarusuario()
    {
        StringRequest requerimiento = new StringRequest(Request.Method.POST, api_user, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Toast.makeText(activity_login.this,"Ingreso exitoso",Toast.LENGTH_SHORT);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity_login.this,"Error de ingreso intentelo despues",Toast.LENGTH_SHORT);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<>();
                parametros.put("v_correo",txt_correo.getText().toString());
                parametros.put("v_nombre",txt_nombre.getText().toString());
                parametros.put("v_apellido",txt_apellido.getText().toString());
                parametros.put("v_contrasena",txt_clave.getText().toString());
                parametros.put("v_nick",txt_nick.getText().toString());

                return parametros;
            }
        };
        n_requerimiento = Volley.newRequestQueue(this);
        n_requerimiento.add(requerimiento);
    }
}
