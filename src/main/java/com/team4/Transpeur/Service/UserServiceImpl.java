package com.team4.Transpeur.Service;

import com.team4.Transpeur.Entities.User;
import com.team4.Transpeur.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public boolean existsByUsername(String userName){
        return userRepository.existsByUsername(userName);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
