package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorContacto extends RecyclerView.Adapter<AdaptadorContacto.ViewHolderDatos> {
    ArrayList<Contacto> lista;
    Context context;
    public AdaptadorContacto(ArrayList<Contacto> lista,Context context) {
        this.lista = lista;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_formato_contacto,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderDatos
                                             viewHolderDatos, final int i) {

        viewHolderDatos.tvnombre.setText(lista.get(i).nombre);
        viewHolderDatos.tvcel.setText(lista.get(i).cel);
        viewHolderDatos.tvcorreo.setText(lista.get(i).correo);
        viewHolderDatos.fila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater=LayoutInflater.from(context);
                View subview=inflater.inflate(R.layout.agregardatos,null);
                final EditText nombre,telefono,correo;
                nombre=(EditText)subview.findViewById(R.id.tvingnombre);
                telefono=(EditText)subview.findViewById(R.id.tvingtelefono);
                correo =(EditText)subview.findViewById(R.id.tvingcorreo);
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Editar contacto");
                builder.setView(subview);
                builder.setCancelable(false);
                builder.create();
                nombre.setText(lista.get(i).nombre,TextView.BufferType.EDITABLE);
                telefono.setText(lista.get(i).cel,TextView.BufferType.EDITABLE);
                correo.setText(lista.get(i).correo,TextView.BufferType.EDITABLE);
                builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ContactoDAO contactoDAO = new ContactoDAO(lista);
                        String nom=nombre.getText().toString();
                        String tel=telefono.getText().toString();
                        String cor=correo.getText().toString();
                        Contacto contacto=new Contacto(nom,tel,cor);
                        contactoDAO.editarContacto(i,contacto);
                        lista = contactoDAO.getLista();
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ContactoDAO contactoDAO = new ContactoDAO(lista);
                        contactoDAO.eliminarContacto(i);
                        lista = contactoDAO.getLista();
                        notifyDataSetChanged();
                    }
                });
                builder.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    public class ViewHolderDatos extends RecyclerView.ViewHolder{
        LinearLayout fila;
        TextView tvnombre;
        TextView tvcel;
        TextView tvcorreo;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            fila = (LinearLayout) itemView.findViewById(R.id.fila);
            tvnombre = (TextView)itemView.findViewById(R.id.tvnombre);
            tvcel = (TextView)itemView.findViewById(R.id.tvcel);
            tvcorreo = (TextView)itemView.findViewById(R.id.tvcorreo);
        }
    }
}
