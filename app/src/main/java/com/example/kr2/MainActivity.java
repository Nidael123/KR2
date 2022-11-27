package com.example.kr2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.kr2.BaseManager.ManagerBase;
import com.example.kr2.Clases.Kanji;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button start,login;
    int versiondatabase,updatedata,versionupdate;//con esto hacemos los cambios en las diferentes versiones de laa base
    String urlkanji;
    ContentValues campos;
    JSONObject jsonObject;
    RequestQueue n_requeriminto;
    SQLiteDatabase bd;
    ManagerBase nuevabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        versionupdate = 1 ; //aumentar cada vez que se aumente la base de datos
        urlkanji = getString(R.string.api_kanji);
        versiondatabase = 1;//cambiar cada vwez que la base se modifique
        start = (Button) findViewById(R.id.btn_start);
        login = (Button) findViewById(R.id.btn_login);
        campos = new ContentValues();
        nuevabase =new ManagerBase(this,"bd_kanji",null,versiondatabase);
        updatedata = buscarversion();

        if(versionupdate > updatedata)
        {
            //enviar a segundo plano y solo va a actualizar los datos de los kanjis
            bd = nuevabase.getWritableDatabase();
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    cargar_kanji();
                }
            });
            campos.clear();
            campos.put("id_version",versionupdate);
            Long ingreso = bd.insert("version","id_version",campos);
            Toast.makeText(this, "Datos Actualizados", Toast.LENGTH_SHORT).show();
        }

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Prueba","enviar al a");
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,activity_login.class));
            }
        });

    }
    public void cargar_kanji()
    {

        ContentValues campos1 = new ContentValues();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlkanji, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("datos");
                    Log.d("main activity",response.getJSONArray("datos").toString() );
                    for(int i=0;i<=jsonArray.length()-1;i++) {
                        jsonObject = new JSONObject(jsonArray.getJSONObject(i).toString());
                        /*llenado de la base de datos */

                        campos1.put("id_kanji",Integer.parseInt(jsonObject.getString("id_kanji").toString()));
                        campos1.put("kanji",jsonObject.getString("kanji").toString());
                        campos1.put("nivel",0);
                        campos1.put("sig_ingle",jsonObject.getString("ingles").toString());
                        campos1.put("sig_español",jsonObject.getString("significado").toString());
                        campos1.put("id_tipo",jsonObject.getString("id_tipo").toString());

                        Log.d("error al consultar",jsonObject.getString("kanji").toString());
                        Long ingreso = bd.insert("kanji","id_kanji",campos1);
                        Log.d("main cargar kanji","ingreso a base :"+ingreso);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //verifico el error
                Log.d("error al consultar",error.toString());
            }
        });
        n_requeriminto = Volley.newRequestQueue(this);
        n_requeriminto.add(jsonObjectRequest);
    }
    public int buscarversion()
    {
        int version = 0;
        SQLiteDatabase db = nuevabase.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("select max(id_version) from version", null);
            cursor.moveToFirst();
            version = cursor.getInt(0);
            Log.d("for","cantidad:" + cursor.getInt(0));
        }
        catch (Exception e){
            Log.d("error de base","" + e.toString());
            version = 0;
        }
        return version;

    }
}