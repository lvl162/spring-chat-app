package com.team4.Transpeur.Service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sendEmail(SimpleMailMessage email);
}
