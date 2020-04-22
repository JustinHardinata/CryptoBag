package com.example.cryptobag.entities;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = Coin.class, version = 1)
public abstract class CoinDatabase extends RoomDatabase {
    public abstract CoinDao coinDao();

}
