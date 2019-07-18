package com.vroulos.mynutricion.models;

import com.google.gson.annotations.SerializedName;

public class UserRegisterResponse {
    @SerializedName("error")
    private boolean err;

    @SerializedName("message")
    private String msg;

    public UserRegisterResponse(boolean err, String msg) {
        this.err = err;
        this.msg = msg;
    }

    public boolean isErr() {
        return err;
    }

    public String getMsg() {
        return msg;
    }
}
