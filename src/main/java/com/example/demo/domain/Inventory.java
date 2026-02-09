package com.example.demo.domain;

import java.util.Objects;

public class Inventory implements Printable {
    private Product product;
    private int count;

    public Inventory() {}

    public Inventory(Product product, int count) {
        this.product = product;
        this.count = count;
    }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }

    public double calculateTotalValue() {
        if (product == null) return 0.0;
        return product.getPrice() * count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Inventory)) return false;
        Inventory that = (Inventory) o;
        return Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }

    @Override
    public String toString() {
        String pName = (product == null) ? "null" : product.getName();
        return pName + " | count: " + count + " | total: " + calculateTotalValue();
    }
}
