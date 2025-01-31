package com.example.androidwallet;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Crypto.class}, version = 1, exportSchema = false)
public abstract class WalletDatabase extends RoomDatabase {
    public abstract WalletDao walletDao();

    private static volatile WalletDatabase INSTANCE;

    // ExecutorService para operaciones en segundo plano
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static WalletDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WalletDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    WalletDatabase.class, "wallet_database")
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    // Insertar criptomonedas estáticas solo cuando se cree la base de datos por primera vez
                                    databaseWriteExecutor.execute(() -> {
                                        WalletDao dao = INSTANCE.walletDao();
                                        // Comprobar si las criptos ya están en la base de datos para evitar duplicados
                                        if (dao.getAllCryptos().getValue() == null || dao.getAllCryptos().getValue().isEmpty()) {
                                            List<Crypto> staticCryptos = Arrays.asList(
                                                    new Crypto("Bitcoin", 0.0, R.drawable.bitcoin),
                                                    new Crypto("Ethereum", 0.0, R.drawable.ethereum),
                                                    new Crypto("Cardano", 0.0, R.drawable.cardano),
                                                    new Crypto("Solana", 0.0, R.drawable.solana)
                                            );
                                            dao.insertAll(staticCryptos);  // Insertar las criptos estáticas
                                        }
                                    });
                                }
                            })
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
