package com.franquisias.model;

import lombok.Data;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "franquisias")
public class Franquisia {
    private String id = UUID.randomUUID().toString();
    private String name;
    private List<Sucursal> sucursales = new ArrayList<>();

    public void addSucursal(Sucursal sucursal) {
        this.sucursales.add(sucursal);
    }

    public void addProductToSucursal(String sucursalId, Product product) {
        Sucursal sucursal = this.sucursales.stream()
                .filter(s -> s.getId().equals(sucursalId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
        sucursal.addProduct(product);
    }

    public void removeProductFromSucursal(String sucursalId, String productId) {
        Sucursal sucursal = this.sucursales.stream()
                .filter(s -> s.getId().equals(sucursalId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
        sucursal.removeProduct(productId);
    }
}
