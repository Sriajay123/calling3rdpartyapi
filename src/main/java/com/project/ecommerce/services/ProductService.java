package com.project.ecommerce.services;

import com.project.ecommerce.models.Product;

import java.util.List;

public interface ProductService {

    public Product getProductById(long id);

    public List<Product> getProducts();
}
