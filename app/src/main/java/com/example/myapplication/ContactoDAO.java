package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactoDAO {
    private ArrayList<Contacto> lista;
    public ContactoDAO(ArrayList<Contacto> lista) {
        this.lista = lista;
    }
    public ArrayList<Contacto> getLista() {
        return lista;
    }

    public void agregarContacto(Contacto contacto){
        lista.add(contacto);
    }
    public  void editarContacto(int i,Contacto contacto){
        lista.set(i,contacto);
    }
    public  void eliminarContacto(int i){
        lista.remove(i);
    }
}
