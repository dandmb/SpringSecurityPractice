package com.dmb.securityCourse.repositories;

import com.dmb.securityCourse.entities.Avis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisRepository extends CrudRepository<Avis,Integer> {
}
