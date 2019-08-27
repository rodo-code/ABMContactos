package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdaptadorContacto extends RecyclerView.Adapter<AdaptadorContacto.ViewHolderDatos> {
    List<Contacto> list;
    Context context;
    ContactDAO ContactDAO;
    public AdaptadorContacto(List<Contacto> list, Context context, com.example.myapplication.ContactDAO contactDAO) {
        this.list = list;
        this.context = context;
        this.ContactDAO = contactDAO;
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

        viewHolderDatos.tvnombre.setText(list.get(i).getNombre());
        viewHolderDatos.tvcel.setText(list.get(i).getCel());
        viewHolderDatos.tvcorreo.setText(list.get(i).getCorreo());
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
                nombre.setText(list.get(i).getNombre(),TextView.BufferType.EDITABLE);
                telefono.setText(list.get(i).getCel(),TextView.BufferType.EDITABLE);
                correo.setText(list.get(i).getCorreo(),TextView.BufferType.EDITABLE);
                builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nom=nombre.getText().toString();
                        String tel=telefono.getText().toString();
                        String cor=correo.getText().toString();
                        Contacto contacto=new Contacto(list.get(i).getIdcontacto(),nom,tel,cor);
                        ContactDAO.modificacion(contacto);
                        list = ContactDAO.listrTodos();
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ContactDAO.baja(list.get(i).getIdcontacto());
                        list = ContactDAO.listrTodos();
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
        return list.size();
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
