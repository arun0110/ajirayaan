package com.ajira.ajirayaan.repository;


import com.ajira.ajirayaan.entity.EnvironmentConfig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvironmentConfigRepository extends CrudRepository<EnvironmentConfig, Long> {
}
