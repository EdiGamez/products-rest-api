package com.edig.productsapi.service;

import com.edig.productsapi.dto.ProductDTO;
import com.edig.productsapi.entity.Category;

import java.util.List;

/**
 * The interface Product service.
 */
public interface IProductService {

    /**
     * Add product.
     *
     * @param productDTO the product dto
     */
    void addProduct(ProductDTO productDTO);

    /**
     * Gets category by name.
     *
     * @param name the Categories name
     * @return the category by name
     */
    Category getCategoryByName(String name);

    /**
     * List all products list.
     *
     * @return the list
     */
    List<ProductDTO> listAllProducts();

    /**
     * List products by category list.
     *
     * @param categoryName the category name
     * @return the list
     */
    List<ProductDTO> listProductsByCategory(String categoryName);


    /**
     * Delete product boolean.
     *
     * @param productName the product name
     * @return the boolean
     */
    boolean deleteProduct(String productName);


    /**
     * Update product boolean.
     *
     * @param productDTO the product dto
     * @return the boolean
     */
    boolean updateProduct(ProductDTO productDTO);

    /**
     * Gets product by name.
     *
     * @param name the product name
     * @return the product by name
     */
    ProductDTO getProductByName(String name);
}
