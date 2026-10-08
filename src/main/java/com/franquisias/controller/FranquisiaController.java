package com.franquisias.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.franquisias.dto.FranquisiaResponse;
import com.franquisias.dto.ProductoStockResponse;
import com.franquisias.model.Franquisia;
import com.franquisias.model.Product;
import com.franquisias.model.Sucursal;
import com.franquisias.service.FranquisiaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/franquicias")
@Tag(name = "Franquicias", description = "API para gestionar franquicias, sucursales y productos")
public class FranquisiaController {

    private final FranquisiaService franquisiaService;

    public FranquisiaController(FranquisiaService franquisiaService) {
        this.franquisiaService = franquisiaService;
    }

    @Operation(summary = "Listar franquicias", description = "Devuelve un resumen de todas las franquicias con la cantidad de sucursales.")
    @GetMapping
    public ResponseEntity<List<FranquisiaResponse>> getAllFranquicias() {
        return ResponseEntity.ok(franquisiaService.getAllFranquicias());
    }

    @Operation(summary = "Obtener franquicia por ID", description = "Devuelve una franquicia completa con sus sucursales y productos.")
    @GetMapping("/{id}")
    public ResponseEntity<Franquisia> getFranquisiaById(@PathVariable String id) {
        return ResponseEntity.ok(franquisiaService.getFranquisiaById(id));
    }

    @Operation(summary = "Crear franquicia", description = "Crea una nueva franquicia con el nombre proporcionado.")
    @PostMapping
    public ResponseEntity<Franquisia> createFranquisia(@RequestBody Franquisia franquisia) {
        return ResponseEntity.ok(franquisiaService.createFranquisia(franquisia));
    }

    @Operation(summary = "Agregar sucursal", description = "Agrega una nueva sucursal a la franquicia indicada.")
    @PostMapping("/{id}/sucursales")
    public ResponseEntity<Franquisia> addSucursal(
            @PathVariable String id,
            @RequestBody Sucursal sucursal) {
        return ResponseEntity.ok(franquisiaService.addSucursalToFranquisia(id, sucursal));
    }

    @Operation(summary = "Agregar producto", description = "Agrega un nuevo producto con stock a una sucursal de una franquicia.")
    @PostMapping("/{id}/sucursales/{sucursalId}/productos")
    public ResponseEntity<Franquisia> addProduct(
            @PathVariable String id,
            @PathVariable String sucursalId,
            @RequestBody Product product) {
        return ResponseEntity.ok(franquisiaService.addProductToSucursal(id, sucursalId, product));
    }

    @Operation(summary = "Eliminar producto", description = "Elimina un producto de una sucursal de una franquicia.")
    @DeleteMapping("/{id}/sucursales/{sucursalId}/productos/{productId}")
    public ResponseEntity<Franquisia> removeProduct(
            @PathVariable String id,
            @PathVariable String sucursalId,
            @PathVariable String productId) {
        return ResponseEntity.ok(franquisiaService.removeProductFromSucursal(id, sucursalId, productId));
    }

    @Operation(summary = "Actualizar nombre de franquicia", description = "Modifica el nombre de una franquicia existente.")
    @PatchMapping("/{id}/nombre")
    public ResponseEntity<Franquisia> updateFranquisiaName(
            @PathVariable String id,
            @RequestParam String nombre) {
        return ResponseEntity.ok(franquisiaService.updateFranquisiaName(id, nombre));
    }

    @Operation(summary = "Actualizar nombre de sucursal", description = "Modifica el nombre de una sucursal dentro de una franquicia.")
    @PatchMapping("/{id}/sucursales/{sucursalId}/nombre")
    public ResponseEntity<Franquisia> updateSucursalName(
            @PathVariable String id,
            @PathVariable String sucursalId,
            @RequestParam String nombre) {
        return ResponseEntity.ok(franquisiaService.updateSucursalName(id, sucursalId, nombre));
    }

    @Operation(summary = "Actualizar nombre de producto", description = "Modifica el nombre de un producto dentro de una sucursal.")
    @PatchMapping("/{id}/sucursales/{sucursalId}/productos/{productId}/nombre")
    public ResponseEntity<Franquisia> updateProductName(
            @PathVariable String id,
            @PathVariable String sucursalId,
            @PathVariable String productId,
            @RequestParam String nombre) {
        return ResponseEntity.ok(franquisiaService.updateProductName(id, sucursalId, productId, nombre));
    }

    @Operation(summary = "Actualizar stock de producto", description = "Modifica la cantidad de stock de un producto en una sucursal.")
    @PatchMapping("/{id}/sucursales/{sucursalId}/productos/{productId}/stock")
    public ResponseEntity<Franquisia> updateProductStock(
            @PathVariable String id,
            @PathVariable String sucursalId,
            @PathVariable String productId,
            @RequestParam Integer stock) {
        return ResponseEntity.ok(franquisiaService.updateProductStock(id, sucursalId, productId, stock));
    }

    @Operation(summary = "Producto con más stock por sucursal", description = "Devuelve el producto con mayor stock de cada sucursal de una franquicia, indicando a qué sucursal pertenece.")
    @GetMapping("/{id}/productos/mayor-stock")
    public ResponseEntity<List<ProductoStockResponse>> getTopStockProducts(
            @PathVariable String id) {
        return ResponseEntity.ok(franquisiaService.getTopStockProductsByFranquisia(id));
    }
}
