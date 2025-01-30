package com.example.androidwallet;

public class Crypto {
    private String nombre;
    private double cantidad;
    private double valorEnEuros;
    private int imagenResId; // ID de recurso para la imagen

    public Crypto(String nombre, double cantidad, double valorEnEuros, int imagenResId) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.valorEnEuros = valorEnEuros;
        this.imagenResId = imagenResId;
    }

    public String getNombre() {
        return nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public double getValorEnEuros() {
        return valorEnEuros;
    }

    public int getImagenResId() {
        return imagenResId;
    }
}
