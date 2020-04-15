package com.example.cryptobag;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cryptobag.entities.Coin;
import com.example.cryptobag.entities.CoinLoreResponse;
import com.example.cryptobag.entities.CoinService;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CoinListAdapter.OnNoteListener {

    String Symbol = null;

    public static final String MESSAGE = "ActivityMain";
    private RecyclerView mRecyclerView;
    private CoinListAdapter mCoinListAdapter;
    private static List<Coin> coinList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);

        mCoinListAdapter = new CoinListAdapter(this, coinList, this);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.coinlore.net/api/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        CoinService service = retrofit.create(CoinService.class);

        Call<CoinLoreResponse> call = service.get100Coins();

        mRecyclerView.setAdapter(mCoinListAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        new NetworkTask().execute();

    }

    public class NetworkTask extends AsyncTask<Void, Integer, CoinLoreResponse> {


        @Override
        protected CoinLoreResponse doInBackground(Void... voids) {

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("https://api.coinlore.net/api/")
                    .addConverterFactory(GsonConverterFactory.create());

            Retrofit retrofit = builder.build();

            CoinService service = retrofit.create(CoinService.class);
            Call<CoinLoreResponse> call = service.get100Coins();

            CoinLoreResponse coinList = null;

            try {
                Response<CoinLoreResponse> coinResponse = call.execute();
                coinList = coinResponse.body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return coinList;
        }

        @Override
        protected void onPostExecute (CoinLoreResponse coinLoreResponse) {
            super.onPostExecute(coinLoreResponse);
            if(coinLoreResponse != null) {
                setCoins(coinLoreResponse.getData());
            } else {

            }
        }
    }


    @Override
    public void onNoteClick(int position) {

        int symbol = position;
        launchActivity(symbol);

    }

    public void launchActivity (int symbol) {
        Intent intent = new Intent (this, DetailActivity.class);
        intent.putExtra(MESSAGE, symbol);
        startActivity(intent);

    }
    public void fragmentDetail(int position){

        DetailFragment fragment = new DetailFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragment == null) {
            transaction.add(R.id.detail_container, fragment);
        } else {
            transaction.replace(R.id.detail_container, fragment);
        }
        transaction.commit();

        Bundle intentBundle = new Bundle();
        intentBundle.putInt(MESSAGE, position);
        fragment.setArguments(intentBundle);

    }


    public static Coin coinSearch(int position){
        Coin targetCoin = coinList.get(position);
        return targetCoin;
    }
    public void setCoins(List<Coin> newCoins) {
        coinList = newCoins;
        mCoinListAdapter = new CoinListAdapter(this, newCoins, this);
        mRecyclerView.setAdapter(mCoinListAdapter);

    }

}