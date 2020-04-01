package com.example.cryptobag;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cryptobag.entities.Coin;
import static com.example.cryptobag.MainActivity.EXTRA_MESSAGE;

public class DetailActivity extends AppCompatActivity {
    private TextView Name;
    private TextView Symbol;
    private TextView ValueUSD;
    private TextView Change1h;
    private TextView Change24h;
    private TextView Change7d;
    private TextView Marketcap;
    private TextView Volume24h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        int message = intent.getIntExtra(String.valueOf(EXTRA_MESSAGE),0);

        Log.d("detail activity", "message = " + message);

        setContentView(R.layout.activity_detail);
        TextView name = findViewById(R.id.bName);
        TextView symbol = findViewById(R.id.bSymbol);
        TextView value = findViewById(R.id.bValue);
        TextView change1h = findViewById(R.id.bChange1h);
        TextView change24h = findViewById(R.id.bChange24h);
        TextView change7d = findViewById(R.id.bChange7d);
        TextView marketcap = findViewById(R.id.bMarketcap);
        TextView volume24h = findViewById(R.id.bVolume24h);

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


}