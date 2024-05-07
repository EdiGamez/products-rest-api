package com.edig.productsapi.repository;

import com.edig.productsapi.entity.Category;
import com.edig.productsapi.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    Optional<List<Product>> findByCategory(Category category);
    Optional<Product> findProductByName(String name);
    @Transactional
    @Modifying
    void deleteProductById(Long id);
}
