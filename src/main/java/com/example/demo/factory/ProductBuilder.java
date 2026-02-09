package com.example.demo.factory;

import com.example.demo.domain.Product;
import com.example.demo.domain.Supplier;

public class ProductBuilder {
    private int id;
    private String name;
    private double price;
    private Supplier supplier;

    public ProductBuilder setId(int id) { this.id = id; return this; }
    public ProductBuilder setName(String name) { this.name = name; return this; }
    public ProductBuilder setPrice(double price) { this.price = price; return this; }
    public ProductBuilder setSupplier(Supplier supplier) { this.supplier = supplier; return this; }

    public Product build() {
        return new Product(id, name, price, supplier);
    }
}

