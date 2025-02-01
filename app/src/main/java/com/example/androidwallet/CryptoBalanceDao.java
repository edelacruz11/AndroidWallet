package com.example.androidwallet;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CryptoBalanceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CryptoBalance cryptoBalance);

    @Query("SELECT * FROM crypto_balance")
    LiveData<List<CryptoBalance>> getAll();

    @Query("DELETE FROM crypto_balance WHERE nombre = :cryptoName")
    void delete(String cryptoName);
}
