package com.example.androidwallet;

public class NFT {
    private String nombre;
    private int imagenResId; // ID del recurso para la imagen del NFT

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
