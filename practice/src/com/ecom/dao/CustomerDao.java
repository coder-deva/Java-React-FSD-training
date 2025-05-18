package com.ecom.dao;

import com.ecom.model.Customer;
import com.ecom.exception.InvalidIdException;

public interface CustomerDao {
    Customer getById(int id) throws InvalidIdException;
}
