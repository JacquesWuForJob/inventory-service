package com.futurlab.inventory_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.futurlab.inventory_service.model.InventoryItem;
import com.futurlab.inventory_service.model.User;
import com.futurlab.inventory_service.repository.InventoryItemRepository;
import com.futurlab.inventory_service.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    /**
     * Fetches the inventory for a given user.
     *
     * @param userId The ID of the user whose inventory is to be fetched.
     * @return A list of InventoryItem objects representing the user's inventory.
     */
    public List<InventoryItem> getUserInventory(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<InventoryItem> items = inventoryItemRepository.findByUser(user);
        return items;
    }

    /**
     * Adds an item to the user's inventory. If the item already exists, the quantity is updated.
     *
     * @param userId The ID of the user.
     * @param item   The InventoryItem to be added.
     */
    public void addItemToInventory(Long userId, InventoryItem item) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        InventoryItem existingItem = inventoryItemRepository.findByUserAndItemName(user, item.getItemName());
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
            inventoryItemRepository.save(existingItem);
        } else {
            item.setUser(user);
            inventoryItemRepository.save(item);
        }
    }

    /**
     * Adds multiple items to the user's inventory. If an item already exists, the quantity is updated.
     *
     * @param userId The ID of the user.
     * @param items  A list of InventoryItem objects to be added.
     */
    public void addItemsToInventory(Long userId, List<InventoryItem> items) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        for (InventoryItem item : items) {
            InventoryItem existingItem = inventoryItemRepository.findByUserAndItemName(user, item.getItemName());
            if (existingItem != null) {
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
                inventoryItemRepository.save(existingItem);
            } else {
                item.setUser(user);
                inventoryItemRepository.save(item);
            }
        }
    }

    /**
     * Subtracts a quantity of an item from the user's inventory. Throws an exception if the quantity is insufficient.
     *
     * @param userId The ID of the user.
     * @param item   The InventoryItem to be subtracted.
     */
    public void subtractItemFromInventory(Long userId, InventoryItem item) {
        InventoryItem existingItem = inventoryItemRepository.findByUserIdAndItemName(userId, item.getItemName());

        if (existingItem == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        }

        if (existingItem.getUser().getId().equals(userId)) {
            if (existingItem.getQuantity() >= item.getQuantity()) {
                existingItem.setQuantity(existingItem.getQuantity() - item.getQuantity());
                inventoryItemRepository.save(existingItem);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough quantity");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only edit your own inventory");
        }
    }
}