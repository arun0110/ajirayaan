package com.ajira.ajirayaan.repository;

import com.ajira.ajirayaan.entity.RoverStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoverStatusRepository extends JpaRepository<RoverStatus, Long> {


}
