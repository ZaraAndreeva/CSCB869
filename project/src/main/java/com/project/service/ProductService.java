package com.project.service;

import com.project.dao.ProductDAO;
import com.project.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDAO productDAO;

    public List<Product> getAllProducts(){
        return productDAO.findAll();
    }

    public List<Product> getProductsByCategory(String category){
        return productDAO.findAllByCategory(category);
    }

    public Product getProductsById(int id){
        return productDAO.findProductById(id);
    }


    public List<Product> getProductsByType(String type){
        return productDAO.findAllByType(type);
    }

    public List<Product> getProductsByCategoryAndType(String category, String type){
        return productDAO.findAllByCategoryAndType(category, type);
    }

    public List<String> getAllCategories(){
        return productDAO.findAllCategories();
    }

    public List<String> getAllTypes(){
        return productDAO.findAllTypes();
    }

    public Product addProduct(Product product){
        return productDAO.save(product);
    }

    public void deleteProduct(int id){
        productDAO.deleteById(id);
    }

}
