package com.vroulos.mynutricion.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MessagesResponse {

    @SerializedName("messages")
    private List<Message> messages;

    public MessagesResponse(List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
