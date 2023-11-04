package com.dmb.securityCourse.controllers;

import com.dmb.securityCourse.entities.Avis;
import com.dmb.securityCourse.services.AvisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/avis")
public class AvisController {
    private final AvisService avisService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void creer(@RequestBody Avis avis){

        this.avisService.creer(avis);
    }
}
