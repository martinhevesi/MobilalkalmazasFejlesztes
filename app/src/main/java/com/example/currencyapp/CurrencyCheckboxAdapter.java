package com.example.currencyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CurrencyCheckboxAdapter extends ArrayAdapter<Currency> {
    private Context mContext;
    private ArrayList<Currency> listState;
    private CurrencyCheckboxAdapter currencyCheckboxAdapter;
    private boolean isFromView = false;

    public CurrencyCheckboxAdapter(Context context, int resource, List<Currency> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.listState = (ArrayList<Currency>) objects;
        this.currencyCheckboxAdapter = this;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.spinner_item, null);
            holder = new ViewHolder();
            holder.currencyName = (TextView) convertView.findViewById(R.id.text);
            holder.selectCurrencyCheckBox = (CheckBox) convertView.findViewById(R.id.checkbox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.currencyName.setText(listState.get(position).getCurrencyName());

        isFromView = true;
        holder.selectCurrencyCheckBox.setChecked(listState.get(position).isSelected());
        isFromView = false;

        if ((position == 0)) {
            holder.selectCurrencyCheckBox.setVisibility(View.INVISIBLE);
        } else {
            holder.selectCurrencyCheckBox.setVisibility(View.VISIBLE);
        }
        holder.selectCurrencyCheckBox.setTag(position);
        holder.selectCurrencyCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int getPosition = (Integer) buttonView.getTag();

                if (!isFromView) {
                    listState.get(position).setSelected(isChecked);
                }
            }
        });
        return convertView;
    }

    private static class ViewHolder {
        private TextView currencyName;
        private CheckBox selectCurrencyCheckBox;
    }
}
