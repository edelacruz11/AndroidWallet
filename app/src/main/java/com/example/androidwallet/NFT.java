package com.example.androidwallet;

public class NFT {
    private String nombre;
    private int imagenResId;

    public NFT(String nombre, int imagenResId) {
        this.nombre = nombre;
        this.imagenResId = imagenResId;
    }

    public String getNombre() {
        return nombre;
    }

    public int getImagenResId() {
        return imagenResId;
    }
}
