package com.example.cryptobag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView name;
    private TextView symbol;
    private TextView value;
    private TextView change1h;
    private TextView change24h;
    private TextView change7d;
    private TextView marketcap;
    private TextView volume24h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView name = findViewById(R.id.bName);
        TextView symbol = findViewById(R.id.bSymbol);
        TextView value = findViewById(R.id.bValue);
        TextView change1h = findViewById(R.id.bChange1h);
        TextView change24h = findViewById(R.id.bChange24h);
        TextView change7d = findViewById(R.id.bChange7d);
        TextView marketcap = findViewById(R.id.bMarketcap);
        TextView volume24h = findViewById(R.id.bVolume24h);

        Intent intent = getIntent();
        String coinSymbol = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        final Coin coin = Coin.searchCoin(coinSymbol);

        name.setText(coin.getName());
        symbol.setText(coin.getSymbol());
        value.setText(Double.toString(coin.getValue()));
        change1h.setText(Double.toString(coin.getChange1h()));
        change24h.setText(Double.toString(coin.getChange24h()));
        change7d.setText(Double.toString(coin.getChange7d()));
        marketcap.setText(Double.toString(coin.getMarketcap()));
        volume24h.setText(Double.toString(coin.getVolume()));


        int a = 10;

        String aString = Integer.toString(a);
















    }

}
