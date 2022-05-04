package com.service;

import com.model.Message;

public interface QueueService {

    Message readMessage();

    void writeMessage(Message message);
}
