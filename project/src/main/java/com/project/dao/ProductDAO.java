package com.project.dao;

import com.project.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDAO extends CrudRepository<Product, Integer> {

    List<Product> findAll();

    @Query(value = "SELECT * FROM products WHERE category=:category", nativeQuery = true)
    List<Product> findAllByCategory(@Param("category") String category);

    @Query(value = "SELECT * FROM products WHERE id=:id", nativeQuery = true)
    Product findProductById(@Param("id") int id);

    @Query(value = "SELECT * FROM products WHERE type=:type", nativeQuery = true)
    List<Product> findAllByType(@Param("type") String type);

    @Query(value = "SELECT * FROM products WHERE category=:category and type=:type", nativeQuery = true)
    List<Product> findAllByCategoryAndType(@Param("category") String category, @Param("type") String type);

    @Query(value = "SELECT DISTINCT category FROM products", nativeQuery = true)
    List<String> findAllCategories();

    @Query(value = "SELECT DISTINCT type FROM products", nativeQuery = true)
    List<String> findAllTypes();

    Product save(Product product);

    void deleteById(@Param("id") int id);
}
