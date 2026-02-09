package com.example.demo.service;

import com.example.demo.domain.Inventory;
import com.example.demo.domain.Partner;
import com.example.demo.exception.InvalidInventoryException;
import com.example.demo.repository.IInventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final IInventoryRepository repository;
    private final InventoryManager manager = new InventoryManager();

    public InventoryService(IInventoryRepository repository) {
        this.repository = repository;
    }

    private void refreshPool() {
        manager.setAll(repository.findAll());
    }

    public List<Inventory> getAllInventory() {
        refreshPool();
        return manager.getAll();
    }

    public Inventory getInventoryById(int productId) {
        return repository.findByProductId(productId);
    }

    public void addInventory(Inventory inventory) {
        validateInventory(inventory);
        repository.save(inventory);
        refreshPool();
    }

    public void updateInventory(int productId, int newCount) {
        if (productId <= 0) throw new InvalidInventoryException("productId must be positive");
        if (newCount < 0) throw new InvalidInventoryException("count cannot be negative");

        repository.updateInventoryCount(productId, newCount);
        refreshPool();
    }

    public void deleteInventory(int productId) {
        if (productId <= 0) throw new InvalidInventoryException("productId must be positive");
        repository.deleteByProductId(productId);
        refreshPool();
    }

    // ✅ DataPool demo (используется реально)
    public List<Inventory> filterByMinCount(int minCount) {
        refreshPool();
        return manager.filterByMinCount(minCount);
    }

    public List<Inventory> sortByTotalValueAsc() {
        refreshPool();
        return manager.sortByTotalValueAsc();
    }

    // ✅ Polymorphism demo (можно показать на защите)
    public String partnerInfo(Partner partner) {
        return partner.getName() + " role=" + partner.getRole();
    }

    private void validateInventory(Inventory inventory) {
        if (inventory == null) throw new InvalidInventoryException("Request body is missing");
        if (inventory.getProduct() == null) throw new InvalidInventoryException("Product is required");
        if (inventory.getProduct().getId() <= 0) throw new InvalidInventoryException("Product id must be positive");
        if (inventory.getCount() < 0) throw new InvalidInventoryException("Count cannot be negative");
    }
}
