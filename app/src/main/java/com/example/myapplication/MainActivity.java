package com.example.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Contacto> lista;
    RecyclerView recyclerView;
    ContactoDAO sqlite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sqlite = new ContactoDAOSQLite(this,"dbcontacto",null,1);
        lista = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.rvContenedor);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        AdaptadorContacto adaptador = new AdaptadorContacto(lista,this,sqlite);
        recyclerView.setAdapter(adaptador);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfazAgregarContacto();
            }
        });
    }
    private void interfazAgregarContacto(){
        LayoutInflater inflater=LayoutInflater.from(this);
        View subview=inflater.inflate(R.layout.agregardatos,null);
        final EditText nombre,telefono,correo;
        nombre=(EditText)subview.findViewById(R.id.tvingnombre);
        telefono=(EditText)subview.findViewById(R.id.tvingtelefono);
        correo =(EditText)subview.findViewById(R.id.tvingcorreo);
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Agregar contacto");
        builder.setView(subview);
        builder.setCancelable(true);
        builder.create();
        builder.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nom=nombre.getText().toString();
                String tel=telefono.getText().toString();
                String cor=correo.getText().toString();
                Contacto agenda=new Contacto(nom,tel,cor);
                sqlite.alta(agenda);
                lista = sqlite.listarTodos();
                Toast.makeText(getApplicationContext(),"El contacto se guardo correctamente",Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
}
