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
        if (product.getPrice() <= 0) {
        	
            throw new InvalidInputException("Price must be greater than zero");
        }
        
        Category category = categoryDao.getById(categoryId);
        product.setCategory(category);
        productDao.insert(product);
    }
    
    

    public List<Product> getByCategoryId(int categoryId) throws InvalidIdException {
        return productDao.getByCategoryId(categoryId);
    }
}
