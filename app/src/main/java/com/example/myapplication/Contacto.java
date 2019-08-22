package com.example.myapplication;

public class Contacto {
    String nombre,cel,correo;

    public Contacto(String nombre, String cel, String correo) {
        this.nombre = nombre;
        this.cel = cel;
        this.correo = correo;
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
