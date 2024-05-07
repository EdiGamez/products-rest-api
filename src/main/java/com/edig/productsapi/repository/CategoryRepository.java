package com.edig.productsapi.repository;

import com.edig.productsapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    /**
     * Find by name category.
     *
     * @param name the name
     * @return the category
     */
    Category findCategoryByName(String name);
}
