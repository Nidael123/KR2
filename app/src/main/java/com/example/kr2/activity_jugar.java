package com.example.kr2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kr2.BaseManager.ManagerBase;
import com.example.kr2.Clases.Kanji;

import java.util.ArrayList;

public class activity_jugar extends AppCompatActivity {

    Button btn_next,btn_preview,btn_return;
    TextView txt_kanji;
    SQLiteDatabase bd;
    ManagerBase nuevabase;
    ArrayList<Kanji> list_kanjis;
    int place;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);

        nuevabase = new ManagerBase(this,"bd_kanji",null,1);
        place  = 0;
        list_kanjis = new ArrayList<Kanji>();
        cargarkanji_base();
        btn_next =(Button) findViewById(R.id.btn_next);
        btn_preview = (Button) findViewById(R.id.btn_preview);
        btn_return = (Button) findViewById(R.id.btn_regresar);
        txt_kanji = (TextView) findViewById(R.id.txt_kanji);
        txt_kanji.setText(list_kanjis.get(place).getKanji());





        btn_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(place == 0)
                {
                    place = list_kanjis.size() - 1;
                }else place--;
                txt_kanji.setText(list_kanjis.get(place).getKanji());
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(place == list_kanjis.size() - 1)
                {
                    place = 0;
                }else place++;
                txt_kanji.setText(list_kanjis.get(place).getKanji());
            }
        });
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void cargarkanji_base()
    {
        Kanji prueba;
        int i = 1;
        prueba = new Kanji();
        bd = nuevabase.getReadableDatabase();
        try {
            Cursor cursor = bd.rawQuery("select kanji from kanji",null);
            if(cursor.move(i))
            {
                do
                {
                    i++;
                    prueba.setKanji(cursor.getString(0));
                    llenararray(prueba.getKanji());
                    Log.d("dowhiole",cursor.getString(0));
                }while(cursor.moveToNext());
            }
        }catch (Exception e)
        {
            Log.d("Error en jugar:",e.toString());
        }
    }
    public  void  llenararray(String kanji)
    {
        Kanji kj = new Kanji();
        kj.setKanji(kanji);
        list_kanjis.add(kj);
    }
}