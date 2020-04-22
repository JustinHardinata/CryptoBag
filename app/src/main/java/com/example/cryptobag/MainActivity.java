package com.example.cryptobag;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.cryptobag.entities.Coin;
import com.example.cryptobag.entities.CoinDatabase;
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
    public static CoinDatabase coinDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);

        mCoinListAdapter = new CoinListAdapter(this, coinList, this);

        mRecyclerView.setAdapter(mCoinListAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        coinDb = Room.databaseBuilder(getApplicationContext(), CoinDatabase.class, "coinDb")
                .build();

        new NetworkTask().execute();

    }

    public class NetworkTask extends AsyncTask<Void, Integer, CoinLoreResponse> {


        @Override
        protected CoinLoreResponse doInBackground(Void... voids) {
            coinDb.coinDao().deleteAllCoins();

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
            Coin[] coinArray = coinList.getData().toArray(new Coin[coinList.getData().size()]);

            coinDb.coinDao().insertCoins(coinArray);

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
    public class UpdateAdapterTask extends AsyncTask<Void, Void, List<Coin>>{
        @Override
        protected List<Coin> doInBackground(Void... voids) {
            List<Coin> allCoins = coinDb.coinDao().getCoins();

            coinList = allCoins;

            return allCoins;
        }

        @Override
        protected void onPostExecute(List<Coin> coins) {
            super.onPostExecute(coins);
            if (coins != null) {
                setCoins(coins);
            } else {
            }
        }
    }


    @Override
    public void onNoteClick(int position) {
        String id = coinList.get(position).getId();
        launchActivity(id);

    }

    public void launchActivity (String id) {
        Intent intent = new Intent (this, DetailActivity.class);
        intent.putExtra(MESSAGE, id);
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