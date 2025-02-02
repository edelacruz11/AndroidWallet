package com.example.androidwallet;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CryptoRepository {

    private final CryptoBalanceDao cryptoBalanceDao;
    private final Executor executor;

    public CryptoRepository(Application application) {
        CryptoDatabase db = CryptoDatabase.getDatabase(application);
        cryptoBalanceDao = db.cryptoBalanceDao();
        executor = Executors.newSingleThreadExecutor();
    }

    // Obtener todas las criptos de la base de datos
    public LiveData<List<CryptoBalance>> getAllCryptoBalances() {
        return cryptoBalanceDao.getAll();
    }

    // Actualizar una criptomoneda
    public void insertCryptoBalance(CryptoBalance cryptoBalance) {
        executor.execute(() -> cryptoBalanceDao.insert(cryptoBalance));
    }

}
