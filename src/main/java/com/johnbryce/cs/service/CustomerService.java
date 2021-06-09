package com.johnbryce.cs.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.johnbryce.cs.beans.Category;
import com.johnbryce.cs.beans.Coupon;
import com.johnbryce.cs.beans.Customer;
import com.johnbryce.cs.exceptions.PurchaseUnavailableException;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Service
@Scope("prototype")
public class CustomerService extends ClientService {

	private int customerId;

	@Override
	public boolean login(String email, String password) {
		return this.customersRepository.ifCustomerExists(email, password);
	}

	public int getCustIdFromDB(String email, String password) {
		return this.customersRepository.getCustomerId(email, password);
	}

	public void purchaseCoupon(Coupon coupon) throws PurchaseUnavailableException {
		if (coupon.getAmount() < 1) {
			throw new PurchaseUnavailableException("No coupons available");
		}
		if (coupon.getEndDate().before(Date.valueOf(LocalDate.now())) == true) {
			throw new PurchaseUnavailableException("This coupon expired on", coupon.getEndDate());
		}
		Customer customer = this.customersRepository.getOne(this.customerId);
		List<Coupon> coupons = customer.getCoupons();
		for (Coupon coup : coupons) {
			if (coup.getId() == coupon.getId()) {
				throw new PurchaseUnavailableException("You've already purchased this coupon");
			}
		}
		coupon.setAmount(coupon.getAmount() - 1);
		customer.getCoupons().add(coupon);
		this.customersRepository.saveAndFlush(customer);
	}

	public List<Coupon> getCustomerCoupons(Category category) {
		return getCustomerCoupons().stream().filter(c -> c.getCategory().equals(category)).collect(Collectors.toList());
	}

	public List<Coupon> getCustomerCoupons(double maxPrice) {
		return getCustomerCoupons().stream().filter(c -> c.getPrice() <= maxPrice).collect(Collectors.toList());
	}

	public Customer getCustomerDetails() {
		List<Customer> customers = this.customersRepository.findAll();
		for (Customer cust : customers) {
			if (cust.getId() == this.customerId) {
				return cust;
			}
		}
		return null;
	}

	public List<Coupon> getCustomerCoupons() {
		return this.customersRepository.getOne(this.customerId).getCoupons();
	}

	public List<Coupon> getAllCoupons() {
		return this.couponsRepository.findAll();
	}

}
