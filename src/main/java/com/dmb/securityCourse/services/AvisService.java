package com.dmb.securityCourse.services;

import com.dmb.securityCourse.entities.Avis;
import com.dmb.securityCourse.repositories.AvisRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AvisService {
    private final AvisRepository avisRepository;
    public void creer(Avis avis){
        this.avisRepository.save(avis);
    }

}
