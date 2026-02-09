package com.example.demo.repository;

import com.example.demo.domain.Inventory;

import java.util.List;

public interface IInventoryRepository {
    List<Inventory> findAll();
    Inventory findByProductId(int productId);
    void save(Inventory inventory);
    void updateInventoryCount(int productId, int newCount);
    void deleteByProductId(int productId);
}
