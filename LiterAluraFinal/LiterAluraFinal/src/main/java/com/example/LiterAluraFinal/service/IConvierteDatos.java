package com.example.LiterAluraFinal.service;

public interface IConvierteDatos {
    <T> T traerDatos(String json, Class<T> tClass);
}
