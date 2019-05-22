package com.project.controller;

import com.project.entity.Product;
import com.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/products")
    public @ResponseBody
    List<Product> getAllProducts(@RequestParam(required = false, name = "category") String category, @RequestParam(required = false, name = "type") String type) {
        List<Product> products;
        if (category != null) {
            if (type != null) {
                products = (productService.getProductsByCategoryAndType(category, type));
            } else {
                products = productService.getProductsByCategory(category);
            }
        } else {
            if (type != null) {
                products = productService.getProductsByType(type);
            } else {
                products = productService.getAllProducts();
            }
        }
        return products;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/add")
    public @ResponseBody Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(value = "/modify")
    public @ResponseBody Product modifyProduct(@RequestBody() Product product) {
        Product productToModify = productService.getProductsById(product.getId());
        if (product.getName() != null) {
            productToModify.setName(product.getName());
        }
        if (!product.getExpirationDate().equals(productToModify.getExpirationDate())) {
            productToModify.setExpirationDate(product.getExpirationDate());
        }
        if (!product.getDescription().equals(productToModify.getDescription())) {
            productToModify.setDescription(product.getDescription());
        }
        if (!product.getPrice().equals(productToModify.getPrice())) {
            productToModify.setPrice(product.getPrice());
        }
        if (!product.getCompany().equals(productToModify.getCompany())) {
            productToModify.setCompany(product.getCompany());
        }
        if (!product.getCategory().equals(productToModify.getCategory())) {
            productToModify.setCategory(product.getCategory());
        }
        if (!product.getType().equals(productToModify.getType())) {
            productToModify.setType(product.getType());
        }

        productToModify = productService.addProduct(productToModify);
        return productToModify;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(value = "/delete")
    public @ResponseBody String deleteProduct(@RequestParam(name = "id") int id) {
        productService.deleteProduct(id);
        return null;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/categories")
    public @ResponseBody
    List<String> getAllCategories(HttpServletRequest request) {
        return productService.getAllCategories();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/types")
    public @ResponseBody
    List<String> getAllTypes(HttpServletRequest request) {
        return productService.getAllTypes();
    }

}
