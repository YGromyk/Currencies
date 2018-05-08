package com.example.fepeprog.currencies;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fepeprog.currencies.APIs.Currency;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterCurrency extends RecyclerView.Adapter<AdapterCurrency.ViewHolder> {
    private ArrayList<Currency> dataCurrency;
    private int itemLayout;

    public AdapterCurrency(ArrayList<Currency> data) {
        dataCurrency = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView valuteLogo;
        public TextView baseValute;
        public TextView secondValute;
        public TextView buyPrice;

        public TextView sellPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            valuteLogo = (ImageView) itemView.findViewById(R.id.valute_logo);
            baseValute = (TextView) itemView.findViewById(R.id.base_valute);
            secondValute = (TextView) itemView.findViewById(R.id.second_valute);
            buyPrice = (TextView) itemView.findViewById(R.id.buy_price);
            sellPrice = (TextView) itemView.findViewById(R.id.sell_price);
        }


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View view = inflater.inflate(R.layout.valute_currency_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Currency currency = dataCurrency.get(position);
        String flag = "";
        switch (currency.getCcy()) {
            case "USD":
                flag = "us";
                break;
            case "EUR":
                flag = "eu";
                break;
            case "RUR":
                flag = "ru";
                break;
            case "BTC":
                flag = "https://en.bitcoin.it/w/images/en/6/69/Btc-sans.png";
                break;
        }
        if (currency.getCcy().equals("BTC"))
            Picasso.get().load(flag).into(holder.valuteLogo);
        else
            Picasso.get().load("http://www.countryflags.io/" + flag + "/flat/64.png").into(holder.valuteLogo);

        holder.baseValute.setText(currency.getCcy());
        holder.secondValute.setText(currency.getBaseCcy());
        holder.buyPrice.setText(currency.getBuy());
        holder.sellPrice.setText((currency.getSale()));
    }


    @Override
    public int getItemCount() {
        return dataCurrency.size();
    }

    public void add(int position, Currency item) {
        dataCurrency.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        dataCurrency.remove(position);
        notifyItemRemoved(position);
    }
}