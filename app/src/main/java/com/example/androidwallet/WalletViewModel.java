package com.example.androidwallet;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class WalletViewModel extends AndroidViewModel {

    private final MutableLiveData<List<CryptoBalance>> monedas = new MutableLiveData<>();
    private final CryptoRepository cryptoRepository;
    private final Map<String, Double> preciosCrypto;
    private final Executor executor;

    public WalletViewModel(Application application) {
        super(application);

        cryptoRepository = new CryptoRepository(application);

        executor = Executors.newSingleThreadExecutor();

        cryptoRepository.getAllCryptoBalances().observeForever(monedas::setValue);

        preciosCrypto = new HashMap<>();
        preciosCrypto.put("Bitcoin", 100000.0);
        preciosCrypto.put("Ethereum", 3000.0);
        preciosCrypto.put("Cardano", 1.0);
        preciosCrypto.put("Solana", 250.0);
    }

    public LiveData<List<CryptoBalance>> getMonedas() {
        return monedas;
    }

    public void comprarCrypto(String nombre, double cantidadComprada) {
        executor.execute(() -> {
            List<CryptoBalance> listaActual = monedas.getValue();
            if (listaActual != null) {
                for (CryptoBalance crypto : listaActual) {
                    if (crypto.getNombre().equals(nombre)) {
                        double precioUnitario = preciosCrypto.getOrDefault(nombre, 0.0);
                        double valorEnEuros = cantidadComprada * precioUnitario;

                        crypto.setCantidad(crypto.getCantidad() + cantidadComprada);
                        crypto.setValorEnEuros(crypto.getValorEnEuros() + valorEnEuros);

                        cryptoRepository.insertCryptoBalance(crypto);
                        break;
                    }
                }
                monedas.postValue(listaActual);
            }
        });
    }

    public void enviarCrypto(String nombre, double cantidadEnviada) {
        executor.execute(() -> {
            List<CryptoBalance> listaActual = monedas.getValue();
            if (listaActual != null) {
                for (CryptoBalance crypto : listaActual) {
                    if (crypto.getNombre().equals(nombre)) {
                        if (crypto.getCantidad() >= cantidadEnviada) {
                            double precioUnitario = preciosCrypto.getOrDefault(nombre, 0.0);
                            double valorEnEuros = cantidadEnviada * precioUnitario;

                            crypto.setCantidad(crypto.getCantidad() - cantidadEnviada);
                            crypto.setValorEnEuros(crypto.getValorEnEuros() - valorEnEuros);

                            cryptoRepository.insertCryptoBalance(crypto);
                        } else {
                            return;
                        }
                        break;
                    }
                }
                monedas.postValue(listaActual);
            }
        });
    }
}
