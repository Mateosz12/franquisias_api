package com.franquisias.dto;

public record ProductoStockResponse(
        String productId,
        String productName,
        Integer stock,
        String sucursalId,
        String sucursalName
) {
}
