package com.johnbryce.cs.service.clr;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.johnbryce.cs.beans.Category;
import com.johnbryce.cs.beans.Coupon;
import com.johnbryce.cs.exceptions.RestrictedEntityDetailsChangeException;
import com.johnbryce.cs.service.CompanyService;
//@Component
@Order(2)
public class CompanyServiceTesting implements CommandLineRunner {

	@Autowired
	private CompanyService companyService;

	@Override
	public void run(String... args) throws Exception {
		System.out.println(">> BAD LOGIN <<");
		System.out.println(companyService.login("johnsom@gmail.com", "178866"));

		System.out.println(">> VALID LOGIN <<");
		System.out.println(companyService.login("tnv@gmail.com", "5781"));

		companyService.setCompanyId(4);

		System.out.println(">> ADDING COUPON WITH EXISTING TITLE: NOT WORKING <<");

		Coupon coupon1 = new Coupon();
		coupon1.setCompanyId(4);
		coupon1.setCategory(Category.RESTAURANT);
		coupon1.setTitle("Free dessert");
		coupon1.setDescription("Free dessert lunch deal");
		coupon1.setStartDate(Date.valueOf("2020-10-25"));
		coupon1.setEndDate(Date.valueOf("2020-11-30"));
		coupon1.setAmount(3);
		coupon1.setPrice(45.5);
		coupon1.setImage("jpeg");
		try {
			companyService.addCoupon(coupon1);
		} catch (RestrictedEntityDetailsChangeException e) {
			System.out.println(e.getMessage());
		}

		System.out.println(">> ADDING COUPON WITH NEW TITLE: WORKING <<");
		Coupon coupon2 = new Coupon();
		coupon2.setCompanyId(4);
		coupon2.setCategory(Category.RESTAURANT);
		coupon2.setTitle("Free second meal");
		coupon2.setDescription("Buy one meal, get second free");
		coupon2.setStartDate(Date.valueOf("2020-11-11"));
		coupon2.setEndDate(Date.valueOf("2020-12-11"));
		coupon2.setAmount(3);
		coupon2.setPrice(99.9);
		coupon2.setImage("jpeg");

		try {
			companyService.addCoupon(coupon2);
		} catch (RestrictedEntityDetailsChangeException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(companyService.getCompanyCoupons());
		System.out.println(">> UPDATING COUPON ID: NOT WORKING <<");
		coupon2.setId(9);
		try {
			companyService.updateCoupon(coupon2);
		} catch (RestrictedEntityDetailsChangeException e) {
			System.out.println(e.getMessage());
		}

		System.out.println(">> UPDATING COMPANY ID: NOT WORKING <<");
		coupon2.setId(7);
		coupon2.setCompanyId(3);
		try {
			companyService.updateCoupon(coupon2);
		} catch (RestrictedEntityDetailsChangeException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(">> UPDATING COUPON: WORKING <<");
		coupon2.setCompanyId(4);
		coupon2.setPrice(33.5);
		try {
			companyService.updateCoupon(coupon2);
			System.out.println(companyService.getCompanyCoupons());
		} catch (RestrictedEntityDetailsChangeException e) {
			System.out.println(e.getMessage());
		}

		System.out.println(">> DELETING COUPON AND CVC RELATED COUPONS << ");
		companyService.deleteCoupon(0);
		System.out.println(companyService.getCompanyCoupons());

		System.out.println(">> GET ALL COUPONS BY CATEGORY <<");
		System.out.println(companyService.getCompanyCoupons(Category.RESTAURANT));

		System.out.println(">> GET ALL COUPONS BELOW MAX PRICE <<");
		System.out.println(companyService.getCompanyCoupons(149.9));

		System.out.println(">> GET COMPANY DETAILS <<");
		System.out.println(companyService.getCompanyDetails());
	}
}
