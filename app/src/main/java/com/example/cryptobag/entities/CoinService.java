package com.example.cryptobag.entities;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CoinService {
    @GET("tickers/")
    Call<CoinLoreResponse> get100Coins();
}
