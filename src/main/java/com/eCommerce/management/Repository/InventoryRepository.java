package com.eCommerce.management.Repository;

import com.eCommerce.management.Model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface InventoryRepository extends MongoRepository<Inventory,String> {

}
