package com.ecom.dao;

import com.ecom.model.Category;
import com.ecom.exception.InvalidIdException;

public interface CategoryDao {
    Category getById(int id) throws InvalidIdException;
}
