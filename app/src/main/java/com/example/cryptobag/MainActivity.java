package com.example.cryptobag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button launchDetailActivity = findViewById(R.id.launchActivityDetail);


    }
    public void sendMessage(View view){
        Intent intent = new Intent(this, DetailActivity.class);
        String message = "BTC";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}