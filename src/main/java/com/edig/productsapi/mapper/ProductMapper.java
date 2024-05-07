package com.edig.productsapi.mapper;

import com.edig.productsapi.dto.ProductDTO;
import com.edig.productsapi.entity.Product;
import com.edig.productsapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public static CategoryRepository repository;

    @Autowired
    public ProductMapper(CategoryRepository repository) {
        this.repository = repository;
    }

    public static ProductDTO toProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setBrand(product.getBrand());
        productDTO.setMadeIn(product.getMadeIn());
        productDTO.setPrice(product.getPrice());
        productDTO.setCategoryName(product.getCategory().getName());

        return productDTO;
    }

    public static Product toProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setBrand(productDTO.getBrand());
        product.setMadeIn(productDTO.getMadeIn());
        product.setPrice(productDTO.getPrice());
        product.setCategory(repository.findCategoryByName(productDTO.getCategoryName()));
        return product;
    }

}
