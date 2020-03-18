package com.example.cryptobag;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements CoinListAdapter.OnNoteListener {

    private boolean mIsDualPane = false;
    String Symbol = null;

    public static final String EXTRA_MESSAGE = "com.example.cryptobag.MESSAGE";
    private RecyclerView mRecyclerView;
    private CoinListAdapter mCoinListAdapter;
    private final LinkedList<Coin> mWordList = new LinkedList<>();
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinkedList<Coin> mycoin = Coin.CreateCoins(mWordList);

        mRecyclerView = findViewById(R.id.recyclerview);

        mCoinListAdapter = new CoinListAdapter(this, mWordList, this);

        mRecyclerView.setAdapter(mCoinListAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        View detail_scrollview = findViewById(R.id.fragment_scrollview);

        if (detail_scrollview != null && detail_scrollview.getVisibility() == View.VISIBLE) {
            mIsDualPane = true;
        }


    }

    @Override
    public void onNoteClick(int position) {

        String symbol = getSymbol(position);
        if (mIsDualPane == false) {
            launchActivity(symbol);
        }
        else {
            fragmentActivity(symbol);
        }

    }

    public void launchActivity (String symbol) {
        Intent intent = new Intent (this, DetailActivity.class);
        String message = symbol;
        intent.putExtra(EXTRA_MESSAGE, symbol);
        startActivity(intent);

    }

    public void fragmentActivity (String symbol) {

        DetailFragment fragment = new DetailFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (fragment == null) {
            transaction.add(R.id.fragment_scrollview, fragment);
        } else {
            transaction.replace(R.id.fragment_scrollview,fragment);
        }

        transaction.commit();
        Bundle intentBundle = new Bundle();
        intentBundle.putString(EXTRA_MESSAGE, symbol);
        fragment.setArguments(intentBundle);

    }

    public String getSymbol (int position) {
        String symbol = "";
        if (position == 0) {
            symbol = "BTC";
        }
        else if (position == 1) {
            symbol = "ETH";
        }
        else if (position == 2) {
            symbol = "XRP";
        }
        else if (position == 3) {
            symbol = "BCH";
        }
        else if (position == 4) {
            symbol = "BCHSV";
        }
        else if (position == 5) {
            symbol = "USDT";
        }
        else if (position == 6) {
            symbol = "LTC";
        }
        else if (position == 7) {
            symbol = "EOS";
        }
        else if (position == 8) {
            symbol = "BNB";
        }
        else if (position == 9) {
            symbol = "XLM";
        }
        return symbol;
    }


}
