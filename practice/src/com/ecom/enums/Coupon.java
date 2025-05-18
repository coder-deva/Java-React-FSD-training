package com.ecom.enums;

public enum Coupon {
	PROMOCODE20(5),
	EXCLUSIVE15MEGADEAL(10),
	FLASHDEAL(15),
	BIRTHDAYBONUS(20);

    private final int discount;

    Coupon(int discount) {
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }
}
