package com.example.kr2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kr2.BaseManager.ManagerBase;
import com.example.kr2.Clases.Kanji;

import java.util.ArrayList;

public class activity_jugar extends AppCompatActivity {

    Button btn_next,btn_preview,btn_return;
    TextView txt_kanji,txt_sigingles,txt_sigespano;
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
        btn_next =(Button) findViewById(R.id.btn_prueba_3);
        btn_preview = (Button) findViewById(R.id.btn_prueba_1);
        btn_return = (Button) findViewById(R.id.btn_prueba_2);
        txt_kanji = (TextView) findViewById(R.id.txt_kanji_prueba);
        txt_sigingles = (TextView)findViewById(R.id.txt_sigingles);
        txt_sigespano = (TextView)findViewById(R.id.txt_sigespanol);
        txt_kanji.setText(list_kanjis.get(place).getKanji());





        btn_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(place == 0)
                {
                    place = list_kanjis.size() - 1;
                }else place--;
                txt_kanji.setText(list_kanjis.get(place).getKanji());
                txt_sigingles.setText(list_kanjis.get(place).getSig_ingle());
                txt_sigespano.setText(list_kanjis.get(place).getSig_español());
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
                txt_sigingles.setText(list_kanjis.get(place).getSig_ingle());
                txt_sigespano.setText(list_kanjis.get(place).getSig_español());

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
            Cursor cursor = bd.rawQuery("select kanji,sig_ingle,sig_español,nivel from kanji",null);
            if(cursor.move(i))
            {
                do
                {
                    i++;
                    prueba.setKanji(cursor.getString(0));
                    prueba.setSig_ingle(cursor.getString(1));
                    prueba.setSig_español(cursor.getString(2));
                    llenararray(prueba);
                    Log.d("dowhiole",cursor.getString(0));
                }while(cursor.moveToNext());
            }
        }catch (Exception e)
        {
            Log.d("Error en jugar:",e.toString());
        }
    }
    public  void  llenararray(Kanji kanji)
    {
        Kanji kj = new Kanji();
        kj.setKanji(kanji.getKanji());
        kj.setSig_ingle(kanji.getSig_ingle());
        kj.setSig_español(kanji.getSig_español());
        list_kanjis.add(kj);
    }
}