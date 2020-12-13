package com.ajira.ajirayaan.repository;

import com.ajira.ajirayaan.entity.Perform;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformRepository extends CrudRepository<Perform, Long> {
}
