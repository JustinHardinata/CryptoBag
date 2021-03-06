package com.example.cryptobag;

import java.util.ArrayList;
import java.util.LinkedList;

public class OldCoin {
    private String name;
    private String symbol;
    private double value;
    private double change1h;
    private double change24h;
    private double change7d;
    private double marketcap;
    private double volume;

    public OldCoin() {
    }

    public OldCoin(String name, String symbol, double value, double change1h, double change24h, double change7d, double marketcap, double volume) {
        this.name = name;
        this.symbol = symbol;
        this.value = value;
        this.change1h = change1h;
        this.change24h = change24h;
        this.change7d = change7d;
        this.marketcap = marketcap;
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getChange1h() {
        return change1h;
    }

    public void setChange1h(double change1h) {
        this.change1h = change1h;
    }

    public double getChange24h() {
        return change24h;
    }

    public void setChange24h(double change24h) {
        this.change24h = change24h;
    }

    public double getChange7d() {
        return change7d;
    }

    public void setChange7d(double change7d) {
        this.change7d = change7d;
    }

    public double getMarketcap() {
        return marketcap;
    }

    public void setMarketcap(double marketcap) {
        this.marketcap = marketcap;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public static ArrayList<OldCoin> getCoins() {
        ArrayList<OldCoin> coins = new ArrayList<>();
        coins.add(new OldCoin("Bitcoin", "BTC", 8662.99, -5.30, 0.06, 6.25, 157394075212.34, 26248451879.217194));
        coins.add(new OldCoin("Ethereum", "ETH", 165.69, -5.94, 0.00, 14.58, 18116094926.74, 11453091518.956093));
        coins.add(new OldCoin("XRP", "XRP", 0.232488, -6.10, 0.36, 8.03, 9975961452.76, 1857161424.9047709));
        coins.add(new OldCoin("Bitcoin Cash", "BCH", 332.50, -5.25, 0.31, 25.16, 6061849292.55, 4034762667.1342015));
        coins.add(new OldCoin("Bitcoin SV", "BCHSV", 274.49, 6.09, 0.98, 71.68, 5003375789.67, 2920688747.7987976));
        coins.add(new OldCoin("Tether", "USDT", 1.00, 0.09, -0.04, 0.29, 4051244046.05,32969047733.32528));
        coins.add(new OldCoin("Litecoin", "LTC", 57.11, -6.42, 0.35, 12.84, 3665038765.74, 3433599488.5887113));
        coins.add(new OldCoin("EOS", "EOS", 3.58, -7.21, -0.11, 12.92, 3324669063.56, 3353780327.053705));
        coins.add(new OldCoin("Binance Coin", "BNB", 17.20, -5.04, 0.24, 12.51, 2675482775.95, 233309183.3948947));
        coins.add(new OldCoin("Stellar", "XLM", 0.061529, -2.09, 1.78, 25.85, 1232939271.42, 502557303.372596));
        return coins;
    }

    public static OldCoin searchCoin (String symbol){

        ArrayList<OldCoin> coinArrayList = getCoins();

        OldCoin myCoin = coinArrayList.get(0);

        if(symbol.equals("BTC")){
            myCoin = coinArrayList.get(0);
        }

             if (symbol.equals("ETH")){
            myCoin = coinArrayList.get(1);

            }
            if(symbol.equals("XRP")){
                myCoin = coinArrayList.get(2);

            }
            if(symbol.equals("BCH")){
                myCoin = coinArrayList.get(3);
            }
            if(symbol.equals("BCHSV")){
                myCoin = coinArrayList.get(4);
            }
            if(symbol.equals("USDT")){
                myCoin = coinArrayList.get(5);
            }
            if(symbol.equals("LTC")){
                myCoin = coinArrayList.get(6);
            }
            if(symbol.equals("EOS")){
                myCoin = coinArrayList.get(7);
            }
            if(symbol.equals("BNB")){
                myCoin = coinArrayList.get(8);
            }
            if(symbol.equals("XLM")){
                myCoin = coinArrayList.get(9);
            }

        return myCoin;

    }
    public static LinkedList<OldCoin> CreateCoins (LinkedList<OldCoin> coins) {

        ArrayList<OldCoin> myCoins = getCoins();

        coins.addAll(myCoins);

        return coins;
    }
}