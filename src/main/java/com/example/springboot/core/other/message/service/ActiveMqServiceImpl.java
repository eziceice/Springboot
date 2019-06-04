package com.example.springboot.core.other.message.service;

import com.example.springboot.core.relationaldb.mybatisredis.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ActiveMqServiceImpl implements ActiveMqService {

    @Autowired
    @Qualifier("jmsTopicTemplate")
    private JmsTemplate jmsTopicTemplate;

    @Autowired
    @Qualifier("jmsQueueTemplate")
    private JmsTemplate jmsQueueTemplate;

    private static final String messageDestination = "message-destination";

    private static final String userDestination = "user-destination";

    @Override
    public void sendMsg(String message) {
        System.out.println("Send Message: " + message);
        jmsTopicTemplate.convertAndSend(messageDestination, message);
    }

    @Override
    @JmsListener(destination = messageDestination, containerFactory = "topicListenerFactory")
    public void receiveMsg(String message) {
        System.out.println("Received Message: " + message);
    }

    @Override
    public void sendUser(User user) {
        System.out.println("Send User: " + user);
        jmsQueueTemplate.convertAndSend(userDestination, user);
    }

    @Override
    @JmsListener(destination = userDestination, containerFactory = "queueListenerFactory")
    public void receiveUser(User user) {
        System.out.println("Received User: " + user);
    }


}
