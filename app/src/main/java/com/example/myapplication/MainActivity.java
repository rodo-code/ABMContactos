package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Variables para la lista de contactos y la base de datos

    List<Contacto> list;
    RecyclerView recyclerView;
    ContactDAO sqlite;

    //Inicializacion de la aplicación y enlace con el xml activity_main.xml
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sqlite = new ContactDAOSQLite(this,"dbcontacto",null,1);
        list = new ArrayList<>();

        //Creacion del reciclerView

        recyclerView = (RecyclerView)findViewById(R.id.rvContenedor);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
            list=sqlite.listrTodos();

            if(list.size()>0){
                AdaptadorContacto adaptador = new AdaptadorContacto(list,this,sqlite);
                recyclerView.setAdapter(adaptador);

            }else{
                Toast.makeText(this, "Sin contactos", Toast.LENGTH_LONG).show();

            }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfazAgregarContacto();
            }
        });
    }

    //AlertDialog para la creacion de un contacto

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
                list.add(agenda);
                sqlite.alta(agenda);
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                finish();
                startActivity(i);
                Toast.makeText(getApplicationContext(),"El contacto se guardo correctamente",Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
}
