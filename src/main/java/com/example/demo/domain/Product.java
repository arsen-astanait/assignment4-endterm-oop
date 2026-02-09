package com.example.demo.domain;

import java.util.Objects;

public class Product implements Printable {
    private int id;
    private String name;
    private double price;
    private Supplier supplier;

    public Product() {}

    public Product(int id, String name, double price, Supplier supplier) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.supplier = supplier;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public Supplier getSupplier() { return supplier; }
    public void setSupplier(Supplier supplier) { this.supplier = supplier; }

    @Override
    public String toString() {
        return name + " (" + price + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
