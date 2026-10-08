package com.franquisias.model;

import lombok.Data;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

@Data
public class Sucursal {
    private String id = UUID.randomUUID().toString();
    private String name;
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(String productId) {
        boolean removed = this.products.removeIf(p -> p.getId().equals(productId));
        if (!removed) {
            throw new RuntimeException("Producto no encontrado en la sucursal");
        }
    }

    public Product getProductById(String productId) {
        return this.products.stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en la sucursal"));
    }

    public Optional<Product> getTopProduct() {
        return this.products.stream()
                .max(Comparator.comparingInt(Product::getStock));
    }
}
