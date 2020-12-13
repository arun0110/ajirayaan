package com.ajira.ajirayaan.repository;

import com.ajira.ajirayaan.entity.InventoryItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryItemRepository extends CrudRepository<InventoryItem, Long> {
}
