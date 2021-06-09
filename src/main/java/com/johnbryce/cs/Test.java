package com.johnbryce.cs;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.johnbryce.cs.beans.Category;
import com.johnbryce.cs.beans.Company;
import com.johnbryce.cs.beans.Coupon;
import com.johnbryce.cs.beans.Customer;
import com.johnbryce.cs.exceptions.PurchaseUnavailableException;
import com.johnbryce.cs.exceptions.RestrictedEntityDetailsChangeException;
import com.johnbryce.cs.repo.CouponsRepository;
import com.johnbryce.cs.security.ClientType;
import com.johnbryce.cs.security.LoginManager;
import com.johnbryce.cs.service.AdminService;
import com.johnbryce.cs.service.CompanyService;
import com.johnbryce.cs.service.CustomerService;

//@Component
public class Test implements CommandLineRunner {

	@Autowired
	private LoginManager loginManager;
	@Autowired
	private AdminService adminService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CustomerService customerService;

	@Override
	public void run(String... args) throws Exception {

//		System.out.println(">> ADMIN VALID LOGIN <<");
//		adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
//
//		Coupon coupon1 = new Coupon();
//		coupon1.setCompanyId(1);
//		coupon1.setCategory(Category.FOOD);
//		coupon1.setTitle("Free bread");
//		coupon1.setDescription("2 free loaves with purchase");
//		coupon1.setStartDate(Date.valueOf("2020-11-11"));
//		coupon1.setEndDate(Date.valueOf("2020-12-11"));
//		coupon1.setAmount(3);
//		coupon1.setPrice(19.9);
//		coupon1.setImage("jpeg");
//
//		Coupon coupon2 = new Coupon();
//		coupon2.setCompanyId(1);
//		coupon2.setCategory(Category.FOOD);
//		coupon2.setTitle("Gift basket");
//		coupon2.setDescription("Food gift basket");
//		coupon2.setStartDate(Date.valueOf("2020-10-02"));
//		coupon2.setEndDate(Date.valueOf("2020-10-20"));
//		coupon2.setAmount(3);
//		coupon2.setPrice(55.5);
//		coupon2.setImage("jpeg");
//
//		Company c1 = new Company();
//		c1.setName("Roladin");
//		c1.setEmail("roladinbakery@gmail.com");
//		c1.setPassword("1111");
//		c1.getCoupons().add(coupon1);
//		c1.getCoupons().add(coupon2);
//
//		Coupon coupon3 = new Coupon();
//		coupon3.setCompanyId(2);
//		coupon3.setCategory(Category.FOOD);
//		coupon3.setTitle("Dairy Month Deal");
//		coupon3.setDescription("Any dairy products 50% off");
//		coupon3.setStartDate(Date.valueOf("2020-10-10"));
//		coupon3.setEndDate(Date.valueOf("2020-11-11"));
//		coupon3.setAmount(3);
//		coupon3.setPrice(55.5);
//		coupon3.setImage("jpeg");
//		Company c2 = new Company();
//		c2.setName("Tnuva");
//		c2.setEmail("tnv@gmail.com");
//		c2.setPassword("5781");
//		c2.getCoupons().add(coupon3);
//
//		try {
//			adminService.addCompany(c1);
//			adminService.addCompany(c2);
//		} catch (RestrictedEntityDetailsChangeException e) {
//			System.out.println(e.getMessage());
//		}
//
//		System.out.println(">> ADDING COMPANY WITH THE SAME EMAIL: UNSUCCESSFUL <<");
//		Company c = new Company();
//		c.setName("Teva");
//		c.setEmail("tnv@gmail.com");
//		c.setPassword("4545");
//		try {
//			adminService.addCompany(c);
//		} catch (RestrictedEntityDetailsChangeException e) {
//			System.out.println(e.getMessage());
//		}
//
//		System.out.println(">> ADDING COMPANY: SUCCESSFUL <<");
//
//		Coupon coupon4 = new Coupon();
//		coupon4.setCompanyId(3);
//		coupon4.setCategory(Category.RESTAURANT);
//		coupon4.setTitle("Free dessert");
//		coupon4.setDescription("Free dessert lunch deal");
//		coupon4.setStartDate(Date.valueOf("2020-10-25"));
//		coupon4.setEndDate(Date.valueOf("2020-10-30"));
//		coupon4.setAmount(3);
//		coupon4.setPrice(45.5);
//		coupon4.setImage("jpeg");
//
//		Coupon coupon5 = new Coupon();
//		coupon5.setCompanyId(3);
//		coupon5.setCategory(Category.RESTAURANT);
//		coupon5.setTitle("Free breakfast");
//		coupon5.setDescription("5th breakfast on house");
//		coupon5.setStartDate(Date.valueOf("2021-01-01"));
//		coupon5.setEndDate(Date.valueOf("2021-12-31"));
//		coupon5.setAmount(3);
//		coupon5.setPrice(45.5);
//		coupon5.setImage("jpeg");
//
//		Company c3 = new Company();
//		c3.setName("Bicicletta");
//		c3.setEmail("bici@gmail.com");
//		c3.setPassword("4444");
//		c3.getCoupons().add(coupon4);
//		c3.getCoupons().add(coupon5);
//		try {
//			adminService.addCompany(c3);
//			System.out.println(adminService.getAllCompanies());
//		} catch (RestrictedEntityDetailsChangeException e) {
//			System.out.println(e.getMessage());
//		}
//		System.out.println(">> UPDATING COMPANY ID: UNSUCCESSFUL <<");
//		c3.setId(5);
//		try {
//			adminService.updateCompany(c3);
//		} catch (RestrictedEntityDetailsChangeException e) {
//			System.out.println(e.getMessage());
//		}
//
//		System.out.println(">> UPDATING COMPANY NAME: UNSUCCESSFUL <<");
//		c3.setId(3);
//		c3.setName("FOX");
//		try {
//			adminService.updateCompany(c3);
//		} catch (RestrictedEntityDetailsChangeException e) {
//			System.out.println(e.getMessage());
//		}
//
//		System.out.println(">> SUCCESSFULLY UPDATING COMPANY (PASSWORD) <<");
//		c1.setPassword("pastry");
//		try {
//			adminService.updateCompany(c1);
//		} catch (RestrictedEntityDetailsChangeException e) {
//			System.out.println(e.getMessage());
//		}
//		System.out.println(adminService.getOneCompany(1));
//
//		Customer customer1 = new Customer();
//		customer1.setFirstName("John");
//		customer1.setLastName("Appleseed");
//		customer1.setPassword("8888");
//		customer1.setEmail("johnappleseed@apple.com");
//		customer1.getCoupons().add(coupon1);
//		customer1.getCoupons().add(coupon2);
//		customer1.getCoupons().add(coupon5);
//		adminService.addCustomer(customer1);
//
//		System.out.println(">> ADDING CUSTOMER WITH THE SAME EMAIL: UNSUCCESSFUL <<");
//		Customer customer = new Customer();
//		customer.setFirstName("James");
//		customer.setLastName("Bond");
//		customer.setPassword("007");
//		customer.setEmail("johnappleseed@apple.com");
//		try {
//			adminService.addCustomer(customer);
//		} catch (RestrictedEntityDetailsChangeException e) {
//			System.out.println(e.getMessage());
//		}
//
//		System.out.println(">> ADDING NEW CUSTOMER <<");
//
//		Customer customer2 = new Customer();
//		customer2.setFirstName("Donna");
//		customer2.setLastName("Tartt");
//		customer2.setEmail("donnatartt@gmail.com");
//		customer2.setPassword("goldfinch");
//		customer2.getCoupons().add(coupon5);
//
//		Customer customer3 = new Customer();
//		customer3.setFirstName("Mario");
//		customer3.setLastName("Puzo");
//		customer3.setPassword("3232");
//		customer3.setEmail("mario@gmail.com");
//		customer3.getCoupons().add(coupon3);
//		customer3.getCoupons().add(coupon5);
//		try {
//			adminService.addCustomer(customer2);
//			adminService.addCustomer(customer3);
//		} catch (RestrictedEntityDetailsChangeException e) {
//			System.out.println(e.getMessage());
//		}
//		System.out.println(adminService.getAllCustomers());
//
//		System.out.println(">> UPDATING CUSTOMER'S ID: UNSUCCESSFUL <<");
//		customer2.setId(3);
//		try {
//			adminService.updateCustomer(customer2);
//		} catch (RestrictedEntityDetailsChangeException e) {
//			System.out.println(e.getMessage());
//		}
//
//		System.out.println(">> SUCCESSFULLY UPDATING CUSTOMER (EMAIL)<<");
//		customer2 = adminService.getOneCustomer(2);
//		customer2.setEmail("donna@gmail.com");
//		try {
//			adminService.updateCustomer(customer2);
//		} catch (RestrictedEntityDetailsChangeException e) {
//			System.out.println(e.getMessage());
//		}
//		System.out.println(adminService.getOneCustomer(2));
//
//		System.out.println(">> DELETNG CUSTOMER <<");
//		adminService.deleteCustomer(3);
//		System.out.println(adminService.getAllCustomers());
//
//		System.out.println(">> DELETING COMPANY <<");
//		adminService.deleteCompany(1);
//		System.out.println(adminService.getAllCompanies());
//
//		System.out.println(">> COMPANY VALID LOGIN <<");
//		companyService = (CompanyService) loginManager.login("bici@gmail.com", "4444", ClientType.COMPANY);
//		companyService.setCompanyId(3);
//
//		System.out.println(">> ADDING COUPON WITH EXISTING TITLE: NOT WORKING <<");
//		Coupon coupon = new Coupon();
//		coupon.setCompanyId(3);
//		coupon.setCategory(Category.RESTAURANT);
//		coupon.setTitle("Free dessert");
//		coupon.setDescription("Second dessert free");
//		coupon.setStartDate(Date.valueOf("2020-12-25"));
//		coupon.setEndDate(Date.valueOf("2020-12-31"));
//		coupon.setAmount(1);
//		coupon.setPrice(15.5);
//		coupon.setImage("jpeg");
//		try {
//			companyService.addCoupon(coupon);
//		} catch (RestrictedEntityDetailsChangeException e) {
//			System.out.println(e.getMessage());
//		}
//
//		System.out.println(">> ADDING COUPON WITH A NEW TITLE: WORKING <<");
//
//		Coupon coupon6 = new Coupon();
//		coupon6.setCompanyId(3);
//		coupon6.setCategory(Category.RESTAURANT);
//		coupon6.setTitle("10 % discount");
//		coupon6.setDescription("Soft opening promotion");
//		coupon6.setStartDate(Date.valueOf("2020-10-01"));
//		coupon6.setEndDate(Date.valueOf("2020-10-15"));
//		coupon6.setAmount(3);
//		coupon6.setPrice(30.7);
//		coupon6.setImage("jpeg");
//
//		Coupon coupon7 = new Coupon();
//		coupon7.setCompanyId(3);
//		coupon7.setCategory(Category.RESTAURANT);
//		coupon7.setTitle("30 % discount");
//		coupon7.setDescription("Birthday month discount");
//		coupon7.setStartDate(Date.valueOf("2020-11-02"));
//		coupon7.setEndDate(Date.valueOf("2020-11-20"));
//		coupon7.setAmount(0);
//		coupon7.setPrice(55.5);
//		coupon7.setImage("jpeg");
//
//		try {
//			companyService.addCoupon(coupon6);
//			companyService.addCoupon(coupon7);
//		} catch (RestrictedEntityDetailsChangeException e) {
//			System.out.println(e.getMessage());
//		}
//		System.out.println(companyService.getCompanyCoupons());
//
//		System.out.println(">> UPDATING COUPON ID: NOT WORKING <<");
//		coupon5.setId(9);
//		try {
//			companyService.updateCoupon(coupon5);
//		} catch (RestrictedEntityDetailsChangeException e) {
//			System.out.println(e.getMessage());
//		}
//
//		System.out.println(">> UPDATING COMPANY ID: NOT WORKING <<");
//		coupon5.setId(5);
//		coupon5.setCompanyId(4);
//		try {
//			companyService.updateCoupon(coupon5);
//		} catch (RestrictedEntityDetailsChangeException e) {
//			System.out.println(e.getMessage());
//		}
//
//		System.out.println(">> UPDATING COUPON: WORKING (PRICE) <<");
//		coupon6.setPrice(33.5);
//		try {
//			companyService.updateCoupon(coupon6);
//		} catch (RestrictedEntityDetailsChangeException e) {
//			System.out.println(e.getMessage());
//		}
//		System.out.println(companyService.getCompanyCoupons());
//
//		System.out.println(">> DELETING COUPON AND CVC RELATED COUPONS << ");
//		companyService.deleteCoupon(5);
//		System.out.println(companyService.getCompanyCoupons());
//
//		System.out.println(">> GET ALL COUPONS BY CATEGORY <<");
//		System.out.println(companyService.getCompanyCoupons(Category.RESTAURANT));
//
//		System.out.println(">> GET ALL COUPONS BELOW MAX PRICE <<");
//		System.out.println(companyService.getCompanyCoupons(49.9));
//
//		System.out.println(">> GET COMPANY DETAILS <<");
//		System.out.println(companyService.getCompanyDetails());
//		System.out.println(">> ALL COUPONS BEFORE PURCHASE <<");
//		System.out.println(adminService.getAllCoupons());
//		System.out.println(">> CUSTOMER VALID LOGIN <<");
//		customerService = (CustomerService) loginManager.login("johnappleseed@apple.com", "8888", ClientType.CUSTOMER);
//
//		System.out.println(">> SUCCESSFUL COUPON PURCHASE <<");
//		try {
//			customerService.purchaseCoupon(coupon3);
//		} catch (PurchaseUnavailableException e) {
//			System.out.println(e.getMessage());
//		}
//		System.out.println(customerService.getCustomerCoupons());
//
//		System.out.println(">> UNSUCCESSFUL COUPON PURCHASE <<");
//		try {
//			customerService.purchaseCoupon(coupon6);
//		} catch (PurchaseUnavailableException e) {
//			System.out.println(e.getMessage());
//		}
//		try {
//			customerService.purchaseCoupon(coupon7);
//		} catch (PurchaseUnavailableException e) {
//			System.out.println(e.getMessage());
//		}
//		try {
//			customerService.purchaseCoupon(coupon3);
//		} catch (PurchaseUnavailableException e) {
//			System.out.println(e.getMessage());
//		}
//
//		System.out.println(">> GET ALL CUSTOMER'S COUPONS BY CATEGORY <<");
//		System.out.println(customerService.getCustomerCoupons(Category.FOOD));
//
//		System.out.println(">> GET ALL COUPONS BELOW MAX PRICE <<");
//		System.out.println(customerService.getCustomerCoupons(99.9));
//
//		System.out.println(">> GET CUSTOMER DETAILS <<");
//		System.out.println(customerService.getCustomerDetails());

	}

}
