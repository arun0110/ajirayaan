package com.ajira.ajirayaan.repository;

import com.ajira.ajirayaan.entity.AreaMap;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaMapRepository extends CrudRepository<AreaMap, Long> {
}
