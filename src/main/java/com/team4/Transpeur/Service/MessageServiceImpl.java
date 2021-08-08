package com.team4.Transpeur.Service;

import com.team4.Transpeur.Model.Entities.Message;
import com.team4.Transpeur.Repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    }

    @Override
    public Optional<Message> findById(Long id) {
        Optional<Message> message = messageRepository.findById(id);
        return message;
    }

    @Override
    public List<Message> findByUsername(String username) {
        return messageRepository.findAll().stream().filter(m -> m.getCreator().getUsername().equals(username))
                .collect(Collectors.toList());
    }

}
