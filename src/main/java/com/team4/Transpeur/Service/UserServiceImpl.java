package com.team4.Transpeur.Service;

import com.team4.Transpeur.Model.Entities.User;
import com.team4.Transpeur.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public User save(User user) {
        return userRepository.saveAndFlush(user);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public List<User> searchByUsername(String username) {
        return userRepository.findAll().stream().filter(p -> p.getUsername().contains(username) || p.getEmail().contains(username))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void changePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Page<User> findPageUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

}
