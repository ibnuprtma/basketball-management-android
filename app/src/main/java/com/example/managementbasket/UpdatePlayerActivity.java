package com.example.managementbasket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.managementbasket.Config.InitService;
import com.example.managementbasket.Model.PostPutDelPlayer;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePlayerActivity extends AppCompatActivity {

    private EditText et_name, et_position, et_jersey_number, et_address;
    private Button bt_simpan;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_player);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Update Player");

        et_name = findViewById(R.id.et_name);
        et_position = findViewById(R.id.et_position);
        et_jersey_number = findViewById(R.id.et_number);
        et_address = findViewById(R.id.et_address);
        bt_simpan = findViewById(R.id.btn_simpan);

        Intent i = getIntent();
        Bundle b = i.getBundleExtra("player");

        et_name.setText(b.getString("name"));
        et_position.setText(b.getString("positions"));
        et_jersey_number.setText(b.getString("number_jersey"));
        et_address.setText(b.getString("address"));
        id = b.getInt("id");

        bt_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString();
                String position = et_position.getText().toString();
                String number = et_jersey_number.getText().toString();
                String address = et_address.getText().toString();

                if(name.isEmpty() || position.isEmpty() || number.isEmpty() || address.isEmpty()){
                    Toast.makeText(UpdatePlayerActivity.this, "Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }else if(number.length() > 3){
                    Toast.makeText(UpdatePlayerActivity.this, "panjang tahun tidak boleh lebih dari 3 digit", Toast.LENGTH_SHORT).show();
                }else{
                    Call<PostPutDelPlayer> call = InitService.getInstance().putPlayer(id,name,position,number,address);
                    call.enqueue(new Callback<PostPutDelPlayer>() {
                        @Override
                        public void onResponse(Call<PostPutDelPlayer> call, Response<PostPutDelPlayer> response) {
                            Toast.makeText(UpdatePlayerActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            setResult(2);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<PostPutDelPlayer> call, Throwable t) {
                            Log.d("Error", "ubahPlayer: " + t.getMessage());
                        }
                    });
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
