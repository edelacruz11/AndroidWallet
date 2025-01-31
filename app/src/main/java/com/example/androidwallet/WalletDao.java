package com.example.androidwallet;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WalletDao {

    @Query("SELECT * FROM wallet_table ORDER BY nombre ASC")
    LiveData<List<Crypto>> getAllCryptos();

    @Insert(onConflict = OnConflictStrategy.IGNORE)  // No se insertan nuevas monedas si ya existen
    void insertAll(List<Crypto> cryptos);

    @Query("UPDATE wallet_table SET cantidad = cantidad + :cantidad WHERE nombre = :nombre")
    void updateQuantity(String nombre, double cantidad);

    @Query("UPDATE wallet_table SET cantidad = cantidad - :cantidad WHERE nombre = :nombre AND cantidad >= :cantidad")
    int sendCrypto(String nombre, double cantidad);
}
