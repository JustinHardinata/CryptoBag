package com.example.cryptobag;

import androidx.fragment.app.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cryptobag.entities.Coin;


public class DetailFragment extends Fragment {
    private TextView bName;
    private TextView bSymbol;
    private TextView bValueUSD;
    private TextView bChange1h;
    private TextView bChange24h;
    private TextView bChange7d;
    private TextView bMarketcap;
    private TextView bVolume24h;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int message = getArguments().getInt(MainActivity.MESSAGE);

        bName = getView().findViewById(R.id.bName);
        bSymbol = getView().findViewById(R.id.bSymbol);
        bValueUSD = getView().findViewById(R.id.bValue);
        bChange1h = getView().findViewById(R.id.bChange1h);
        bChange24h = getView().findViewById(R.id.bChange24h);
        bChange7d = getView().findViewById(R.id.bChange7d);
        bMarketcap = getView().findViewById(R.id.bMarketcap);
        bVolume24h = getView().findViewById(R.id.bVolume24h);

        Coin coin = MainActivity.coinSearch(message);

        new CoinQueryTask().execute();


    }
    public class CoinQueryTask extends AsyncTask<Void, Void, Coin> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        @Override
        protected Coin doInBackground(Void... voids) {

            String id = getArguments().getString(MainActivity.MESSAGE);

            Coin targetCoin = MainActivity.coinDb.coinDao().getCoinById(id);
            return targetCoin;
        }

        @Override
        protected void onPostExecute(Coin coin) {
            super.onPostExecute(coin);
            if (coin != null) {

                bName.setText(coin.getName());
                bSymbol.setText(coin.getSymbol());
                bValueUSD.setText(coin.getPriceUsd());
                bChange1h.setText(coin.getPercentChange1h());
                bChange24h.setText(coin.getPercentChange24h());
                bChange7d.setText(coin.getPercentChange7d());
                bMarketcap.setText(coin.getMarketCapUsd());
                bVolume24h.setText(Double.toString(coin.getVolume24()));

            }  else {
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_detail, container, false);

    }




}
