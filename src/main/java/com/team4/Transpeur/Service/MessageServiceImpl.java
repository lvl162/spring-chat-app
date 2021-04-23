package com.team4.Transpeur.Service;

import com.team4.Transpeur.Entities.Message;
import com.team4.Transpeur.Repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
    @Override
    public Message save(Message message) {
        return messageRepository.saveAndFlush(message);
    }
    public List<Message> findAll() {
        return messageRepository.findAll();
    };
}
