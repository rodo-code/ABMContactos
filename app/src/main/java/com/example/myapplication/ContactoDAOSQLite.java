package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public class ContactoDAOSQLite extends SQLiteOpenHelper implements ContactoDAO {

    public ContactoDAOSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public List<Contacto> listarTodos() {
        return null;
    }

    @Override
    public void alta(Contacto contacto) {

    }

    @Override
    public void baja(int id) {

    }

    @Override
    public void modificacion(int id, Contacto contacto) {

    }
}
