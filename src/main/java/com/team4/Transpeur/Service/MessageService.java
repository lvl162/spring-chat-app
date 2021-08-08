package com.team4.Transpeur.Service;

import com.team4.Transpeur.Model.Entities.Message;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    Message save(Message message);
    List<Message> findAll();
    Optional<Message> findById(Long id);
    List<Message> findByUsername(String username);
}
