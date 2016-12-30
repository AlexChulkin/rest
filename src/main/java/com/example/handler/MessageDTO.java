package com.example.handler;


/**
 * Created by alexc_000 on 2016-12-30.
 */
class MessageDTO {
    private String message;
    private MessageType type;

    public MessageDTO() {
    }

    MessageDTO(MessageType type, String message) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public MessageType getType() {
        return type;
    }
}
