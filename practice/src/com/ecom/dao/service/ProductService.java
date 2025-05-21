package com.ecom.dao.service;

import java.util.List;

import com.ecom.dao.CategoryDao;
import com.ecom.dao.ProductDao;
import com.ecom.dao.impl.CategoryDaoImpl;
import com.ecom.dao.impl.ProductDaoImpl;
import com.ecom.exception.InvalidIdException;
import com.ecom.exception.InvalidInputException;
import com.ecom.model.Category;
import com.ecom.model.Product;

public class ProductService {
    private ProductDao productDao = new ProductDaoImpl();
    private CategoryDao categoryDao = new CategoryDaoImpl();

    public void insertProduct(Product product, int categoryId) throws InvalidIdException, InvalidInputException {
        if (product.getTitle().isEmpty()) {
            throw new InvalidInputException("Product name cannot be empty");
        }

        if (product.getPrice() <= 0) {
            throw new InvalidInputException("Price must be greater than zero");
        }

        Category category = categoryDao.getById(categoryId);  // Fetch category by ID
        if (category == null) {
            throw new InvalidIdException("id given is invalid");  // Change the message to match the test case
        }

        product.setCategory(category);
        productDao.insert(product);  // Insert product into the database
    }

    
    

    public List<Product> getByCategoryId(int categoryId) throws InvalidIdException {
        List<Product> products = productDao.getByCategoryId(categoryId);  

        if (products.isEmpty()) {
            throw new InvalidIdException("Invalid Category ID : No products found");
        }

        return products;
    }

}
