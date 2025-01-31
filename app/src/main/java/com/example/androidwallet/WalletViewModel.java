package com.example.androidwallet;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WalletViewModel extends AndroidViewModel {
    private WalletRepository walletRepository;
    private LiveData<List<Crypto>> allCryptos;

    public WalletViewModel(Application application) {
        super(application);
        walletRepository = new WalletRepository(application);
        allCryptos = walletRepository.getListaMonedas();
    }

    // Método para obtener la lista de criptos
    public LiveData<List<Crypto>> getMonedas() {
        return allCryptos;
    }

    // Método para comprar criptomonedas
    public void buyCrypto(String nombre, double cantidad) {
        walletRepository.buyCrypto(nombre, cantidad);
    }

    // Método para enviar criptomonedas
    public boolean sendCrypto(String nombre, double cantidad) {
        return walletRepository.sendCrypto(nombre, cantidad);
    }
}
