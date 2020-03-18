package com.example.cryptobag;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class CoinListAdapter extends RecyclerView.Adapter<CoinListAdapter.CoinViewHolder> {

    private final LinkedList<Coin> mWordList;
    private LayoutInflater mInflater;
    private final String TAG = "CoinListAdapter";
    public final String EXTRA_MESSAGE = "com.example.cryptobag.MESSAGE";
    private OnNoteListener mOnNoteListener;

    public class CoinViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView coinName;
        public final TextView coinPrice;
        public final TextView coinChange1h;
        public final TextView coinSymbol;
        final CoinListAdapter mCoinListAdapter;
        OnNoteListener onNoteListener;



        public CoinViewHolder(View itemView,CoinListAdapter adapter, OnNoteListener onNoteListener) {
            super(itemView);
            coinName = itemView.findViewById(R.id.word);
            coinPrice = itemView.findViewById(R.id.Price);
            coinChange1h = itemView.findViewById(R.id.Percentage);
            coinSymbol = itemView.findViewById(R.id.Symbol);
            this.mCoinListAdapter = adapter;
            mOnNoteListener = onNoteListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            mOnNoteListener.onNoteClick(getAdapterPosition());
        }

    }
    public interface OnNoteListener{
        void onNoteClick(int position);
    }

    public CoinListAdapter(Context context, LinkedList<Coin> wordList,  OnNoteListener onNoteListener) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
        this.mOnNoteListener = onNoteListener;
    }
    @NonNull
    @Override
    public CoinListAdapter.CoinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = mInflater.inflate(R.layout.wordlist_item, parent, false);
        return new CoinViewHolder(itemView,this, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CoinListAdapter.CoinViewHolder holder, int position) {

        Coin mCurrent = mWordList.get(position);
        holder.coinName.setText(mCurrent.getName());
        holder.coinChange1h.setText((double) mCurrent.getChange1h() + "%");
        holder.coinPrice.setText( "$" + (double) mCurrent.getValue());
        holder.coinSymbol.setText(mCurrent.getSymbol());
        holder.coinSymbol.setVisibility(View.INVISIBLE);

    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }


}