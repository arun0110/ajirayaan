package com.ajira.ajirayaan.repository;

import com.ajira.ajirayaan.entity.Environment;
import com.ajira.ajirayaan.entity.EnvironmentUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvironmentRepository extends JpaRepository<Environment,Long> {

}
