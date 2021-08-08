package com.team4.Transpeur.Repositories;

import com.team4.Transpeur.Model.Entities.PasswordResetToken;
import com.team4.Transpeur.Service.PasswordResetService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {
    public PasswordResetToken findByToken(String token);
}
