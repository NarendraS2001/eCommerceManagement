package com.eCommerce.management.Service;

import com.eCommerce.management.Model.Inventory;
import com.eCommerce.management.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {

        this.inventoryRepository = inventoryRepository;
    }

    //    Adding items into inventory--
    public List<String> addItemToInventory(List<Inventory> inventory) {
        List<String> updatedItemList = new ArrayList<>();
        for (Inventory item : inventory) {
            if (inventoryRepository.existsById(item.getItemId())) {
                updatedItemList.add("item already exist with item id:" + item.getItemId());
            } else {
                Inventory updateItem = inventoryRepository.save(item);
                updatedItemList.add("item successfully saved with itemId:" + updateItem.getItemId());
            }
        }
        return updatedItemList;
    }

    //    Retrieving All items List OR Read Operation
    public List<Inventory> findAllProduct() {
        return inventoryRepository.findAll();
    }


    public String updateItem( String itemIdRequest, Inventory updateBody) {
        Optional<Inventory> existingItem = inventoryRepository.findById(itemIdRequest);
        if (existingItem.isEmpty())
            throw new RuntimeException("item not found");
        {
            existingItem.get().setName(updateBody.getName());
            existingItem.get().setCategory(updateBody.getCategory());
            existingItem.get().setPrice(updateBody.getPrice());
            inventoryRepository.save(existingItem.get());

            return itemIdRequest+" itemId updated successfully";

        }
    }

    public String deleteItem(String itemId) {
        if (inventoryRepository.existsById(itemId)) {
            inventoryRepository.deleteById(itemId);
            return itemId + " itemId product deleted successfully";
        } else return itemId + " itemId product does not Exist in inventory";
    }
}




