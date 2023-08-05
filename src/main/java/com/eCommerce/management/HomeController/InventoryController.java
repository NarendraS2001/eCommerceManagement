package com.eCommerce.management.HomeController;

import com.eCommerce.management.Model.Inventory;
import com.eCommerce.management.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {

        this.inventoryService = inventoryService;
    }

    @PostMapping("/")
    public List<String> createInventory(@RequestBody List<Inventory> inventory){
        return inventoryService.addItemToInventory(inventory);
    }

    @GetMapping("/")
    public List<Inventory> getAllItemsFromInventory(){

        return  inventoryService.findAllProduct();
    }
    @PutMapping("/{itemId}")
    public String modifyItem( @PathVariable ("itemId")String itemIdRequest,@RequestBody Inventory inventory) {
        return  inventoryService.updateItem(itemIdRequest,inventory);
    }
    @DeleteMapping("/{itemId}")
    public String deleteItemById(@PathVariable("itemId") String itemId){
       return inventoryService.deleteItem(itemId);

    }


}
