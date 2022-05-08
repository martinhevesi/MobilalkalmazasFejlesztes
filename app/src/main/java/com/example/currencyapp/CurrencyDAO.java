package com.example.currencyapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CurrencyDAO {

    @Insert
    void insert(Currency currency);

    @Query("DELETE FROM currency_table")
    void deleteAll();

    @Query("SELECT * FROM currency_table")
    LiveData<List<Currency>> getCurrencies();
}
