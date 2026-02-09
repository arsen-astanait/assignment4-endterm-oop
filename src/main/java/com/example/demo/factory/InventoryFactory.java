package com.example.demo.factory;

import com.example.demo.domain.Inventory;
import com.example.demo.domain.Product;

public class InventoryFactory {
    private InventoryFactory() {}

    public static Inventory create(Product product, int count) {
        return new Inventory(product, count);
    }
}
