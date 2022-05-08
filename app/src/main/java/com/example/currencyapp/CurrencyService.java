package com.example.currencyapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencyService {

    final static String SECRET = "11e3c402a0174b73ab07279a60c74a50";
    JSONObject jsonObject;
    List<Currency> filtered;

    public CurrencyService() throws IOException, InterruptedException {
        this.jsonObject = new JSONObject();
        this.filtered = new ArrayList<>();
        sendHttpGetRequest();
    }

    public void start(List<Currency> currencyList) throws IOException, JSONException {
        filterCurrenciesWithPrice(currencyList);
    }

    public void filterCurrenciesWithPrice(List<Currency> currencyList) throws JSONException {
        filtered = currencyList;
        for (Currency c : filtered) {
            c.setCurrencyValue(Double.parseDouble(this.jsonObject.get(c.getCurrencyName()).toString()));
        }
        System.err.println(filtered);
    }

    public void sendHttpGetRequest() throws IOException, InterruptedException {

        Thread thread = new Thread(() -> {
            try {
                URL url = new URL("https://openexchangerates.org/api/latest.json?app_id=" + SECRET);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                try {

                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    String result = new BufferedReader(new InputStreamReader(in))
                            .lines().collect(Collectors.joining("\n"));
//                        System.err.println(result);
                    jsonObject = (JSONObject) new JSONObject(result).get("rates");
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        thread.start();
        thread.join();

    }
}
