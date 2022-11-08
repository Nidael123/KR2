package com.example.kr2.BaseManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ManagerBase extends SQLiteOpenHelper {
    //vocabulario hacer cuando cargue la pantlla de vocabulario
    String t_kanji = "create table kanji(id_kanji int ,kanji varchar(25),nivel int default 0,sig_ingle varchar(100),sig_español varchar(100),id_tipo int)";
    String t_versionbase = "create table version(id_version int)";
    String t_kanjiestado = "create table  kanjiestado(id_kanji int,estado bool default 0)";
    int updatedatabase;//verifico que version de los datos hay en la base de datos

    public ManagerBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(t_versionbase);
        database.execSQL(t_kanji);
        database.execSQL(t_kanjiestado);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int versionanterior, int versionnueva) {
        //version 1
        if(versionanterior == 1)
        {
            database.execSQL("drop table if exists kanji ");
            database.execSQL("drop table if exists t_versionbase ");//borro las tablas en el caso de existir
            onCreate(database);
        }
        if(versionanterior == 2)
        {
            database.execSQL("drop table if exists kanji ");
        }
    }

    public void prueba(SQLiteDatabase database)
    {
        database.execSQL("");
    }

}
