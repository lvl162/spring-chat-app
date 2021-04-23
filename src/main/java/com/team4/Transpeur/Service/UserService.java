package com.team4.Transpeur.Service;

import com.team4.Transpeur.Entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public boolean existsByUsername(String userName);

    public void save(User user);

    public boolean existsByEmail(String email);
    public Optional<User> findById(Long id);
    public List<User> findAll();
}
