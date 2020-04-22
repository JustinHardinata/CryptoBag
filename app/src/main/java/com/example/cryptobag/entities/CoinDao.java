package com.example.cryptobag.entities;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CoinDao {

    @Insert()
    void insertCoins(Coin... coins);

    @Query("DELETE FROM coin")
    void deleteAllCoins();

    @Query("SELECT * FROM coin")
    List<Coin> getCoins();

    @Query("SELECT * FROM coin WHERE id = :id")
    Coin getCoinById(String id);

}
