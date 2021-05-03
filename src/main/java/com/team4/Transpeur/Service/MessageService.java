package com.team4.Transpeur.Service;

import com.team4.Transpeur.Model.Entities.Message;
import com.team4.Transpeur.Payload.Respone.MessageDAO;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    public Message save(Message message);
    public List<MessageDAO> findAll();
    public Optional<MessageDAO> findById(Long id);
    public List<MessageDAO> findByUsername(String username);
}
