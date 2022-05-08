package com.example.currencyapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Currency.class}, version = 3, exportSchema = false)
public abstract class CurrencyRoomDatabase extends RoomDatabase {

    public abstract CurrencyDAO currencyDAO();

    private static CurrencyRoomDatabase instance;

    public static CurrencyRoomDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (CurrencyRoomDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.getApplicationContext(), CurrencyRoomDatabase.class,
                            "currency_database").fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }
}
