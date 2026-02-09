package com.example.demo.controller;

import com.example.demo.domain.Inventory;
import com.example.demo.dto.UpdateCountRequest;
import com.example.demo.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Inventory> getAll() {
        return service.getAllInventory();
    }

    @GetMapping("/{id}")
    public Inventory getById(@PathVariable("id") int id) {
        return service.getInventoryById(id);
    }

    @PostMapping
    public String add(@RequestBody Inventory inventory) {
        service.addInventory(inventory);
        return "Inventory added/updated successfully";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") int id, @RequestBody UpdateCountRequest req) {
        service.updateInventory(id, req.getCount());
        return "Inventory updated successfully";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        service.deleteInventory(id);
        return "Inventory deleted successfully";
    }

    // ✅ DataPool endpoints (для демонстрации требований)
    @GetMapping("/filter/minCount/{minCount}")
    public List<Inventory> filterMinCount(@PathVariable int minCount) {
        return service.filterByMinCount(minCount);
    }

    @GetMapping("/sort/totalValue")
    public List<Inventory> sortByTotalValue() {
        return service.sortByTotalValueAsc();
    }
}

