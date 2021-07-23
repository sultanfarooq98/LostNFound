package com.example.lostnfound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void post(View view) {
        Intent intent = new Intent(this, Post.class);
        startActivity(intent);
    }

    public void search(View view) {
    }

    public void show(View view) {
        Intent intent  = new Intent(getApplicationContext(), AllItems.class);
        startActivity(intent);
    }
}