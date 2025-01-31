package com.example.androidwallet;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "wallet_table")
public class Crypto {
    @PrimaryKey
    @NonNull
    private String nombre;
    private double cantidad;
    private int imagenResId;  // Añadir este campo

    public Crypto(@NonNull String nombre, double cantidad, int imagenResId) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.imagenResId = imagenResId;
    }

    public String getNombre() {
        return nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public int getImagenResId() {
        return imagenResId;
    }
}
