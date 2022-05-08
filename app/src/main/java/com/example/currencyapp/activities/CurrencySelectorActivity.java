package com.example.currencyapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.currencyapp.Currency;
import com.example.currencyapp.CurrencyViewModel;
import com.example.currencyapp.CurrencyCheckboxAdapter;
import com.example.currencyapp.R;

import java.util.ArrayList;

public class CurrencySelectorActivity extends AppCompatActivity {

    CurrencyViewModel viewModel;

    final String[] currencies = {"Válassz az alábbiak közül", "USD", "EUR", "HUF", "RSD", "CHF", "BTC", "GBP", "RUB", "UAH"};
    ArrayList<Currency> listCurrencies = new ArrayList<>();
    ArrayList<Currency> currencyList = new ArrayList<>();
    CurrencyCheckboxAdapter currencyCheckboxAdapter;

    Spinner spinner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_selector);

        spinner = findViewById(R.id.currencies);

        viewModel = new CurrencyViewModel(getApplication());

        viewModel.getAllCurrencies().observe(this, response -> {
            if (response.size() > 0) {
                currencyList = new ArrayList<>(response);
                System.err.println("LOAD ALREADY SELECTED ONES");
                for (int i = 0; i < currencies.length; i++) {
                    int finalI = i;
                    listCurrencies.get(i).setSelected(currencyList.stream().anyMatch(currency -> currency.getCurrencyName().equals(currencies[finalI])));
                }
                currencyCheckboxAdapter.notifyDataSetChanged();
            }
        });
        for (String currency : currencies) {
            Currency state = new Currency();
            state.setCurrencyName(currency);
            listCurrencies.add(state);
        }

        currencyCheckboxAdapter = new CurrencyCheckboxAdapter(CurrencySelectorActivity.this, 0,
                listCurrencies);
        spinner.setAdapter(currencyCheckboxAdapter);

    }

    public void saveSelectedCurrencies(View view) {
        System.err.println("saved:");
        ArrayList<String> selected = new ArrayList<>();
        for (int i = 0; i < currencies.length; ++i) {
            if (currencyCheckboxAdapter.getItem(i).isSelected()) {
                System.err.println(currencyCheckboxAdapter.getItem(i).getCurrencyName());
                selected.add(currencyCheckboxAdapter.getItem(i).getCurrencyName());
            }
        }
        Intent intent = new Intent(this, ShowCurrenciesActivity.class);
        if (!selected.isEmpty()) {
            viewModel.deleteAll();
            for (String s : selected) {
                viewModel.insert(new Currency(s));
            }
            startActivity(intent);
        } else {
            Toast.makeText(CurrencySelectorActivity.this, "Választanod kell legalább egy valutát ahhoz hogy továbbhaladhass!", Toast.LENGTH_LONG).show();

        }

    }


}
