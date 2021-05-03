package com.team4.Transpeur.Service;

import com.team4.Transpeur.Model.Entities.Message;
import com.team4.Transpeur.Model.BO.MessageBO;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    public Message save(Message message);
    public List<MessageBO> findAll();
    public Optional<MessageBO> findById(Long id);
    public List<MessageBO> findByUsername(String username);
}
