package com.example.currencyapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currencyapp.Currency;
import com.example.currencyapp.CurrencyListAdapter;
import com.example.currencyapp.CurrencyService;
import com.example.currencyapp.CurrencyViewModel;
import com.example.currencyapp.R;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowCurrenciesActivity extends AppCompatActivity {

    private CurrencyViewModel viewModel;
    private CurrencyService currencyService;
    private List<Currency> currencyList = new ArrayList();

    private RecyclerView mRecyclerView;
    private CurrencyListAdapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_currencies);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(
                this, 1));

        mAdapter = new CurrencyListAdapter(this, new ArrayList<>(currencyList));
        mRecyclerView.setAdapter(mAdapter);

        try {
            currencyService = new CurrencyService();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        viewModel = new CurrencyViewModel(getApplication());
        viewModel.getAllCurrencies().observe(this, response -> {
            if (response.size() > 0) {
                currencyList = response;
                callApi();
                System.err.println("CHANGED");
                mAdapter = new CurrencyListAdapter(this, new ArrayList<>(currencyList));
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        });

    }

    public void callApi() {
        System.err.println("API CALL");
        try {
            System.err.println(currencyList);
            currencyService.start(currencyList);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public void updateCurrencies(View view) {
        Intent intent = new Intent(this, CurrencySelectorActivity.class);
        startActivity(intent);
    }
}
