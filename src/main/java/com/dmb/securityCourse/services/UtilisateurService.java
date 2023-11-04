package com.dmb.securityCourse.services;

import com.dmb.securityCourse.entities.Role;
import com.dmb.securityCourse.entities.TypeDeRole;
import com.dmb.securityCourse.entities.Utilisateur;
import com.dmb.securityCourse.entities.Validation;
import com.dmb.securityCourse.repositories.UtilisateurRepository;
import com.dmb.securityCourse.repositories.ValidationRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;


@AllArgsConstructor
@Service
public class UtilisateurService implements UserDetailsService {
    private UtilisateurRepository utilisateurRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private ValidationService validationService;
    private ValidationRepository validationRepository;

    public void inscription(Utilisateur utilisateur) {
        if ((!utilisateur.getEmail().contains("@")) || (!utilisateur.getEmail().contains("."))) {
            throw new RuntimeException("Email invalide");
        }
        Optional<Utilisateur> utilisateurOptional=this.utilisateurRepository.findByEmail(utilisateur.getEmail());

        if (utilisateurOptional.isPresent()){
            throw new RuntimeException("Ce mail existe deja");
        }

        String mdpCrypted = this.passwordEncoder.encode(utilisateur.getMdp());
        utilisateur.setMdp(mdpCrypted);

        Role role=new Role();
        role.setLibelle(TypeDeRole.UTILISATEUR);
        utilisateur.setRole(role);
        this.utilisateurRepository.save(utilisateur);
        this.validationService.enregistrer(utilisateur);
    }

    public void activation(Map<String, String> activation) {
        Validation validation=this.validationService.lireEnFonctionDuCode(activation.get("code"));
        if (Instant.now().isAfter(validation.getExpire())){
            throw new RuntimeException("Votre code a expiré");
        }
       Utilisateur utilisateur= this.utilisateurRepository.findById(validation.getUtilisateur().getId()).orElseThrow(
                ()-> new RuntimeException("Utilisateur inconnu")
        );
        utilisateur.setActif(true);
        utilisateurRepository.save(utilisateur);
        validation.setActivation(Instant.now());
        validationRepository.save(validation);
    }

    // Aller chercher le user ds la bd en fction du mail
    //
    @Override
    public Utilisateur loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.utilisateurRepository
                .findByEmail(username)
                .orElseThrow(() -> new  UsernameNotFoundException("Aucun utilisateur ne corespond à cet identifiant"));
    }
}
