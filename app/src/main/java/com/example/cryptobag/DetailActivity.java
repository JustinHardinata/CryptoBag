package com.example.cryptobag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.LinkedList;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        DetailFragment fragment = new DetailFragment();

        FragmentTransaction initTransaction = getSupportFragmentManager().beginTransaction();

        initTransaction.add(R.id.fragment_container, fragment);

        initTransaction.commit();

        Intent intent = getIntent();

        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        Bundle intentBundle = new Bundle();

        intentBundle.putString(MainActivity.EXTRA_MESSAGE, message);

        fragment.setArguments(intentBundle);

    }

}
