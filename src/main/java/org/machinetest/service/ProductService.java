package org.machinetest.service;


import org.machinetest.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<Product> getAllProducts(Pageable pageable); // Supports server-side pagination

    Product getProductById(Long id); // Fetch a single product with category details

    Product createProduct(Product product); // Create a product and associate it with a category

    Product updateProduct(Long id, Product product); // Update an existing product

    void deleteProduct(Long id); // Delete a product
}
