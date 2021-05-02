package com.team4.Transpeur.Service;

import com.team4.Transpeur.Model.Entities.Message;

import java.util.List;

public interface MessageService {
    public Message save(Message message);
    public List<Message> findAll();
}
