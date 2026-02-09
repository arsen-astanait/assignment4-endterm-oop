package com.example.demo.domain;

public interface Printable {
    default String pretty() {
        return toString();
    }
}
