package com.johnbryce.cs.service.clr;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.johnbryce.cs.beans.Category;
import com.johnbryce.cs.beans.Coupon;
import com.johnbryce.cs.beans.Customer;
import com.johnbryce.cs.exceptions.PurchaseUnavailableException;
import com.johnbryce.cs.service.CustomerService;

//@Component
@Order(3)
public class CustomersServiceTesting implements CommandLineRunner {
	@Autowired
	private CustomerService customerService;

	@Override
	public void run(String... args) throws Exception {
		customerService.setCustomerId(1);

		System.out.println(">> BAD LOGIN <<");
		System.out.println(customerService.login("johnson@gmail.com", "178866"));

		System.out.println(">> VALID LOGIN <<");
		System.out.println(customerService.login("johnappleseed@apple.com", "8888"));
		System.out.println(">> GET ALL CUSTOMER'S COUPONS BEFORE PURCHASE <<");
		System.out.println(customerService.getCustomerCoupons());
		System.out.println(">> SUCCESSFUL COUPON PURCHASE <<");
		Coupon coupon7 = new Coupon();
		coupon7.setCompanyId(4);
		coupon7.setCategory(Category.RESTAURANT);
		coupon7.setTitle("Free second meal");
		coupon7.setDescription("Buy one meal, get second free");
		coupon7.setStartDate(Date.valueOf("2020-11-11"));
		coupon7.setEndDate(Date.valueOf("2020-12-11"));
		coupon7.setAmount(3);
		coupon7.setPrice(99.9);
		coupon7.setImage("jpeg");
		try {
			customerService.purchaseCoupon(coupon7);
		} catch (PurchaseUnavailableException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(">> GET ALL CUSTOMER'S COUPONS AFTER PURCHASE <<");
		System.out.println(customerService.getCustomerCoupons());

		System.out.println(">> UNSUCCESSFUL COUPON PURCHASE <<");

		Coupon coupon2 = new Coupon();
		coupon2.setCompanyId(1);
		coupon2.setCategory(Category.FOOD);
		coupon2.setTitle("Gift basket");
		coupon2.setDescription("Food gift basket");
		coupon2.setStartDate(Date.valueOf("2020-10-02"));
		coupon2.setEndDate(Date.valueOf("2020-11-20"));
		coupon2.setAmount(0);
		coupon2.setPrice(55.5);
		coupon2.setImage("jpeg");
		try {
			customerService.purchaseCoupon(coupon2);
		} catch (PurchaseUnavailableException e) {
			System.out.println(e.getMessage());
		}
		Coupon coupon3 = new Coupon();
		coupon3.setCompanyId(4);
		coupon3.setCategory(Category.RESTAURANT);
		coupon3.setTitle("10 % discount");
		coupon3.setDescription("Soft opening promotion");
		coupon3.setStartDate(Date.valueOf("2020-10-01"));
		coupon3.setEndDate(Date.valueOf("2020-10-15"));
		coupon3.setAmount(3);
		coupon3.setPrice(30.7);
		coupon3.setImage("jpeg");
		try {
			customerService.purchaseCoupon(coupon3);
		} catch (PurchaseUnavailableException e) {
			System.out.println(e.getMessage());
		}

		coupon7.setId(7);
		try {
			customerService.purchaseCoupon(coupon7);
		} catch (PurchaseUnavailableException e) {
			System.out.println(e.getMessage());
		}

		System.out.println(">> GET ALL CUSTOMER'S COUPONS BY CATEGORY <<");
		System.out.println(customerService.getCustomerCoupons(Category.RESTAURANT));

		System.out.println(">> GET ALL COUPONS BELOW MAX PRICE <<");
		System.out.println(customerService.getCustomerCoupons(2999.9));

		System.out.println(">> GET CUSTOMER DETAILS <<");
		System.out.println(customerService.getCustomerDetails());

	}

}
