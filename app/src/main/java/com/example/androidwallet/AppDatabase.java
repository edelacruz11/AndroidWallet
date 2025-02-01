package com.example.androidwallet;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {CryptoBalance.class,}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public abstract CryptoBalanceDao cryptoBalanceDao();

    // Método para obtener la instancia de la base de datos
    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "nombre_de_base_de_datos")
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    // Insertar datos predeterminados aquí
                                    new Thread(() -> {
                                        CryptoBalanceDao dao = INSTANCE.cryptoBalanceDao();
                                        dao.insert(new CryptoBalance("Bitcoin", 0, 0.0));
                                        dao.insert(new CryptoBalance("Ethereum", 0, 0.0));
                                        dao.insert(new CryptoBalance("Cardano", 0, 0.0));
                                        dao.insert(new CryptoBalance("Solana", 0, 0.0));
                                        // Agregar más datos si es necesario
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
