package com.example.androidwallet;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WalletRepository {
    private WalletDao walletDao;
    private LiveData<List<Crypto>> listaMonedas;

    public WalletRepository(Application application) {
        WalletDatabase db = WalletDatabase.getDatabase(application);
        walletDao = db.walletDao();
        listaMonedas = walletDao.getAllCryptos();  // Esto sigue trayendo la lista de criptos de la base de datos
    }

    public LiveData<List<Crypto>> getListaMonedas() {
        return listaMonedas;
    }

    // Compra de criptomonedas (solo actualiza la cantidad)
    public void buyCrypto(String nombre, double cantidad) {
        walletDao.updateQuantity(nombre, cantidad);  // Suma la cantidad a la criptomoneda
    }

    // Envío de criptomonedas (resta la cantidad)
    public boolean sendCrypto(String nombre, double cantidad) {
        int result = walletDao.sendCrypto(nombre, cantidad);  // Resta la cantidad si es posible
        return result > 0; // Si la cantidad fue suficiente, result será mayor que 0
    }
}
