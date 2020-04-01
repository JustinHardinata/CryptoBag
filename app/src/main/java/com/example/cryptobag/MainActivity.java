package com.example.cryptobag;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptobag.entities.Coin;
import com.example.cryptobag.entities.CoinLoreResponse;
import com.google.gson.Gson;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CoinListAdapter.OnNoteListener {

    String Symbol = null;

    public static int EXTRA_MESSAGE;
    private RecyclerView mRecyclerView;
    private CoinListAdapter mCoinListAdapter;
    private static List<Coin> coinList;
    private static final String TAG = "MainActivity";

    CoinLoreResponse coinLoreList = new Gson().fromJson(CoinLoreResponse.queryResult, CoinLoreResponse.class);
    List<Coin> myCoins = coinLoreList.getData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.coinList = myCoins;


        mRecyclerView = findViewById(R.id.recyclerView);

        mCoinListAdapter = new CoinListAdapter(this, myCoins, this);

        mRecyclerView.setAdapter(mCoinListAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onNoteClick(int position) {

        int symbol = position;
        launchActivity(symbol);

    }

    public void launchActivity (int symbol) {
        Intent intent = new Intent (this, DetailActivity.class);
        int message = symbol;
        intent.putExtra(String.valueOf(EXTRA_MESSAGE), symbol);
        startActivity(intent);

    }

    public static Coin coinSearch(int position){

        Coin targetCoin = coinList.get(position);

        return targetCoin;
    }


}
