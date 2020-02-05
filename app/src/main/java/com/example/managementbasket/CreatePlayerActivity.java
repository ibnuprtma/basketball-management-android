package com.example.managementbasket;

import androidx.appcompat.app.AppCompatActivity;

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

public class CreatePlayerActivity extends AppCompatActivity {


    private EditText et_name, et_position, et_number_jersey, et_address;
    private Button bt_simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_player);

        //tombol back diatas
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Add Players");

        et_name = findViewById(R.id.et_name);
        et_position = findViewById(R.id.et_position);
        et_number_jersey = findViewById(R.id.et_number);
        et_address = findViewById(R.id.et_address);
        bt_simpan = findViewById(R.id.btn_simpan);

        bt_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString();
                String position = et_position.getText().toString();
                String number_jersey = et_number_jersey.getText().toString();
                String address = et_address.getText().toString();

                if(name.isEmpty() || position.isEmpty() || number_jersey.isEmpty() || address.isEmpty()){
                    Toast.makeText(CreatePlayerActivity.this, "Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }else if(number_jersey.length() > 3){
                    Toast.makeText(CreatePlayerActivity.this, "panjang tahun tidak boleh lebih dari 3 digit", Toast.LENGTH_SHORT).show();
                }else{
                    Call<PostPutDelPlayer> call = InitService.getInstance().postPlayer(name,position,number_jersey,address,"default.jpg");
                    call.enqueue(new Callback<PostPutDelPlayer>() {
                        @Override
                        public void onResponse(Call<PostPutDelPlayer> call, Response<PostPutDelPlayer> response) {
                            Toast.makeText(CreatePlayerActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            setResult(1);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<PostPutDelPlayer> call, Throwable t) {
                            Log.d("Error", "tambahPlayer: " + t.getMessage());
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
