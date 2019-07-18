package com.vroulos.mynutricion.models;

import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("message")
    private String message;

    @SerializedName("customer")
    private String customer;

    @SerializedName("sync")
    private int in_sync;

    public Message(String message, String customer, Integer in_sync) {
        this.message = message;
        this.customer = customer;
        this.in_sync = in_sync;
    }

    public String getCustomer() {
        return customer;
    }

    public String getMessage() {
        return message;
    }

    public int getIn_sync() {
        return in_sync;
    }
}
