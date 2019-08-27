package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ContactDAOSQLite extends SQLiteOpenHelper implements ContactDAO {

    public ContactDAOSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            String sql="CREATE TABLE contacto(" +
                    "idcontacto INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT," +
                    "celular TEXT," +
                    "correo TEXT);";
            db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS contacto");
        onCreate(db);
    }

    @Override
    public List<Contacto> listrTodos() {
        String sql="select * from contacto";
        SQLiteDatabase db=this.getReadableDatabase();
        List<Contacto> listContactos=new ArrayList<>();

        Cursor cursor=db.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {

                int id= Integer.parseInt(cursor.getString(0));
                String nombre=cursor.getString(1);
                String celular=cursor.getString(2);
                String correo=cursor.getString(3);
                listContactos.add(new Contacto(id,nombre,celular,correo));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return listContactos;
    }

    @Override
    public void alta(Contacto contacto) {
        ContentValues values=new ContentValues();
        values.put("nombre",contacto.getNombre());
        values.put("celular",contacto.getCel());
        values.put("correo",contacto.getCorreo());
        SQLiteDatabase database=this.getWritableDatabase();
        database.insert("contacto",null,values);
    }

    @Override
    public void baja(int id) {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("contacto","idcontacto = ?",new String[]{String.valueOf(id)});

    }

    @Override
    public void modificacion(Contacto contacto) {

        ContentValues values=new ContentValues();
        values.put("nombre",contacto.getNombre());
        values.put("celular",contacto.getCel());
        values.put("correo",contacto.getCorreo());
        SQLiteDatabase db= this.getWritableDatabase();
        db.update("contacto",values,"idcontacto = ?",new String[]{String.valueOf(contacto.getIdcontacto())});
    }
}
