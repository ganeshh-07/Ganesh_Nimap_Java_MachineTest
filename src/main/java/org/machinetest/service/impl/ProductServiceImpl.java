package org.machinetest.service.impl;

import org.machinetest.entity.Product;
import org.machinetest.repository.ProductRepository;
import org.machinetest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Fetch all products with server-side pagination.
     */
    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    /**
     * Fetch a single product by its ID.
     * Includes the associated category details.
     */
    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
    }

    /**
     * Create a new product and associate it with a category.
     */
    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Update an existing product.
     * Allows updating the name, price, and associated category.
     */
    @Override
    public Product updateProduct(Long id, Product product) {
        // Fetch the existing product
        Product existingProduct = getProductById(id);

        // Update fields
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());

        // Save and return the updated product
        return productRepository.save(existingProduct);
    }

    /**
     * Delete a product by its ID.
     */
    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

