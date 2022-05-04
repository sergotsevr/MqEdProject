package com.controller;

import com.model.Message;
import com.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class QueueController {

    @Autowired
    QueueService service;

    @GetMapping("/read")
    public Message readMessage(){
        return service.readMessage();
    }

    @PostMapping("/write")
    public void WriteMessage(Message message){
        service.writeMessage(message);
    }
}
