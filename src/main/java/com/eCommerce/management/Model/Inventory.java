package com.eCommerce.management.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document (collection = "inventory")
public class Inventory {


    @Id
    private String itemId;
    private String category;
    private String name;
    private double price;
    private int quantity;

    public Inventory(String category, String name, double price, int quantity) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

}
