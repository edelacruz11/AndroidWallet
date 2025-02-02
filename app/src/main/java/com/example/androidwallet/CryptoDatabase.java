package com.example.androidwallet;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {CryptoBalance.class,}, version = 2)
public abstract class CryptoDatabase extends RoomDatabase {
    private static volatile CryptoDatabase INSTANCE;

    public abstract CryptoBalanceDao cryptoBalanceDao();

    // Obtener instancia de la base de datos
    public static CryptoDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CryptoDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    CryptoDatabase.class, "cryptodatabase")
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    // Datos predeterminados
                                    new Thread(() -> {
                                        CryptoBalanceDao dao = INSTANCE.cryptoBalanceDao();
                                        dao.insert(new CryptoBalance("Bitcoin", 0, 0.0));
                                        dao.insert(new CryptoBalance("Ethereum", 0, 0.0));
                                        dao.insert(new CryptoBalance("Cardano", 0, 0.0));
                                        dao.insert(new CryptoBalance("Solana", 0, 0.0));
                                    }).start();
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
