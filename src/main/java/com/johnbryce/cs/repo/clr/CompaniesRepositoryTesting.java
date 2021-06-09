package com.johnbryce.cs.repo.clr;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.johnbryce.cs.beans.Category;
import com.johnbryce.cs.beans.Company;
import com.johnbryce.cs.beans.Coupon;
import com.johnbryce.cs.repo.CompaniesRepository;
//@Component
@Order(1)
public class CompaniesRepositoryTesting implements CommandLineRunner {
	@Autowired
	private CompaniesRepository companiesRepository;

	@Override
	public void run(String... args) throws Exception {

		Coupon coupon1 = new Coupon();
		coupon1.setCompanyId(1);
		coupon1.setCategory(Category.FOOD);
		coupon1.setTitle("Free bread");
		coupon1.setDescription("2 free loaves with purchase");
		coupon1.setStartDate(Date.valueOf("2020-11-11"));
		coupon1.setEndDate(Date.valueOf("2020-12-11"));
		coupon1.setAmount(3);
		coupon1.setPrice(19.9);
		coupon1.setImage("jpeg");
		
		Coupon coupon2 = new Coupon();
		coupon2.setCompanyId(1);
		coupon2.setCategory(Category.FOOD);
		coupon2.setTitle("Gift basket");
		coupon2.setDescription("Food gift basket");
		coupon2.setStartDate(Date.valueOf("2020-10-02"));
		coupon2.setEndDate(Date.valueOf("2020-11-20"));
		coupon2.setAmount(3);
		coupon2.setPrice(55.5);
		coupon2.setImage("jpeg");
		Company c1 = new Company();
		c1.setName("Roladin");
		c1.setEmail("roladinbakery@gmail.com");
		c1.setPassword("1111");
		c1.getCoupons().add(coupon1);
		c1.getCoupons().add(coupon2);

		Company c2 = new Company();
		c2.setName("Tnuva");
		c2.setEmail("tnv@gmail.com");
		c2.setPassword("5781");

		Company c3 = new Company();
		c3.setName("Israel Electric Company");
		c3.setEmail("electric@iec.co.il");
		c3.setPassword("3333");
		
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
		Coupon coupon6 = new Coupon();
		coupon6.setCompanyId(4);
		coupon6.setCategory(Category.RESTAURANT);
		coupon6.setTitle("Free dessert");
		coupon6.setDescription("Free dessert lunch deal");
		coupon6.setStartDate(Date.valueOf("2020-10-25"));
		coupon6.setEndDate(Date.valueOf("2020-11-30"));
		coupon6.setAmount(3);
		coupon6.setPrice(45.5);
		coupon6.setImage("jpeg");
		Company c4 = new Company();
		c4.setName("Bicicletta");
		c4.setEmail("bici@gmail.com");
		c4.setPassword("4444");
		c4.getCoupons().add(coupon3);
		c4.getCoupons().add(coupon6);
		
		Coupon coupon4 = new Coupon();
		coupon4.setCompanyId(5);
		coupon4.setCategory(Category.VACATION);
		coupon4.setTitle("70 % discount");
		coupon4.setDescription("Last minute deal");
		coupon4.setStartDate(Date.valueOf("2020-09-25"));
		coupon4.setEndDate(Date.valueOf("2020-09-29"));
		coupon4.setAmount(3);
		coupon4.setPrice(1499.9);
		coupon4.setImage("jpeg");
		Coupon coupon5 = new Coupon();
		coupon5.setCompanyId(5);
		coupon5.setCategory(Category.VACATION);
		coupon5.setTitle("20 % discount");
		coupon5.setDescription("Hot package deal");
		coupon5.setStartDate(Date.valueOf("2020-09-25"));
		coupon5.setEndDate(Date.valueOf("2020-10-10"));
		coupon5.setAmount(3);
		coupon5.setPrice(5999.9);
		coupon5.setImage("jpeg");
		Company c5 = new Company();
		c5.setName("Pomegranate Travel");
		c5.setEmail("pomegranate@walla.com");
		c5.setPassword("5555");
		c5.getCoupons().add(coupon4);
		c5.getCoupons().add(coupon5);
		
		companiesRepository.save(c1);
		companiesRepository.save(c2);
		companiesRepository.save(c3);
		companiesRepository.save(c4);
		companiesRepository.save(c5);
		
		System.out.println(">> ADD COMPANIES <<");
		System.out.println(companiesRepository.findAll());

		System.out.println(">> COMPANY EXISTS <<");
		System.out.println(companiesRepository.ifCompanyExists("roladinbakery@gmail.com", "1111"));

		System.out.println(">> COMPANY DOES NOT EXIST <<");
		System.out.println(companiesRepository.ifCompanyExists("aroma@yahoo.com", "aroma"));

		System.out.println(">> UPDATE COMPANY <<");
		c1 = companiesRepository.getOne(1);
		c1.setEmail("roladin@gmail.com");
		companiesRepository.saveAndFlush(c1);
		System.out.println(companiesRepository.getOne(1));

		System.out.println(">> DELETE COMPANY <<");
		c3 = companiesRepository.getOne(5);
		companiesRepository.delete(c3);

		System.out.println(">> GET ALL COMPANIES <<");
		System.out.println(companiesRepository.findAll());

		System.out.println(">> GET ONE COMPANY <<");
		System.out.println(companiesRepository.getOne(2));

		System.out.println(">> GET COMPANY ID <<");
		System.out.println(companiesRepository.getCompanyId("tnv@gmail.com", "5781"));

	}

}
