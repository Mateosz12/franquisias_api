package com.franquisias.model;

import lombok.Data;
import java.util.UUID;

@Data
public class Product {
    private String id = UUID.randomUUID().toString();
    private String name;
    private Integer stock;
}
