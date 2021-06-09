package com.johnbryce.cs.repo.clr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.johnbryce.cs.beans.Coupon;
import com.johnbryce.cs.beans.Customer;
import com.johnbryce.cs.repo.CouponsRepository;
import com.johnbryce.cs.repo.CustomersRepository;

//@Component
@Order(2)
public class CustomersAndCouponsRepositoryTesting implements CommandLineRunner {
	@Autowired
	private CustomersRepository customersRepository;

	@Autowired
	private CouponsRepository couponsRepository;

	@Override
	public void run(String... args) throws Exception {

		Coupon coupon1 = new Coupon();
		coupon1 = couponsRepository.getOne(1);

		Coupon coupon2 = new Coupon();
		coupon2 = couponsRepository.getOne(2);

		Customer customer1 = new Customer();
		customer1.setFirstName("John");
		customer1.setLastName("Appleseed");
		customer1.setPassword("8888");
		customer1.setEmail("johnappleseed@apple.com");
		customer1.getCoupons().add(coupon1);
		customer1.getCoupons().add(coupon2);
		customersRepository.save(customer1);

		Coupon coupon3 = new Coupon();
		coupon3 = couponsRepository.getOne(3);
		Customer customer2 = new Customer();
		customer2.setFirstName("Hailey");
		customer2.setLastName("Rutledge");
		customer2.setPassword("1515");
		customer2.setEmail("hailey@gmail.com");
		customer2.getCoupons().add(coupon3);
		customersRepository.save(customer2);

		Coupon coupon4 = new Coupon();
		coupon4 = couponsRepository.getOne(4);

		Customer customer3 = new Customer();
		customer3.setFirstName("Mario");
		customer3.setLastName("Puzo");
		customer3.setPassword("3232");
		customer3.setEmail("mario@gmail.com");
		customer3.getCoupons().add(coupon4);
		customersRepository.save(customer3);

		System.out.println(">> ADD CUSTOMERS <<");
		System.out.println(customersRepository.findAll());

		System.out.println(">> CUSTOMER EXISTS <<");
		System.out.println(customersRepository.ifCustomerExists("johnappleseed@apple.com", "8888"));

		System.out.println(">> CUSTOMER DOES NOT EXIST <<");
		System.out.println(customersRepository.ifCustomerExists("john@apple.com", "7777"));

		System.out.println(">> UPDATE CUSTOMER <<");
		customer2 = customersRepository.getOne(2);
		customer2.setFirstName("Elizabeth");
		customersRepository.saveAndFlush(customer2);
		System.out.println(customersRepository.getOne(2));

		System.out.println(">> DELETE CUSTOMER <<");
		customer2 = customersRepository.getOne(2);
		customersRepository.delete(customer2);

		System.out.println(">> GET ALL CUSTOMERS <<");
		System.out.println(customersRepository.findAll());

		System.out.println(">> GET ONE CUSTOMER <<");
		System.out.println(customersRepository.getOne(1));

		System.out.println(">> GET CUSTOMER ID <<");
		System.out.println(customersRepository.getCustomerId("mario@gmail.com", "3232"));

		System.out.println(">> UPDATE COUPON <<");
		coupon2.setAmount(0);
		couponsRepository.saveAndFlush(coupon2);
		System.out.println(couponsRepository.getOne(2));

		System.out.println(">> DELETE COUPON <<");
		couponsRepository.delete(coupon1);

		System.out.println(">> GET ALL COUPONS <<");
		System.out.println(couponsRepository.findAll());

		System.out.println(">> GET ONE COUPON <<");
		System.out.println(couponsRepository.getOne(2));

	}

}
