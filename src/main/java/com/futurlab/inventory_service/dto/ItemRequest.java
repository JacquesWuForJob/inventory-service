package com.futurlab.inventory_service.dto;

public class ItemRequest {

    private String itemName;
    private int quantity;

    public ItemRequest() {}

    public ItemRequest(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    // Getters and Setters

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
