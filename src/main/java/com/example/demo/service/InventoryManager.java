package com.example.demo.service;

import com.example.demo.domain.Inventory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InventoryManager {
    private final List<Inventory> inventoryList = new ArrayList<>();

    public void setAll(List<Inventory> newList) {
        inventoryList.clear();
        if (newList != null) inventoryList.addAll(newList);
    }

    public List<Inventory> getAll() {
        return new ArrayList<>(inventoryList);
    }

    public Inventory findByProductName(String name) {
        if (name == null) return null;
        for (Inventory inv : inventoryList) {
            if (inv.getProduct() != null &&
                    inv.getProduct().getName() != null &&
                    inv.getProduct().getName().equalsIgnoreCase(name)) {
                return inv;
            }
        }
        return null;
    }

    public List<Inventory> filterByMinCount(int minCount) {
        return SearchUtil.filter(inventoryList, inv -> inv.getCount() >= minCount);
    }

    public List<Inventory> sortByTotalValueAsc() {
        return SearchUtil.sort(inventoryList, Comparator.comparingDouble(Inventory::calculateTotalValue));
    }
}
