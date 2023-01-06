package com.example.kr2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.kr2.BaseManager.ManagerBase;
import com.example.kr2.Clases.Kanji;

import java.util.ArrayList;

public class Pruebas extends AppCompatActivity{

    Button btn_1,btn_2,btn_3;
    TextView txt_kanji;
    SQLiteDatabase bd;
    ManagerBase nuevabase;
    ArrayList<Kanji> list_kanjis;
    int numeroKanji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebas);

        txt_kanji =(TextView) findViewById(R.id.txt_kanji_prueba);
        btn_1 = (Button) findViewById(R.id.btn_prueba_1);
        btn_2 = (Button) findViewById(R.id.btn_prueba_2);
        btn_3 = (Button) findViewById(R.id.btn_prueba_3);
        nuevabase = new ManagerBase(this,"bd_kanji",null,1);
        list_kanjis = new ArrayList<>();
        cargarkanji_base();

        numeroKanji = (int) (Math.random() * list_kanjis.size()-1);
        Log.d("Numero aleatorio",""+numeroKanji);
        txt_kanji.setText(list_kanjis.get(numeroKanji).getKanji().toString());
        btn_1.setText(list_kanjis.get(numeroKanji).getSig_español());
        btn_2.setText(list_kanjis.get((int) (Math.random() * list_kanjis.size()-1)).getSig_español());
        btn_3.setText(list_kanjis.get((int) (Math.random() * list_kanjis.size()-1)).getSig_español());

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
            Cursor cursor = bd.rawQuery("select kanji,sig_ingle,sig_español from kanji",null);
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