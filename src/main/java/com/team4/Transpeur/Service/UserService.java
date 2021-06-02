package com.team4.Transpeur.Service;

import com.team4.Transpeur.Model.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public boolean existsByUsername(String userName);

    public User save(User user);

    public boolean existsByEmail(String email);
    public Optional<User> findById(Long id);
    public List<User> findAll();
//    public boolean validUserPassword(User user, String newPassword);
    public void changePassword(User user, String newPassword);

    public Optional<User> findByUsername(String username);

    public Optional<User> findByEmail(String email);

    public Page<User> findPageUser(Pageable pageable);
}
