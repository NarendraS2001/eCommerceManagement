package com.eCommerce.management.Service;

import com.eCommerce.management.Model.Inventory;
import com.eCommerce.management.Repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class InventoryServiceTest {
  @InjectMocks
  InventoryService inventoryService;
  @Mock
  InventoryRepository inventoryRepository;

//  @Test
//  void testAddItemToInventory() {
//    // Arrange the items for testing---
//    //    Given
//    List<Inventory> inventoryList = new ArrayList<>();
//    Inventory item1 = new Inventory("1","Electronics", "Earphone", 1000.0, 3);
//    inventoryList.add(item1);
//
//    Inventory item2 = new Inventory("2","Clothing", "T-Shirt", 250.0, 2);
//    inventoryList.add(item2);
//
//    Inventory newItem = new Inventory("3","Accessories", "Watch", 50.0, 1);
//    inventoryList.add(newItem);
////    when(mockRepository.existsById(existingItem.getItemId())).thenReturn(true);
//    // Actual
//    List<String> result = inventoryService.addItemToInventory(inventoryList);
//
//    // Assert
////    Then
//    assertEquals(3, result.size());
//
//
//     assertEquals("item already exist with item id:" + item1.getItemId(), result.get(0));
//     assertEquals("item already exist with item id:" + item2.getItemId(), result.get(1));
//     assertEquals("item successfully saved with itemId:" + newItem.getItemId(), result.get(2));
//  }
//}

  @Test
  public void testFindAllProduct() {
    // Given, Expected, Prepare data for test
    List<Inventory> testItem = new ArrayList<>();
    testItem.add(new Inventory("1", "Electronics", "Earphone", 1000.0, 2));
    testItem.add(new Inventory("2", "Clothing", "T-Shirt", 250.0, 1));

    // When and Then ,Mock the behavior of the repository
    when(inventoryRepository.findAll()).thenReturn(testItem);

    // Call the service method
    List<Inventory> result = inventoryService.findAllProduct();

    // Verify the result,with help of assert
    assertEquals(2, result.size());
    assertEquals(testItem.get(0), result.get(0));
    assertEquals("T-Shirt", result.get(1).getName());
  }

  @Test
  public void testUpdateItem_ItemFound() {
    // Given OR
    // Prepare test data
    String itemIdRequest = "itemId001";
    Inventory existingItem = new Inventory("Music", "Earphone", 1000.0, 2);
    Inventory updateBody = new Inventory("Electronics", "Headphone", 2000.0, 2);

    //When OR
    // Mock the behavior of the repository
    when(inventoryRepository.findById(itemIdRequest)).thenReturn(Optional.of(existingItem));
    // Call the service method
    String result = inventoryService.updateItem(itemIdRequest, updateBody);

    //Assert, Verify the result and interactions
    assertEquals("itemId001 itemId updated successfully", result);
    assertEquals("Headphone", existingItem.getName());
    assertEquals("Electronics", existingItem.getCategory());
    assertEquals(2000.0, existingItem.getPrice());
  }

  @Test
  public void testUpdateItem_ItemNotFound() {
    // Given,Prepare test data
    String itemIdRequest = "itemId002";
    Inventory updateBody = new Inventory("Electronics", "Earphone", 1000.0, 2);

    //When, Mock the behavior of the repository
    when(inventoryRepository.findById(itemIdRequest)).thenReturn(Optional.empty());

    // Call the service method and assert that it throws the expected exception
    assertThrows(RuntimeException.class, () -> inventoryService.updateItem(itemIdRequest, updateBody));

  }

  @Test
  public void testDeleteItem_ItemFound() {
    //    Given
    //      prepare test data
    String itemId = "itemId001";
    //    When
    //    Mock the behavior repository
    when(inventoryRepository.existsById(itemId)).thenReturn(true);

    String result = inventoryService.deleteItem(itemId);
    //     Assert
    assertEquals(itemId + " itemId product deleted successfully", result);

  }

  @Test
  public void testDeleteItem_ItemNotFound() {
//    Given
    String itemId="itemId005";
//    when
    when(inventoryRepository.existsById(itemId)).thenReturn(false);
    String result=inventoryService.deleteItem(itemId);
//    Assert
    assertEquals(itemId + " itemId product does not Exist in inventory",result);

  }

}





