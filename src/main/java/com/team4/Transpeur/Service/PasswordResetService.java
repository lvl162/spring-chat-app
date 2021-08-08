package com.team4.Transpeur.Service;

import com.team4.Transpeur.Model.Entities.PasswordResetToken;

public interface PasswordResetService {
    String validatePasswordResetToken(String token);
    void save(PasswordResetToken passwordResetToken);
}