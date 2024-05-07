package com.edig.productsapi.service;

import com.edig.productsapi.dto.ProductDTO;
import com.edig.productsapi.entity.Category;
import com.edig.productsapi.entity.Product;
import com.edig.productsapi.exceptions.CategoryNotExistsException;
import com.edig.productsapi.exceptions.ProductAlreadyExistsException;
import com.edig.productsapi.exceptions.ProductNotFoundException;
import com.edig.productsapi.mapper.ProductMapper;
import com.edig.productsapi.repository.CategoryRepository;
import com.edig.productsapi.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService implements IProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    /**
     * Add product.
     *
     * @param productDTO the product dto
     */
    @Override
    public void addProduct(ProductDTO productDTO) {
        Product product = ProductMapper.toProduct(productDTO);
        Optional<Product> productOptional = productRepository.findProductByName(product.getName());
        if (productOptional.isPresent()) {
            throw new ProductAlreadyExistsException("Product with name " + product.getName() + " already exists");
        } else {
            productRepository.save(product);
        }

    }

    /**
     * Gets category by name.
     *
     * @param name the Categories name
     * @return the category by name
     */
    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }

    /**
     * List all products list.
     *
     * @return the list
     */
    @Override
    public List<ProductDTO> listAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
            productDTOs.add(productMapper.toProductDTO(product));
        }
        return productDTOs;
    }

    /**
     * List products by category list.
     *
     * @param categoryName the category name
     * @return the list
     */
    @Override
    public List<ProductDTO> listProductsByCategory(String categoryName) {
        Category category = categoryRepository.findCategoryByName(categoryName);
        List<Product> products = productRepository.findByCategory(category).orElseThrow(
                () -> new CategoryNotExistsException("Category with name " + categoryName + " not found")
        );
        if(products.isEmpty()){
            throw new ProductNotFoundException("No products found for category " + categoryName);
        }
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
            productDTOs.add(productMapper.toProductDTO(product));
        }
        return productDTOs;
    }


    /**
     * Delete product boolean.
     *
     * @param productName the product name
     * @return the boolean
     */
    @Override
    public boolean deleteProduct(String productName) {
        Product product = getProduct(productName);
        productRepository.deleteProductById(product.getId());
        return true;
    }

    private Product getProduct(String productName) {
        Product product = productRepository.findProductByName(productName).orElseThrow(
                () -> new ProductNotFoundException("Product with name " + productName + " not found")
        );
        return product;
    }

    /**
     * Update product.
     *
     * @param productDTO the product dto
     */
    @Override
    public boolean updateProduct(ProductDTO productDTO) {
        Product existingProduct = getProduct(productDTO.getName());

        existingProduct.setName(productDTO.getName());
        existingProduct.setBrand(productDTO.getBrand());
        existingProduct.setMadeIn(productDTO.getMadeIn());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setCategory(categoryRepository.findCategoryByName(productDTO.getCategoryName()));

        productRepository.save(existingProduct);

        return true;
    }

    /**
     * Gets product by name.
     *
     * @param name the product name
     * @return the product by name
     */
    @Override
    public ProductDTO getProductByName(String name) {
        Product product = getProduct(name);
        return productMapper.toProductDTO(product);
    }

}
