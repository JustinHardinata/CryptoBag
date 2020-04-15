package com.example.cryptobag;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

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
        String message = intent.getStringExtra(MainActivity.MESSAGE);

        Bundle intentBundle = new Bundle();
        intentBundle.putString(MainActivity.MESSAGE, message);
        fragment.setArguments(intentBundle);





    }


}
