package com.example.managementbasket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView address;
    Button btnPlayers, btnContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlayers = findViewById(R.id.btnPlayers);
        btnContent = findViewById(R.id.btnContent);

        btnPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,PlayersActivity.class);
                startActivityForResult(i,1);
            }
        });


        btnContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent (MainActivity.this,ContentActivity.class);
                startActivity(b);
            }
        });
    }
}
