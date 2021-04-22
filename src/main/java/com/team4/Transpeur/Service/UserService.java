package com.team4.Transpeur.Service;

import com.team4.Transpeur.Entities.User;

public interface UserService {

    public boolean existsByUsername(String userName);

    public void save(User user);

    public boolean existsByEmail(String email);
}
