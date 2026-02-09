package com.example.demo.domain;

public class Supplier extends Partner {

    public Supplier() { super(); }

    public Supplier(int id, String name, String contact) {
        super(id, name, contact);
    }

    @Override
    public String getRole() {
        return "SUPPLIER";
    }
}
