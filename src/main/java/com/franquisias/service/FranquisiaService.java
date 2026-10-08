package com.franquisias.service;

import org.springframework.stereotype.Service;

import com.franquisias.dto.FranquisiaResponse;
import com.franquisias.dto.ProductoStockResponse;
import com.franquisias.model.Franquisia;
import com.franquisias.model.Sucursal;
import com.franquisias.model.Product;
import com.franquisias.repository.FranquisiaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FranquisiaService {

    private final FranquisiaRepository franquisiaRepository;

    public FranquisiaService(FranquisiaRepository franquisiaRepository) {
        this.franquisiaRepository = franquisiaRepository;
    }

    public List<FranquisiaResponse> getAllFranquicias() {
        return franquisiaRepository.findAll().stream()
                .map(franquisia -> new FranquisiaResponse(
                        franquisia.getId(),
                        franquisia.getName(),
                        franquisia.getSucursales().size()))
                .collect(Collectors.toList());
    }

    public Franquisia getFranquisiaById(String id) {
        return franquisiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Franquisia no encontrada"));
    }

    public Franquisia createFranquisia(Franquisia franquisia) {
        return franquisiaRepository.save(franquisia);
    }

    public Franquisia addSucursalToFranquisia(String franquisiaId, Sucursal sucursal) {
        return franquisiaRepository.findById(franquisiaId)
                .map(franquisia -> {
                    franquisia.addSucursal(sucursal);
                    return franquisiaRepository.save(franquisia);
                })
                .orElseThrow(() -> new RuntimeException("Franquisia no encontrada"));
    }

    public Franquisia addProductToSucursal(String franquisiaId, String sucursalId, Product product) {
        return franquisiaRepository.findById(franquisiaId)
                .map(franquisia -> {
                    franquisia.addProductToSucursal(sucursalId, product);
                    return franquisiaRepository.save(franquisia);
                })
                .orElseThrow(() -> new RuntimeException("Franquisia no encontrada"));
    }

    public Franquisia removeProductFromSucursal(String franquisiaId, String sucursalId, String productId) {
        return franquisiaRepository.findById(franquisiaId)
                .map(franquisia -> {
                    franquisia.removeProductFromSucursal(sucursalId, productId);
                    return franquisiaRepository.save(franquisia);
                })
                .orElseThrow(() -> new RuntimeException("Franquisia no encontrada"));
    }

    public Franquisia updateFranquisiaName(String franquisiaId, String name) {
        return franquisiaRepository.findById(franquisiaId)
                .map(franquisia -> {
                    franquisia.setName(name);
                    return franquisiaRepository.save(franquisia);
                })
                .orElseThrow(() -> new RuntimeException("Franquisia no encontrada"));
    }

    public Franquisia updateSucursalName(String franquisiaId, String sucursalId, String name) {
        return franquisiaRepository.findById(franquisiaId)
                .map(franquisia -> {
                    Sucursal sucursal = franquisia.getSucursales().stream()
                            .filter(s -> s.getId().equals(sucursalId))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
                    sucursal.setName(name);
                    return franquisiaRepository.save(franquisia);
                })
                .orElseThrow(() -> new RuntimeException("Franquisia no encontrada"));
    }

    public Franquisia updateProductName(String franquisiaId, String sucursalId, String productId, String name) {
        return franquisiaRepository.findById(franquisiaId)
                .map(franquisia -> {
                    Sucursal sucursal = franquisia.getSucursales().stream()
                            .filter(s -> s.getId().equals(sucursalId))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
                    Product product = sucursal.getProductById(productId);
                    product.setName(name);
                    return franquisiaRepository.save(franquisia);
                })
                .orElseThrow(() -> new RuntimeException("Franquisia no encontrada"));
    }

    public Franquisia updateProductStock(String franquisiaId, String sucursalId, String productId, Integer stock) {
        return franquisiaRepository.findById(franquisiaId)
                .map(franquisia -> {
                    Sucursal sucursal = franquisia.getSucursales().stream()
                            .filter(s -> s.getId().equals(sucursalId))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
                    Product product = sucursal.getProductById(productId);
                    product.setStock(stock);
                    return franquisiaRepository.save(franquisia);
                })
                .orElseThrow(() -> new RuntimeException("Franquisia no encontrada"));
    }

    public List<ProductoStockResponse> getTopStockProductsByFranquisia(String franquisiaId) {
        Franquisia franquisia = franquisiaRepository.findById(franquisiaId)
                .orElseThrow(() -> new RuntimeException("Franquisia no encontrada"));

        return franquisia.getSucursales().stream()
                .flatMap(sucursal -> sucursal.getTopProduct()
                        .map(product -> new ProductoStockResponse(
                                product.getId(),
                                product.getName(),
                                product.getStock(),
                                sucursal.getId(),
                                sucursal.getName()))
                        .stream())
                .collect(Collectors.toList());
    }
}
