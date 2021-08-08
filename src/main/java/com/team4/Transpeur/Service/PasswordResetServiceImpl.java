package com.team4.Transpeur.Service;

import com.team4.Transpeur.Model.Entities.PasswordResetToken;
import com.team4.Transpeur.Repositories.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {
    final PasswordResetTokenRepository passwordTokenRepository;

    @Autowired
    public PasswordResetServiceImpl(PasswordResetTokenRepository passwordTokenRepository) {
        this.passwordTokenRepository = passwordTokenRepository;
    }

    @Override
    public void save(PasswordResetToken passwordResetToken) {
        passwordTokenRepository.save(passwordResetToken);
    }

    @Override
    public String validatePasswordResetToken( String token) {
        final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);
        if ((passToken == null)) {
            return "invalidToken";
        }

        final Calendar cal = Calendar.getInstance();
        if ((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return "expired";
        }
//        final User user = passToken.getUser();
//        final Authentication auth = new UsernamePasswordAuthenticationToken(user, null, Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
//        SecurityContextHolder.getContext().setAuthentication(auth);
        return null;
    }
}