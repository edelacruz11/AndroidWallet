package com.example.androidwallet;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WalletViewModel extends ViewModel {
    private final MutableLiveData<List<Crypto>> monedas = new MutableLiveData<>();
    private final Map<String, Double> preciosCrypto; // Mapa para almacenar los precios fijos

    public WalletViewModel() {
        // Inicializar la lista de criptomonedas
        List<Crypto> listaInicial = new ArrayList<>();
        listaInicial.add(new Crypto("Bitcoin", 0, 0, R.drawable.bitcoin));
        listaInicial.add(new Crypto("Ethereum", 0, 0, R.drawable.ethereum));
        listaInicial.add(new Crypto("Cardano", 0, 0, R.drawable.cardano));
        listaInicial.add(new Crypto("Solana", 0, 0, R.drawable.solana));

        monedas.setValue(listaInicial);

        // Definir precios fijos
        preciosCrypto = new HashMap<>();
        preciosCrypto.put("Bitcoin", 100000.0);
        preciosCrypto.put("Ethereum", 3000.0);
        preciosCrypto.put("Cardano", 1.0);
        preciosCrypto.put("Solana", 250.0);
    }

    public void setMonedas(List<Crypto> lista) {
        monedas.setValue(lista);
    }

    public LiveData<List<Crypto>> getMonedas() {
        return monedas;
    }

    public void comprarCrypto(String nombre, double cantidadComprada) {
        List<Crypto> listaActual = monedas.getValue();
        if (listaActual != null) {
            for (Crypto crypto : listaActual) {
                if (crypto.getNombre().equals(nombre)) {
                    double precioUnitario = preciosCrypto.getOrDefault(nombre, 0.0);
                    double valorEnEuros = cantidadComprada * precioUnitario;

                    crypto.setCantidad(crypto.getCantidad() + cantidadComprada);
                    crypto.setValorEnEuros(crypto.getValorEnEuros() + valorEnEuros);
                    break;
                }
            }
            monedas.setValue(listaActual);
        }
    }

    public void enviarCrypto(String nombre, double cantidadEnviada) {
        List<Crypto> listaActual = monedas.getValue();
        if (listaActual != null) {
            for (Crypto crypto : listaActual) {
                if (crypto.getNombre().equals(nombre)) {
                    if (crypto.getCantidad() >= cantidadEnviada) { // Validar saldo suficiente
                        double precioUnitario = preciosCrypto.getOrDefault(nombre, 0.0);
                        double valorEnEuros = cantidadEnviada * precioUnitario;

                        crypto.setCantidad(crypto.getCantidad() - cantidadEnviada);
                        crypto.setValorEnEuros(crypto.getValorEnEuros() - valorEnEuros);
                    } else {
                        return; // No hacer nada si no hay saldo suficiente
                    }
                    break;
                }
            }
            monedas.setValue(listaActual);
        }
    }

    public LiveData<Double> getSaldoTotal() {
        MutableLiveData<Double> saldoTotal = new MutableLiveData<>(0.0);
        if (monedas.getValue() != null) {
            double total = 0.0;
            for (Crypto crypto : monedas.getValue()) {
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
