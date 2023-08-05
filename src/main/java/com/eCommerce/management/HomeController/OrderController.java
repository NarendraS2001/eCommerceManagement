package com.eCommerce.management.HomeController;

import com.eCommerce.management.Model.Order;
import com.eCommerce.management.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
   @PostMapping("/")
    public List<String> createOrder(@RequestBody List<Order> listOrder){

       return orderService.addOrderDetails(listOrder);
    }@GetMapping("/")
    public List<Order> getAllOrderDetails(){
       return orderService.findAllOrder();
    }
    @GetMapping("/total")
    public int totalNumberOfOrders(){
       return orderService.findCountOrders();
    }

}
