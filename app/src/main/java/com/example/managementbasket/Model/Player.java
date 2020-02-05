package com.example.managementbasket.Model;

import com.google.gson.annotations.SerializedName;

public class Player {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("position")
    private String position;
    @SerializedName("number_jersey")
    private String number_jersey;
    @SerializedName("address")
    private String address;
    @SerializedName("images")
    private String images;

    public Player() {
    }

    public Player(int id, String name, String position, String number_jersey, String address, String images) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.number_jersey = number_jersey;
        this.address = address;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNumber_jersey() {
        return number_jersey;
    }

    public void setNumber_jersey(String number_jersey) {
        this.number_jersey = number_jersey;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
