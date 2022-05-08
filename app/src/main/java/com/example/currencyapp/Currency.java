package com.example.currencyapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "currency_table")
public class Currency {

    @PrimaryKey
    @NonNull
    @ColumnInfo
    private String currencyName;
    private boolean selected;
    private Double currencyValue;


    public Currency(@NonNull String currencyName) {
        this.currencyName = currencyName;
    }

    public Currency(@NonNull String currencyName, Double currencyValue) {
        this.currencyName = currencyName;
        this.currencyValue = currencyValue;
    }

    public Currency(){
        this.setCurrencyName("");
        this.setSelected(false);
        this.setCurrencyValue(0.0);
    }

    public String getCurrencyName() {
        return this.currencyName;
    }

    public void setCurrencyName(@NonNull String currencyName) {
        this.currencyName = currencyName;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Double getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(Double currencyValue) {
        this.currencyValue = currencyValue;
    }

    @Override
    public String toString() {
        return "Currency(" +
                currencyName + " : " +
                currencyValue +
                ")";
    }
}
