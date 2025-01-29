package com.example.androidwallet;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

public class WalletViewModel extends ViewModel {
    private final MutableLiveData<List<String>> monedas = new MutableLiveData<>();

    public void setMonedas(List<String> lista) {
        monedas.setValue(lista);
    }

    public LiveData<List<String>> getMonedas() {
        return monedas;
    }
}
