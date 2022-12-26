package com.example.kr2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.security.keystore.KeyNotYetValidException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kr2.BaseManager.ManagerBase;
import com.example.kr2.Clases.Kanji;

import java.util.ArrayList;

public class activity_practicar extends AppCompatActivity {

    Button btn_siguiete,btn_anterior,btn_regresar;
    TextView txt_kanji_pra;
    SQLiteDatabase bd;
    ManagerBase nuevabase;
    ArrayList<Kanji> list_kanjis;
    int place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practicar);

        place = 0;
        list_kanjis = new ArrayList<>();
        nuevabase = new ManagerBase(this,"bd_kanji",null,1);
        btn_siguiete = (Button) findViewById(R.id.btn_practica_sig);
        btn_anterior = (Button) findViewById(R.id.btn_practica_anterior);
        btn_regresar = (Button) findViewById(R.id.btn_practica_regresar);
        txt_kanji_pra = (TextView) findViewById(R.id.txt_kanji_pr);
        cargarkanji_base();

        btn_anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(place == 0)
                {
                    place = list_kanjis.size() - 1;
                }else place--;
                txt_kanji_pra.setText(list_kanjis.get(place).getKanji());
            }
        });
        btn_siguiete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(place == list_kanjis.size() - 1)
                {
                    place = 0;
                }else place++;
                txt_kanji_pra.setText(list_kanjis.get(place).getKanji());

            }
        });
        btn_regresar.setOnClickListener(new View.OnClickListener() {
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