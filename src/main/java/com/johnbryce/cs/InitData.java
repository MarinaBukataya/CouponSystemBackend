package com.johnbryce.cs;

import java.sql.Date;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.johnbryce.cs.beans.Category;
import com.johnbryce.cs.beans.Company;
import com.johnbryce.cs.beans.Coupon;
import com.johnbryce.cs.beans.Customer;
import com.johnbryce.cs.repo.CompaniesRepository;
import com.johnbryce.cs.repo.CustomersRepository;

@Component
public class InitData implements CommandLineRunner {
	@Autowired
	private CompaniesRepository companiesRepository;
	@Autowired
	private CustomersRepository customersRepository;

	@Override
	public void run(String... args) throws Exception {
		Coupon coupon1 = new Coupon();
		coupon1.setCompanyId(1);
		coupon1.setCategory(Category.FOOD);
		coupon1.setTitle("Free bread");
		coupon1.setDescription("2 free loaves with purchase");
		coupon1.setStartDate(Date.valueOf("2020-11-11"));
		coupon1.setEndDate(Date.valueOf("2021-12-11"));
		coupon1.setAmount(3);
		coupon1.setPrice(19.9);
		coupon1.setImage("jpeg");

		Coupon coupon2 = new Coupon();
		coupon2.setCompanyId(1);
		coupon2.setCategory(Category.FOOD);
		coupon2.setTitle("Gift basket");
		coupon2.setDescription("Food gift basket");
		coupon2.setStartDate(Date.valueOf("2020-10-02"));
		coupon2.setEndDate(Date.valueOf("2021-10-20"));
		coupon2.setAmount(3);
		coupon2.setPrice(55.5);
		coupon2.setImage("jpeg");
		
		Coupon coupon3 = new Coupon();
		coupon3.setCompanyId(1);
		coupon3.setCategory(Category.RESTAURANT);
		coupon3.setTitle("10 % discount");
		coupon3.setDescription("Soft opening promotion");
		coupon3.setStartDate(Date.valueOf("2021-01-01"));
		coupon3.setEndDate(Date.valueOf("2021-01-15"));
		coupon3.setAmount(3);
		coupon3.setPrice(30.7);
		coupon3.setImage("jpeg");

		Company c1 = new Company();
		c1.setName("Roladin");
		c1.setEmail("roladinbakery@gmail.com");
		c1.setPassword("1111");
		c1.getCoupons().add(coupon1);
		c1.getCoupons().add(coupon2);
		c1.getCoupons().add(coupon3);

		Coupon coupon4 = new Coupon();
		coupon4.setCompanyId(2);
		coupon4.setCategory(Category.FOOD);
		coupon4.setTitle("Dairy Month Deal");
		coupon4.setDescription("Any dairy products 50% off");
		coupon4.setStartDate(Date.valueOf("2020-10-10"));
		coupon4.setEndDate(Date.valueOf("2021-11-11"));
		coupon4.setAmount(3);
		coupon4.setPrice(55.5);
		coupon4.setImage("jpeg");
		Company c2 = new Company();
		c2.setName("Tnuva");
		c2.setEmail("tnv@gmail.com");
		c2.setPassword("5781");
		c2.getCoupons().add(coupon4);

		companiesRepository.saveAll(Arrays.asList(c1, c2));
		Customer customer1 = new Customer();
		customer1.setFirstName("John");
		customer1.setLastName("Appleseed");
		customer1.setPassword("8888");
		customer1.setEmail("johnappleseed@apple.com");
		customer1.getCoupons().add(coupon1);
		customer1.getCoupons().add(coupon2);
		customer1.getCoupons().add(coupon3);
		
		customersRepository.save(customer1);
		
		
	}
}
