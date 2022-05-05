package com.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
    public Message(String message) {
        this.message = message;
    }

    private String message;
}
