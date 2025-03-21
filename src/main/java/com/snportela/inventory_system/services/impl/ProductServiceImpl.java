package com.snportela.inventory_system.services.impl;

import com.snportela.inventory_system.domain.entities.Product;
import com.snportela.inventory_system.exceptions.NotFoundException;
import com.snportela.inventory_system.repositories.ProductRepository;
import com.snportela.inventory_system.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findOne(UUID productId) {
        return productRepository.findById(productId).orElseThrow(NotFoundException::new);
    }

    @Override
    public Product update(UUID productId, Product product) {
        Product existingProduct = productRepository.findById(productId).orElseThrow(NotFoundException::new);

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setProductCategory(product.getProductCategory());
        existingProduct.setSupplier(product.getSupplier());

        return productRepository.save(existingProduct);
    }

    @Override
    public void delete(UUID productId) {
        productRepository.findById(productId).orElseThrow(NotFoundException::new);
        productRepository.deleteById(productId);
    }
}
