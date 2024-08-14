package com.sujal.springboot.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sujal.springboot.models.Message;

@RestController
public class MessageController {
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	
    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message getContent(@Payload Message message) {
		//System.out.print(message);
        return message;
    }
    
	
    @MessageMapping("/private-message")
    public Message receivePrivateMessage(@Payload Message message) {
		//System.out.print(message);
    	simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message);
    	return message;
    }

}
