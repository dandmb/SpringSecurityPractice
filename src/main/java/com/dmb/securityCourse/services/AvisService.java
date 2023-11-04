package com.dmb.securityCourse.services;

import com.dmb.securityCourse.entities.Avis;
import com.dmb.securityCourse.entities.Utilisateur;
import com.dmb.securityCourse.repositories.AvisRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AvisService {
    private final AvisRepository avisRepository;
    public void creer(Avis avis){
        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        avis.setUtilisateur(utilisateur);
        this.avisRepository.save(avis);
    }

}
