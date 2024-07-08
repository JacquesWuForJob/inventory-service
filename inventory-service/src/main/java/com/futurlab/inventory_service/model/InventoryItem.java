package com.futurlab.inventory_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class InventoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String itemName;
    private int quantity;

    // Getters and Setters by @Data

}
