package com.ajira.ajirayaan.repository;

import com.ajira.ajirayaan.entity.StateAndAction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateAndActionRepository extends CrudRepository<StateAndAction, Long> {
}
