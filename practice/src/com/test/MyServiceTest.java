package com.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Scanner;
import com.ecom.exception.InvalidIdException;
import com.ecom.exception.InvalidInputException;
import com.ecom.model.Product;
import com.ecom.dao.service.ProductService;
import com.ecom.dao.service.PurchaseService;

public class MyServiceTest {
	
	private ProductService productService = new ProductService();
	PurchaseService purchaseService = new PurchaseService();

	/* Product checking */
	
	// validate insert
	
    @Test
    public void insertProductSuccess() {
        
        Product product = new Product();
        product.setTitle("Test Product");
        product.setPrice(99.99);
        product.setDescription("A test product");

        int validCategoryId = 1; // valid categotry id is 1 and 2

       
        assertDoesNotThrow(() -> productService.insertProduct(product, validCategoryId));
    }
	
    //when title is empty
    @Test
    public void insertProductEmptyTitle() {
        Product product = new Product();
        product.setTitle(""); // Invalid
        product.setPrice(50.0);
        product.setDescription("No title");
        
        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> 
            productService.insertProduct(product, 1)
        );
        assertEquals("Product name cannot be empty", exception.getMessage());
    }
    
    //invalid price
    @Test
    public void insertProductInvalidPrice() {
        Product product = new Product();
        product.setTitle("Invalid Price");
        product.setPrice(-1.0); // Invalid
        product.setDescription("Price is zero");

        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> 
            productService.insertProduct(product, 1)
        );
        assertEquals("Price must be greater than zero", exception.getMessage());
    }
    
    // invalid category id
    @Test
    public void insertProductInvalidCategoryId() {
        Product product = new Product();
        product.setTitle("Valid Product");
        product.setPrice(50.0);
        product.setDescription("Valid description");

        int invalidCategoryId = 9999; 

        assertThrows(InvalidIdException.class, () -> 
            productService.insertProduct(product, invalidCategoryId)
        );
    }
    
    
    // case 2 wee will check for the fetchcategorybyID
    
    @Test
    public void getByCategoryId() {
        int validCategoryId = 1; 

        try {
            List<Product> products = productService.getByCategoryId(validCategoryId);
            assertNotNull(products);
            assertFalse(products.isEmpty(), "Products list should not be empty");

            for (Product p : products) {
                System.out.println(p.getId() + "\t" + p.getTitle() + "\t" + p.getPrice() +
                        "\t" + p.getDescription() + "\t" + p.getCategory().getId());
            }

        } catch (InvalidIdException e) {
            fail("Should not throw InvalidIdException for valid category ID");
        }
    }

    

    //  invalid category ID
    @Test
    public void getByInvalidCategoryId() {
        int invalidCategoryId = -1;

        InvalidIdException exception = assertThrows(InvalidIdException.class, () ->
                productService.getByCategoryId(invalidCategoryId)
        );

        assertTrue(exception.getMessage().contains("No products found") ||
                   exception.getMessage().contains("invalid"));
    }
    
    // now we test for purchase
    
    //invalid customerId
    @Test
    public void invalidCustomerId() {
        int invalidCustomerId = 9999;  
        int validProductId = 101;
        String input = "NONE\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        Exception exception = assertThrows(InvalidIdException.class, () -> {
            purchaseService.Purchase(invalidCustomerId, validProductId, scanner);
        });

        assertTrue(exception.getMessage().contains("Customer ID is invalid"));
    }
    
    // invalid ProductId
    
    @Test
    public void invalidProductId() {
        int validCustomerId = 1;
        int invalidProductId = 9999;  
        String input = "NONE\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        Exception exception = assertThrows(InvalidIdException.class, () -> {
            purchaseService.Purchase(validCustomerId, invalidProductId, scanner);
        });

        assertTrue(exception.getMessage().contains("Product ID is invalid"));
    }
    
    // valid coupon
    
    @Test
    public void validCoupon() {
        int validCustomerId = 1;
        int validProductId = 2;

        
        String input = "BIRTHDAYBONUS\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        assertDoesNotThrow(() -> {
            purchaseService.Purchase(validCustomerId, validProductId, scanner);
        });
    }
}
    






    