package com.example.androidwallet;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "crypto_balance")
public class CryptoBalance {
    @PrimaryKey
    @NonNull
    private String nombre;  // Usamos el nombre de la crypto como clave primaria
    private double cantidad;
    private double valorEnEuros;

    public CryptoBalance(@NonNull String nombre, double cantidad, double valorEnEuros) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.valorEnEuros = valorEnEuros;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getValorEnEuros() {
        return valorEnEuros;
    }

    public void setValorEnEuros(double valorEnEuros) {
        this.valorEnEuros = valorEnEuros;
    }
}
