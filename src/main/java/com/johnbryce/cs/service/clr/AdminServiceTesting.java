package com.johnbryce.cs.service.clr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.johnbryce.cs.beans.Company;
import com.johnbryce.cs.beans.Customer;
import com.johnbryce.cs.exceptions.RestrictedEntityDetailsChangeException;
import com.johnbryce.cs.service.AdminService;

//@Component
@Order(3)
public class AdminServiceTesting implements CommandLineRunner {
	@Autowired
	private AdminService adminService;

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println(">> ADDING COMPANY WITH THE SAME NAME: UNSUCCESSFUL <<");
		Company c1 = new Company();
		c1.setName("Tnuva");
		c1.setEmail("tnv1@gmail.com");
		c1.setPassword("57811");
		try {
			adminService.addCompany(c1);
		} catch (RestrictedEntityDetailsChangeException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(">> ADDING COMPANY WITH THE SAME EMAIL: UNSUCCESSFUL <<");
		Company c2 = new Company();
		c2.setName("Teva");
		c2.setEmail("tnv@gmail.com");
		c2.setPassword("4545");
		try {
			adminService.addCompany(c2);
		} catch (RestrictedEntityDetailsChangeException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(">> ADDING COMPANY: SUCCESSFUL <<");
		Company c3 = new Company();
		c3.setName("FOX");
		c3.setEmail("fox@walla.com");
		c3.setPassword("23476");
		try {
			adminService.addCompany(c3);
			System.out.println(adminService.getAllCompanies());
		} catch (RestrictedEntityDetailsChangeException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(">> UPDATING COMPANY ID: UNSUCCESSFUL <<");
		c3.setId(3);
		try {
			adminService.updateCompany(c3);
		} catch (RestrictedEntityDetailsChangeException e) {
			System.out.println(e.getMessage());
		}

		System.out.println(">> UPDATING COMPANY NAME: UNSUCCESSFUL <<");
		c3.setId(6);
		c3.setName("FOXhome");
		try {
			adminService.updateCompany(c3);
		} catch (RestrictedEntityDetailsChangeException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(">> SUCCESSFULLY UPDATING COMPANY <<");
		c3.setName("FOX");
		c3.setPassword("foxfox");
		try {
			adminService.updateCompany(c3);
			System.out.println(adminService.getAllCompanies());

		} catch (RestrictedEntityDetailsChangeException e) {
			System.out.println(e.getMessage());
		}

		System.out.println(">> DELETING COMPANY <<");
		adminService.deleteCompany(4);
		System.out.println(adminService.getAllCompanies());

		System.out.println(">> ADDING CUSTOMER WITH THE SAME EMAIL: UNSUCCESSFUL <<");
		Customer customer1 = new Customer();
		customer1.setFirstName("Mario");
		customer1.setLastName("Puzo");
		customer1.setPassword("3232");
		customer1.setEmail("mario@gmail.com");
		try {
			adminService.addCustomer(customer1);
		} catch (RestrictedEntityDetailsChangeException e) {
			System.out.println(e.getMessage());
		}

		System.out.println(">> ADDING NEW CUSTOMER <<");
		Customer customer2 = new Customer();
		customer2.setFirstName("Donna");
		customer2.setLastName("Tartt");
		customer2.setEmail("donnatartt@gmail.com");
		customer2.setPassword("goldfinch");
		try {
			adminService.addCustomer(customer2);
			System.out.println(adminService.getAllCustomers());
		} catch (RestrictedEntityDetailsChangeException e) {
			System.out.println(e.getMessage());
		}

		System.out.println(">> UPDATING CUSTOMER'S ID: UNSUCCESSFUL <<");
		customer2.setId(7);
		try {
			adminService.updateCustomer(customer2);
		} catch (RestrictedEntityDetailsChangeException e) {
			System.out.println(e.getMessage());
		}

		System.out.println(">> SUCCESSFULLY UPDATING CUSTOMER <<");
		customer2.setId(4);
		customer2.setEmail("donna@gmail.com");
		try {
			adminService.updateCustomer(customer2);
			System.out.println(adminService.getOneCustomer(4));
		} catch (RestrictedEntityDetailsChangeException e) {
			System.out.println(e.getMessage());
		}

		System.out.println(">> DELETNG CUSTOMER <<");
		adminService.deleteCustomer(1);
		System.out.println(adminService.getAllCustomers());

	}

}
