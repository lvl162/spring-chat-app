package com.team4.Transpeur.Service;

import com.team4.Transpeur.Entities.Message;
import com.team4.Transpeur.Entities.User;

import java.util.List;

public interface MessageService {
    public Message save(Message message);
    public List<Message> findAll();
}
