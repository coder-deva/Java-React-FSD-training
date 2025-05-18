package com.ecom.dao.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


import com.ecom.dao.CustomerDao;
import com.ecom.dao.ProductDao;
 import com.ecom.dao.PurchaseDao;

import com.ecom.dao.impl.CustomerDaoImpl;
import com.ecom.dao.impl.ProductDaoImpl;

import com.ecom.dao.impl.PurchaseDaoImpl;
import com.ecom.enums.Coupon;
import com.ecom.exception.InvalidIdException;
import com.ecom.model.Customer;
import com.ecom.model.Product;

import com.ecom.model.Purchase;

public class PurchaseService {
	
	
    private PurchaseDao purchaseDao = new PurchaseDaoImpl();
    private CustomerDao customerDao = new CustomerDaoImpl();
    private ProductDao productDao = new ProductDaoImpl();

    public void insert(int customerId, int productId, Scanner scanner) throws InvalidIdException {
    	
        Customer customer = customerDao.getById(customerId);
        
        Product product = productDao.getById(productId);

        System.out.print("Enter coupon code or press Enter for none: ");
        String couponInput = scanner.nextLine().toUpperCase().trim();

        Coupon coupon = null;
        if (!couponInput.isEmpty()) {	
            try {
                coupon = Coupon.valueOf(couponInput);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid coupon code!");
            }
        }

        double discountPercent = coupon == null ? 0 : coupon.getDiscount();
        double discountAmount = product.getPrice() * discountPercent / 100;
        double amountToPay = product.getPrice() - discountAmount;

        Purchase purchase = new Purchase();
        purchase.setCustomer(customer);
        purchase.setProduct(product);
        purchase.setCoupon(coupon);
        purchase.setAmountPaid(amountToPay);

     
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        purchase.setDateOfPurchase(currentDate);

        purchaseDao.insert(purchase);
    }
}
