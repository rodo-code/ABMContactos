package com.example.myapplication;

import java.util.List;

public interface ABM<T> {
    List<T> listrTodos();
    void alta(T t);
    void baja(int id);
    void modificacion(T t);
}
