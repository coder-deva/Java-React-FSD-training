package com.ecom.dao;

import java.util.List;

import com.ecom.model.Product;
import com.ecom.exception.InvalidIdException;

public interface ProductDao {
    void insert(Product product);
    List<Product> getByCategoryId(int categoryId) throws InvalidIdException;
    Product getById(int id) throws InvalidIdException;
}
