package com.example.cryptobag;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cryptobag.entities.Coin;
import com.example.cryptobag.entities.CoinLoreResponse;
import com.example.cryptobag.entities.CoinService;
import com.google.gson.Gson;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CoinListAdapter.OnNoteListener {

    String Symbol = null;

    public static int EXTRA_MESSAGE;
    private RecyclerView mRecyclerView;
    private CoinListAdapter mCoinListAdapter;
    private static List<Coin> coinList;
    private static final String TAG = "MainActivity";


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

        call.enqueue(new Callback<CoinLoreResponse>() {
            @Override
            public void onResponse(Call<CoinLoreResponse> call, Response<CoinLoreResponse> response) {
                CoinLoreResponse coinResponse = response.body();
                List<Coin> myCoins = coinResponse.getData();
                setCoins(myCoins);
            }

            @Override
            public void onFailure(Call<CoinLoreResponse> call, Throwable t) {
                String failMsg = "Could not connect to CoinLore API";
            }
        });

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
    public void setCoins(List<Coin> newCoins) {
        coinList = newCoins;
        mCoinListAdapter = new CoinListAdapter(this, newCoins, this);
        mRecyclerView.setAdapter(mCoinListAdapter);

    }

}