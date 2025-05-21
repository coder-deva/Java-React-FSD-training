package com.ecom.dao.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
	
	
   
    private CustomerDao customerDao = new CustomerDaoImpl();
    private ProductDao productDao = new ProductDaoImpl();
    private PurchaseDao purchaseDao = new PurchaseDaoImpl();
    
    public void Purchase(int customerId, int productId, Scanner scanner) throws InvalidIdException {
        // Fetch customer and product by ID
        Customer customer = customerDao.getById(customerId);
        Product product = productDao.getById(productId);
        
        if (customer == null || product == null) {
            throw new InvalidIdException("ID given is Invalid");
        }

        Purchase purchase = new Purchase();
        purchase.setCustomer(customer);
        purchase.setProduct(product);
        
        // Handle coupon code
        System.out.print("Do you have a coupon?(Y/N) ");
        String ans = scanner.next();

        if (ans.equals("Y")) {
            System.out.println("Enter the code ");
            String couponCode = scanner.next().toUpperCase();

            try {
                Coupon coupon = Coupon.valueOf(couponCode);  // Get the coupon by code
                double discount = coupon.getDiscount();
                System.out.println("Discount = " + discount);
                double discountedFee = product.getPrice() - (product.getPrice() * (discount / 100));
                System.out.println("After Discount, Fee is " + discountedFee);
                purchase.setCoupon(coupon);
                purchase.setAmountPaid(discountedFee);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid coupon code!");
            }
        } else {
            System.out.println("No Coupon applied.....");
            purchase.setAmountPaid(product.getPrice());
        }

        // Set the date of purchase
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        purchase.setDateOfPurchase(currentDate);

        // Save the purchase details to the database
        purchaseDao.insert(purchase);
    }

        
//        String couponInput = scanner.nextLine().toUpperCase().trim();
//
//        Coupon coupon = null;
//        if (!couponInput.isEmpty()) {	
//            try {
//                coupon = Coupon.valueOf(couponInput);
//            } catch (IllegalArgumentException e) {
//                throw new IllegalArgumentException("Invalid coupon code!");
//            }
//        }
//
//        double discountPercent = coupon == null ? 0 : coupon.getDiscount();
//        double discountAmount = product.getPrice() * discountPercent / 100;
//        double amountToPay = product.getPrice() - discountAmount;
//
//        Purchase purchase = new Purchase();
//        purchase.setCustomer(customer);
//        purchase.setProduct(product);
//        purchase.setCoupon(coupon);
//        purchase.setAmountPaid(amountToPay);
//
//     
//        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//        purchase.setDateOfPurchase(currentDate);
//
//        purchaseDao.insert(purchase);
    
}
