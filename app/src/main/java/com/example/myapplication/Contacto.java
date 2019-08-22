package com.example.myapplication;

public class Contacto {
    int idcontacto;
    String nombre,cel,correo;

    public Contacto(String nombre, String cel, String correo) {
        this.nombre = nombre;
        this.cel = cel;
        this.correo = correo;
    }

    public Contacto(int idcontacto, String nombre, String cel, String correo) {
        this.idcontacto = idcontacto;
        this.nombre = nombre;
        this.cel = cel;
        this.correo = correo;
    }

    public int getIdcontacto() {
        return idcontacto;
    }

    public void setIdcontacto(int idcontacto) {
        this.idcontacto = idcontacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
