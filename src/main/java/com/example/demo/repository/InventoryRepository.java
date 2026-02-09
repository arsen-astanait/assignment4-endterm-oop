package com.example.demo.repository;

import com.example.demo.config.DBConnection;
import com.example.demo.domain.Inventory;
import com.example.demo.domain.Product;
import com.example.demo.domain.Supplier;
import com.example.demo.exception.InventoryNotFoundException;
import com.example.demo.factory.InventoryFactory;
import com.example.demo.factory.ProductBuilder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InventoryRepository implements IInventoryRepository {

    @Override
    public List<Inventory> findAll() {
        List<Inventory> list = new ArrayList<>();
        String sql = """
            SELECT p.id, p.name, p.price,
                   s.id AS supplier_id, s.name AS supplier_name, s.contact,
                   i.count
            FROM inventory i
            JOIN products p ON i.product_id = p.id
            JOIN suppliers s ON p.supplier_id = s.id
        """;

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Supplier supplier = new Supplier(
                        rs.getInt("supplier_id"),
                        rs.getString("supplier_name"),
                        rs.getString("contact")
                );

                // builder in flow
                Product product = new ProductBuilder()
                        .setId(rs.getInt("id"))
                        .setName(rs.getString("name"))
                        .setPrice(rs.getDouble("price"))
                        .setSupplier(supplier)
                        .build();

                // factory in flow
                Inventory inventory = InventoryFactory.create(product, rs.getInt("count"));
                list.add(inventory);
            }

            return list;

        } catch (SQLException e) {
            throw new RuntimeException("DB error in findAll()", e);
        }
    }

    @Override
    public Inventory findByProductId(int productId) {
        String sql = """
            SELECT p.id, p.name, p.price,
                   s.id AS supplier_id, s.name AS supplier_name, s.contact,
                   i.count
            FROM inventory i
            JOIN products p ON i.product_id = p.id
            JOIN suppliers s ON p.supplier_id = s.id
            WHERE p.id = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    throw new InventoryNotFoundException("Inventory with product ID " + productId + " not found");
                }

                Supplier supplier = new Supplier(
                        rs.getInt("supplier_id"),
                        rs.getString("supplier_name"),
                        rs.getString("contact")
                );

                Product product = new ProductBuilder()
                        .setId(rs.getInt("id"))
                        .setName(rs.getString("name"))
                        .setPrice(rs.getDouble("price"))
                        .setSupplier(supplier)
                        .build();

                return InventoryFactory.create(product, rs.getInt("count"));
            }

        } catch (SQLException e) {
            throw new RuntimeException("DB error in findByProductId()", e);
        }
    }

    @Override
    public void save(Inventory inventory) {
        String sql = """
            INSERT INTO inventory (product_id, count)
            VALUES (?, ?)
            ON CONFLICT (product_id)
            DO UPDATE SET count = EXCLUDED.count
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, inventory.getProduct().getId());
            ps.setInt(2, inventory.getCount());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("DB error in save()", e);
        }
    }

    @Override
    public void updateInventoryCount(int productId, int newCount) {
        String sql = "UPDATE inventory SET count = ? WHERE product_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, newCount);
            ps.setInt(2, productId);

            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new InventoryNotFoundException("Inventory with product ID " + productId + " not found");
            }

        } catch (SQLException e) {
            throw new RuntimeException("DB error in updateInventoryCount()", e);
        }
    }

    @Override
    public void deleteByProductId(int productId) {
        String sql = "DELETE FROM inventory WHERE product_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new InventoryNotFoundException("Inventory with product ID " + productId + " not found");
            }

        } catch (SQLException e) {
            throw new RuntimeException("DB error in deleteByProductId()", e);
        }
    }
}
