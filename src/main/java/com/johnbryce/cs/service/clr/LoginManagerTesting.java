package com.johnbryce.cs.service.clr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.johnbryce.cs.security.ClientType;
import com.johnbryce.cs.security.LoginManager;
import com.johnbryce.cs.service.AdminService;
import com.johnbryce.cs.service.CompanyService;
import com.johnbryce.cs.service.CustomerService;

//@Component
@Order(3)
public class LoginManagerTesting implements CommandLineRunner {

//	@Autowired
//	private LoginManager loginManager;
//
	@Override
	public void run(String... args) throws Exception {
//
//		System.out.println(">> COMPANY BAD LOGIN <<");
//		CompanyService companyService = (CompanyService) loginManager.login("moshe777@gmail.com", "1234",
//				ClientType.COMPANY);
//		if (companyService != null) {
//			System.out.println(">> USAGE EXAMPLE <<");
//			System.out.println(companyService.getCompanyCoupons());
//		} else {
//			System.out.println("Wrong email or password");
//		}
//
//		System.out.println(">> COMPANY VALID LOGIN <<");
//		CompanyService companyService1 = (CompanyService) loginManager.login("bici@gmail.com", "4444",
//				ClientType.COMPANY);
//		System.out.println(">> USAGE EXAMPLE <<");
//		System.out.println(companyService1.getCompanyCoupons());
//
//		System.out.println(">> CUSTOMER BAD LOGIN <<");
//		CustomerService customerService = (CustomerService) loginManager.login("moshe@gmail.com", "5678",
//				ClientType.CUSTOMER);
//		if (customerService != null) {
//			System.out.println(">> USAGE EXAMPLE <<");
//			System.out.println(customerService.getCustomerCoupons());
//		} else {
//			System.out.println("Wrong email or password");
//		}
//
//		System.out.println(">> CUSTOMER VALID LOGIN <<");
//		CustomerService customerService1 = (CustomerService) loginManager.login("johnappleseed@apple.com", "8888",
//				ClientType.CUSTOMER);
//		System.out.println(">> USAGE EXAMPLE <<");
//		System.out.println(customerService1.getCustomerCoupons());
//
//		System.out.println(">> ADMIN BAD LOGIN <<");
//		AdminService adminService = (AdminService) loginManager.login("mosh@gmail.com", "91011",
//				ClientType.ADMINISTRATOR);
//		if (adminService != null) {
//			System.out.println(">> USAGE EXAMPLE <<");
//			System.out.println(adminService.getAllCompanies());
//		} else {
//			System.out.println("Wrong email or password");
//		}
//
//		System.out.println(">> ADMIN VALID LOGIN <<");
//		AdminService adminService1 = (AdminService) loginManager.login("admin@admin.com", "admin",
//				ClientType.ADMINISTRATOR);
//		System.out.println(">> USAGE EXAMPLE <<");
//		System.out.println(adminService1.getAllCompanies());
//
	}

}
