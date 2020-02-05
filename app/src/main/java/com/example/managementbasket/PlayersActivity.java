package com.example.managementbasket;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.managementbasket.Adapter.PlayerAdapter;
import com.example.managementbasket.Config.InitService;
import com.example.managementbasket.Config.UpdateDeleteListener;
import com.example.managementbasket.Model.GetPlayer;
import com.example.managementbasket.Model.Player;
import com.example.managementbasket.Model.PostPutDelPlayer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayersActivity extends AppCompatActivity implements UpdateDeleteListener {

    private List<Player> list_player;
    private RecyclerView rv_player;
    private PlayerAdapter fAdapter;
    private FloatingActionButton fab_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        rv_player = findViewById(R.id.rv_players);
        fab_add = findViewById(R.id.fab_add);

        getFilm();


        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PlayersActivity.this,CreatePlayerActivity.class);
                startActivityForResult(i,1);
            }
        });

    }

    private void setRecycler(List<Player> list){
        fAdapter = new PlayerAdapter(list,this,this);
        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(this);
        rv_player.setLayoutManager(mLayout);
        rv_player.setAdapter(fAdapter);
        fAdapter.notifyDataSetChanged();
    }

    private void getFilm() {
        try{
            Call<GetPlayer> call = InitService.getInstance().getPlayer();

            call.enqueue(new Callback<GetPlayer>() {
                @Override
                public void onResponse(Call<GetPlayer> call, Response<GetPlayer> response) {
                    if(response.isSuccessful()){
                        list_player = response.body().getList_player();
                        setRecycler(list_player);
                    }
                }

                @Override
                public void onFailure(Call<GetPlayer> call, Throwable t) {
                    Log.d("Error", "getPlayer: " + t.getMessage());
                }
            });
        }catch (Exception e){
            Log.d("Error", "getPlayer: " + e.getMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == 1){
            finish();
            startActivity(getIntent());
        }else
        if(requestCode == 2 && resultCode == 2){
            finish();
            startActivity(getIntent());
        }
    }

    @Override
    public void onUpdate(int position) {
        int id = list_player.get(position).getId();
        String name = list_player.get(position).getName();
        String positions = list_player.get(position).getPosition();
        String number_jersey = list_player.get(position).getNumber_jersey();
        String address = list_player.get(position).getAddress();

        Bundle b = new Bundle();
        b.putInt("id", id);
        b.putString("name", name);
        b.putString("positions", positions);
        b.putString("number_jersey", number_jersey);
        b.putString("address", address);
        Intent i = new Intent(PlayersActivity.this, UpdatePlayerActivity.class);
        i.putExtra("player", b);
        startActivityForResult(i, 2);
    }

    @Override
    public void onDelete(final int position) {
        String name = list_player.get(position).getName();
        showDialogHapus(position,name);
    }

    private void showDialogHapus(final int position, String name) {
        AlertDialog.Builder builder = new AlertDialog.Builder(PlayersActivity.this);
        builder.setMessage("Yakin ingin menghapus player "+ name + " ?");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Ya",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Call<PostPutDelPlayer> call = InitService.getInstance().delPlayer(list_player.get(position).getId());
                        call.enqueue(new Callback<PostPutDelPlayer>() {
                            @Override
                            public void onResponse(Call<PostPutDelPlayer> call, Response<PostPutDelPlayer> response) {
                                Toast.makeText(PlayersActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                list_player.remove(position);
                                fAdapter.notifyItemRemoved(position);
                                fAdapter.notifyItemRangeChanged(position,list_player.size());
                            }

                            @Override
                            public void onFailure(Call<PostPutDelPlayer> call, Throwable t) {
                                Log.d("Error", "hapusPlayer: " + t.getMessage());
                            }
                        });
                    }
                });

        builder.setNegativeButton(
                "Tidak",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

}
