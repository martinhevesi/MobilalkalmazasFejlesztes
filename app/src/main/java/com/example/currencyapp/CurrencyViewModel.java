package com.example.currencyapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CurrencyViewModel extends AndroidViewModel {
    private CurrencyRepository repository;
    private LiveData<List<Currency>> currencies;

    public CurrencyViewModel(@NonNull Application application) {
        super(application);
        this.repository = new CurrencyRepository(application);
        this.currencies = repository.getAllCurrencies();
    }

    public LiveData<List<Currency>> getAllCurrencies() {
        return this.currencies;
    }

    public void insert(Currency currency) {
        this.repository.insert(currency);
    }

    public void deleteAll() {
        this.repository.deleteAll();
    }
}
