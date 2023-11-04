package com.dmb.securityCourse.repositories;

import com.dmb.securityCourse.entities.Validation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ValidationRepository extends CrudRepository<Validation,Integer> {

    Optional<Validation> findByCode(String code);
}
