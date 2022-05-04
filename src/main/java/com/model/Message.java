package com.model;

import lombok.Data;

@Data
public class Message {
    public Message(String message) {
        this.message = message;
    }

    private String message;
}
