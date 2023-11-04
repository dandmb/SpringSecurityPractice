package com.dmb.securityCourse.services;

import com.dmb.securityCourse.entities.Utilisateur;
import com.dmb.securityCourse.entities.Validation;
import com.dmb.securityCourse.repositories.ValidationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@AllArgsConstructor
@Service
public class ValidationService {
    private ValidationRepository validationRepository;
    private NotificationService notificationService;
    public void enregistrer(Utilisateur utilisateur){
        Validation validation=new Validation();
        validation.setUtilisateur(utilisateur);
        Instant creation=Instant.now();
        validation.setCreation(creation);
        Instant expiration=creation.plus(10, ChronoUnit.MINUTES);
        validation.setExpire(expiration);
        Random random=new Random();
        int randomInteger= random.nextInt(999999);
        String code=String.format("%06d",randomInteger);
        validation.setCode(code);
        this.validationRepository.save(validation);
        notificationService.envoyer(validation);

    }

    public Validation lireEnFonctionDuCode(String code){
        return validationRepository.findByCode(code).orElseThrow(
                ()->new RuntimeException("Votre code est invalide")
        );
    }
}
