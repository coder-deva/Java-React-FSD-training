package com.ecom.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecom.dao.CustomerDao;
import com.ecom.exception.InvalidIdException;
import com.ecom.model.Customer;
import com.ecom.utility.DBUtility;

public class CustomerDaoImpl implements CustomerDao {
	DBUtility db = DBUtility.getInstance();

    @Override
    public Customer getById(int id) throws InvalidIdException {
    	
        Connection con = db.connect();
        String sql = "SELECT * FROM customer WHERE id = ?";
        
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setCity(rs.getString("city"));
                return customer;
            }
        } catch (SQLException e) {
        	
            System.out.println(e.getMessage());
        } finally {
        	
            db.close();
        }
        
        throw new InvalidIdException("Customer ID is invalid: " + id);
    }
}
