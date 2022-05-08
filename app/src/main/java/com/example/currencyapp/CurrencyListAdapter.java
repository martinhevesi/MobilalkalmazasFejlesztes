package com.example.currencyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;


public class CurrencyListAdapter extends RecyclerView.Adapter<CurrencyListAdapter.ViewHolder> {

    private ArrayList<Currency> currencies;
    private Context mContext;
    private int lastPosition = -1;

    public CurrencyListAdapter(Context context, ArrayList<Currency> itemsData) {
        this.currencies = itemsData;
        this.mContext = context;
    }


    @Override
    public CurrencyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CurrencyListAdapter.ViewHolder holder, int position) {
        Currency currentItem = currencies.get(position);

        holder.currencyName.setText(currentItem.getCurrencyName());
        holder.currencyValue.setText(String.valueOf(currentItem.getCurrencyValue()));


        if (holder.getAdapterPosition() > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_row);
            holder.itemView.startAnimation(animation);
            lastPosition = holder.getAdapterPosition();
        }
    }

    @Override
    public int getItemCount() {
        return currencies.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView currencyName;
        private TextView currencyValue;

        ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            currencyName = itemView.findViewById(R.id.currencyName);
            currencyValue = itemView.findViewById(R.id.currencyValue);
        }

    }
}
