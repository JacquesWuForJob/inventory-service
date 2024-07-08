package com.futurlab.inventory_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.futurlab.inventory_service.model.InventoryItem;
import com.futurlab.inventory_service.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Simulates user authentication.
     * In a real-world application, replace this with proper authentication logic
     * such as retrieving the user ID from a JWT token or session.
     */
    private Long getCurrentUserId() {
        return 1L; // Assume the current user ID is 1
    }

    /**
     * Endpoint to get a user's inventory.
     *
     * @param userId The ID of the user.
     * @return The user's inventory.
     */
    @GetMapping("/{userId}/inventory")
    public ResponseEntity<List<InventoryItem>> getUserInventory(@PathVariable Long userId) {
        if (!userId.equals(getCurrentUserId())) {
            return ResponseEntity.status(403).build();
        }
        List<InventoryItem> inventoryItems = userService.getUserInventory(userId);
        return ResponseEntity.ok(inventoryItems);
    }


    /**
     * Endpoint to add an item to a user's inventory.
     *
     * @param userId The ID of the user.
     * @param item   The item to add.
     * @return ResponseEntity indicating the result of the operation.
     */
    @PostMapping("/{userId}/inventory")
    public ResponseEntity<Void> addItemToInventory(@PathVariable Long userId, @RequestBody InventoryItem item) {
        if (!userId.equals(getCurrentUserId())) {
            return ResponseEntity.status(403).build();
        }
        userService.addItemToInventory(userId, item);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint to add multiple items to a user's inventory.
     *
     * @param userId The ID of the user.
     * @param items  The items to add.
     * @return ResponseEntity indicating the result of the operation.
     */
    @PostMapping("/{userId}/inventory/bulk")
    public ResponseEntity<Void> addItemsToInventory(@PathVariable Long userId, @RequestBody List<InventoryItem> items) {
        if (!userId.equals(getCurrentUserId())) {
            return ResponseEntity.status(403).build();
        }
        userService.addItemsToInventory(userId, items);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint to subtract an item from a user's inventory.
     *
     * @param userId The ID of the user.
     * @param item   The item to subtract.
     * @return ResponseEntity indicating the result of the operation.
     */
    @PostMapping("/{userId}/inventory/subtract")
    public ResponseEntity<Void> subtractItemFromInventory(@PathVariable Long userId, @RequestBody InventoryItem item) {
        if (!userId.equals(getCurrentUserId())) {
            return ResponseEntity.status(403).build();
        }
        userService.subtractItemFromInventory(userId, item);
        return ResponseEntity.ok().build();
    }
}
