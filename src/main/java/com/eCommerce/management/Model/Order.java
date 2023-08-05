package com.eCommerce.management.Model;

import com.eCommerce.management.Enum.OrderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document (collection = "Order")
public class Order {
    @Id
    private String orderId;
    //    private String itemId;
//    private String itemName;
//    private int quantity;
    private List<Inventory> inventories;
    private OrderType orderType;
    private LocalDateTime localDateTime;

}
