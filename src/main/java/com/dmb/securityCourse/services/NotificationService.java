package com.dmb.securityCourse.services;

import com.dmb.securityCourse.entities.Validation;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificationService {
    JavaMailSender javaMailSender;
    public void envoyer(Validation validation) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("no-reply@dmb.com");
        simpleMailMessage.setTo(validation.getUtilisateur().getEmail());
        simpleMailMessage.setSubject("Code d'activation");
        String texte = String.format(
                "Bonjour %s, <br /> Votre code d'activation est %s; A bientot",
                validation.getUtilisateur().getNom(),
                validation.getCode()
        );
        simpleMailMessage.setText(texte);
        javaMailSender.send(simpleMailMessage);
    }
}
