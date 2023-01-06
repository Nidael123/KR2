package com.example.kr2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.kr2.BaseManager.ManagerBase;
import com.example.kr2.Clases.Kanji;

import java.util.ArrayList;

public class activity_listadok extends AppCompatActivity {

    ArrayList<String> listado_kanjis;
    ManagerBase bd;
    SQLiteDatabase bdcache;
    ListView list_kanji;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listadok);
        list_kanji = (ListView) findViewById(R.id.list_view);
        bd = new ManagerBase(this,"bd_kanji",null,1);
        listado_kanjis = new ArrayList<String>();
        cargar_kanji();
        adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_dropdown_item_1line,listado_kanjis);
        list_kanji.setAdapter(adapter);
    }

    public void cargar_kanji()
    {
        Integer i = 1;
        bdcache = bd.getReadableDatabase();
        try {
            Cursor cursor = bdcache.rawQuery("Select kanji from kanji",null);
            if(cursor.move(i))
            {
                do
                {
                    listado_kanjis.add(cursor.getString(0));
                    Log.d("dowhiole",cursor.getString(0));
                }while(cursor.moveToNext());
            }
        }catch (Exception e)
        {
            Log.d("error","error"+e.toString());
        }
    }

}