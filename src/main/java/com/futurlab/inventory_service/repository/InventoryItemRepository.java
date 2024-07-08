package com.futurlab.inventory_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.futurlab.inventory_service.model.InventoryItem;
import com.futurlab.inventory_service.model.User;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    InventoryItem findByUserAndItemName(User user, String itemName);

    List<InventoryItem> findByUser(User user);

    InventoryItem findByUserIdAndItemName(Long userId, String itemName);
}