package com.example.managementbasket.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPlayer {
    @SerializedName("status")
    private String status;
    @SerializedName("rows")
    private int rows;
    @SerializedName("player")
    private List<Player> list_player;

    public GetPlayer() {
    }

    public GetPlayer(String status, int rows, List<Player> list_player) {
        this.status = status;
        this.rows = rows;
        this.list_player = list_player;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public List<Player> getList_player() {
        return list_player;
    }

    public void setList_player(List<Player> list_player) {
        this.list_player = list_player;
    }
}
