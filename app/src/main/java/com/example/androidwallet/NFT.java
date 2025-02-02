package com.example.androidwallet;

import android.os.Parcel;
import android.os.Parcelable;

public class NFT implements Parcelable {
    private String nombre;
    private int imagenResId;

    public NFT(String nombre, int imagenResId) {
        this.nombre = nombre;
        this.imagenResId = imagenResId;
    }

    protected NFT(Parcel in) {
        nombre = in.readString();
        imagenResId = in.readInt();
    }

    public static final Creator<NFT> CREATOR = new Creator<NFT>() {
        @Override
        public NFT createFromParcel(Parcel in) {
            return new NFT(in);
        }

        @Override
        public NFT[] newArray(int size) {
            return new NFT[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public int getImagenResId() {
        return imagenResId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeInt(imagenResId);
    }
}