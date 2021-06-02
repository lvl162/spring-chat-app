package com.team4.Transpeur.Service;

import com.team4.Transpeur.Model.Entities.Message;
import com.team4.Transpeur.Model.BO.MessageBO;
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
    public List<MessageBO> findAll() {
        return messageRepository.findAll().stream().map(message -> new MessageBO(message.getId(), message.getChatRoom().getId(), message.getCreator().getId(),message.getCreator().getUsername(),message.getContent(),
                message.getMessageType() , message.getCreatedAt())).collect(Collectors.toList());
    }

    @Override
    public Optional<MessageBO> findById(Long id) {
        Optional<Message> message = messageRepository.findById(id);
        return message.map(value -> new MessageBO(value.getId(), value.getChatRoom().getId(), value.getCreator().getId(), value.getCreator().getUsername(), value.getContent(),
                value.getMessageType(), value.getCreatedAt()));
    }

    @Override
    public List<MessageBO> findByUsername(String username) {
        return messageRepository.findAll().stream().filter(m -> m.getCreator().getUsername().equals(username))
                .map(message -> new MessageBO(message.getId(), message.getChatRoom().getId(), message.getCreator().getId(),message.getCreator().getUsername(),message.getContent(),
                        message.getMessageType() , message.getCreatedAt()))
                .collect(Collectors.toList());
    }

}
