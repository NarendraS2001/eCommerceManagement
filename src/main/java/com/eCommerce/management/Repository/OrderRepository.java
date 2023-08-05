package com.eCommerce.management.Repository;

import com.eCommerce.management.Model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {


}
