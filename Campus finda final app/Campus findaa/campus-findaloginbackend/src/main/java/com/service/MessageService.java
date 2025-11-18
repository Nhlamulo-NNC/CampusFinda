package com.service;

import com.model.Message;
import com.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository repo;

    public MessageService(MessageRepository repo) {
        this.repo = repo;
    }

    public Message saveMessage(Message m) {
        return repo.save(m);
    }

    public List<Message> getAllMessages() {
        return repo.findAll();
    }
}


