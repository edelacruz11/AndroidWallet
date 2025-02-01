package com.example.androidwallet;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.AndroidViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class WalletViewModel extends AndroidViewModel {
    private final MutableLiveData<List<CryptoBalance>> monedas = new MutableLiveData<>();
    private final CryptoBalanceDao cryptoBalanceDao;
    private final Map<String, Double> preciosCrypto; // Mapa para almacenar los precios fijos
    private final Executor executor; // Executor para tareas en segundo plano

    public WalletViewModel(Application application) {
        super(application);

        // Inicializar la base de datos y DAO
        AppDatabase db = AppDatabase.getDatabase(application);
        cryptoBalanceDao = db.cryptoBalanceDao();

        // Inicializar el Executor
        executor = Executors.newSingleThreadExecutor();

        // Cargar las criptos desde la base de datos
        cryptoBalanceDao.getAll().observeForever(monedas::setValue);

        // Definir precios fijos
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

                        // Insertar o actualizar el CryptoBalance
                        cryptoBalanceDao.insert(crypto);
                        break;
                    }
                }
                monedas.postValue(listaActual); // Actualiza la lista en el hilo principal
            }
        });
    }

    public void enviarCrypto(String nombre, double cantidadEnviada) {
        executor.execute(() -> {
            List<CryptoBalance> listaActual = monedas.getValue();
            if (listaActual != null) {
                for (CryptoBalance crypto : listaActual) {
                    if (crypto.getNombre().equals(nombre)) {
                        if (crypto.getCantidad() >= cantidadEnviada) { // Validar saldo suficiente
                            double precioUnitario = preciosCrypto.getOrDefault(nombre, 0.0);
                            double valorEnEuros = cantidadEnviada * precioUnitario;

                            crypto.setCantidad(crypto.getCantidad() - cantidadEnviada);
                            crypto.setValorEnEuros(crypto.getValorEnEuros() - valorEnEuros);

                            // Insertar o actualizar el CryptoBalance
                            cryptoBalanceDao.insert(crypto);
                        } else {
                            return; // No hacer nada si no hay saldo suficiente
                        }
                        break;
                    }
                }
                monedas.postValue(listaActual); // Actualiza la lista en el hilo principal
            }
        });
    }

    public LiveData<Double> getSaldoTotal() {
        MutableLiveData<Double> saldoTotal = new MutableLiveData<>(0.0);
        if (monedas.getValue() != null) {
            double total = 0.0;
            for (CryptoBalance crypto : monedas.getValue()) {
                total += crypto.getValorEnEuros();
            }
            saldoTotal.setValue(total);
        }
        return saldoTotal;
    }

    public double getPrecioCrypto(String nombre) {
        return preciosCrypto.getOrDefault(nombre, 0.0);
    }
}
