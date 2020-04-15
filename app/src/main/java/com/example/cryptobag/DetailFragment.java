package com.example.cryptobag;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cryptobag.entities.Coin;


public class DetailFragment extends Fragment {
    private TextView Name;
    private TextView Symbol;
    private TextView ValueUSD;
    private TextView Change1h;
    private TextView Change24h;
    private TextView Change7d;
    private TextView Marketcap;
    private TextView Volume24h;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int message = getArguments().getInt(MainActivity.MESSAGE);

        TextView name = getView().findViewById(R.id.bName);
        TextView symbol = getView().findViewById(R.id.bSymbol);
        TextView value = getView().findViewById(R.id.bValue);
        TextView change1h = getView().findViewById(R.id.bChange1h);
        TextView change24h = getView().findViewById(R.id.bChange24h);
        TextView change7d = getView().findViewById(R.id.bChange7d);
        TextView marketcap = getView().findViewById(R.id.bMarketcap);
        TextView volume24h = getView().findViewById(R.id.bVolume24h);

        Coin coin = MainActivity.coinSearch(message);

        name.setText(coin.getName());
        symbol.setText(coin.getSymbol());
        value.setText(coin.getPriceUsd());
        change1h.setText(coin.getPercentChange1h());
        change24h.setText(coin.getPercentChange24h());
        change7d.setText(coin.getPercentChange7d());
        marketcap.setText(coin.getMarketCapUsd());
        volume24h.setText(Double.toString(coin.getVolume24()));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_detail, container, false);

    }




}
