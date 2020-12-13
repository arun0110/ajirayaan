package com.ajira.ajirayaan.repository;

import com.ajira.ajirayaan.entity.Scenarios;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScenariosRepository extends CrudRepository<Scenarios, Long> {
}
