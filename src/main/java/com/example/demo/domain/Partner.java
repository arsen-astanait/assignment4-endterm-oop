package com.example.demo.domain;

import java.util.Objects;

public abstract class Partner implements Printable {
    protected int id;
    protected String name;
    protected String contact;

    protected Partner() {}

    protected Partner(int id, String name, String contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public abstract String getRole();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Partner)) return false;
        Partner partner = (Partner) o;
        return id == partner.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name + " (" + contact + ")";
    }
}
