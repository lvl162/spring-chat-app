package com.team4.Transpeur.Service;

import com.team4.Transpeur.Model.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean existsByUsername(String userName);

    User save(User user);

    boolean existsByEmail(String email);
    Optional<User> findById(Long id);
    List<User> findAll();
    void changePassword(User user, String newPassword);

    Optional<User> findByUsername(String username);
    List<User> searchByUsername(String username);
    Optional<User> findByEmail(String email);

    Page<User> findPageUser(Pageable pageable);
}
