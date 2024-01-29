package com.example.mysignupapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView textView = findViewById(R.id.skipToHome);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Button button1 = findViewById(R.id.reg_btn);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Registration.class);
                MainActivity.this.startActivity(intent);
            }
        });


        Button button2 = findViewById(R.id.guest_btn);

        button2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MyLoginApp.class);
            startActivity(intent);

        });
    }



}