package com.ibnuprtma.managementbasket.Model;

import com.google.gson.annotations.SerializedName;

public class PostPutDelPlayer {
    @SerializedName("status")
    private String status;
    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;

    public PostPutDelPlayer() {
    }

    public PostPutDelPlayer(String status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
