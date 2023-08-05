package com.eCommerce.management.Service;
import com.eCommerce.management.Enum.OrderType;
import com.eCommerce.management.Model.Inventory;
import com.eCommerce.management.Model.Order;
import com.eCommerce.management.Repository.InventoryRepository;
import com.eCommerce.management.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;


@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private InventoryRepository inventoryRepository;


//    public void setOrderRepository(OrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }

    public List<String> addOrderDetails(List<Order> listOrder) {
        List<String> orderConfirmation = new ArrayList<>();
        for (Order order : listOrder) {
            if (order.getOrderType() == OrderType.purchase) {
                if (inventoryRepository.existsById(order.getItemId())) {
                    Optional<Inventory> discoveredInventory = inventoryRepository.findById(order.getItemId());
                    discoveredInventory.get().setQuantity(order.getQuantity() + discoveredInventory.get().getQuantity());
                    inventoryRepository.save(discoveredInventory.get());
                    order.setLocalDateTime(LocalDateTime.now());
                    orderRepository.save(order);
                    orderConfirmation.add(order.getItemId() + ":itemId successfully purchased and updated in the Inventory");

                } else {
                    orderConfirmation.add(order.getItemId() + ":itemId not found in the Inventory");
                }
            } else {
                if (inventoryRepository.existsById(order.getItemId())) {
                    Optional<Inventory> saleInventory = inventoryRepository.findById(order.getItemId());
                    if (order.getQuantity() <= saleInventory.get().getQuantity()) {
                        saleInventory.get().setQuantity(saleInventory.get().getQuantity() - order.getQuantity());
                        inventoryRepository.save(saleInventory.get());
                        order.setLocalDateTime(LocalDateTime.now());
                        orderRepository.save(order);
                        orderConfirmation.add(order.getItemId() + ":itemId  sold successfully");
                    } else {
                        orderConfirmation.add(order.getItemId() + ":itemId saleOrder Failed because quantity not available in inventory");
                    }
                } else {
                    orderConfirmation.add(order.getItemId() + ":itemId not exist in the Inventory");

                }
            }
        }
        return orderConfirmation;
    }

    public List<Order> findAllOrder() {
        return orderRepository.findAll();
    }

    public int findCountOrders() {
        return (int)  orderRepository.count();
    }
}

