package com.example.cryptobag;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
    private TextView name;
    private TextView symbol;
    private TextView value;
    private TextView change1h;
    private TextView change24h;
    private TextView change7d;
    private TextView marketcap;
    private TextView volume24h;
    private final LinkedList<Coin> Coins = new LinkedList<>();



    public DetailFragment() {
        // Required empty public constructor
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String message = getArguments().getString(MainActivity.EXTRA_MESSAGE);

        TextView name = getView().findViewById(R.id.bName);
        TextView symbol = getView().findViewById(R.id.bSymbol);
        TextView value = getView().findViewById(R.id.bValue);
        TextView change1h = getView().findViewById(R.id.bChange1h);
        TextView change24h = getView().findViewById(R.id.bChange24h);
        TextView change7d = getView().findViewById(R.id.bChange7d);
        TextView marketcap = getView().findViewById(R.id.bMarketcap);
        TextView volume24h = getView().findViewById(R.id.bVolume24h);

        final Coin coin = Coin.searchCoin(message);

        name.setText(coin.getName());
        symbol.setText(coin.getSymbol());
        value.setText(Double.toString(coin.getValue()));
        change1h.setText(Double.toString(coin.getChange1h()));
        change24h.setText(Double.toString(coin.getChange24h()));
        change7d.setText(Double.toString(coin.getChange7d()));
        marketcap.setText(Double.toString(coin.getMarketcap()));
        volume24h.setText(Double.toString(coin.getVolume()));


    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }



}
