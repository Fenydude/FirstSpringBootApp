package com.pbz2.repos;

import com.pbz2.entity.Inventory;
import org.springframework.data.repository.CrudRepository;

public interface InventoryRepo extends CrudRepository<Inventory, Long> {
}
