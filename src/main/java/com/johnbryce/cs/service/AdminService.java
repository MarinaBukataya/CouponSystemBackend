package com.johnbryce.cs.service;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.johnbryce.cs.beans.Company;
import com.johnbryce.cs.beans.Coupon;
import com.johnbryce.cs.beans.Customer;
import com.johnbryce.cs.exceptions.RestrictedEntityDetailsChangeException;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Service
@Lazy
public class AdminService extends ClientService {

	@Override
	public boolean login(String email, String password) {
		if (email.equals("admin@admin.com") && password.equals("admin")) {
			return true;
		}
		return false;
	}

	public Company addCompany(Company company) throws RestrictedEntityDetailsChangeException {
		List<Company> companies = this.companiesRepository.findAll();
		for (Company cmp : companies) {
			if (cmp.getName().equals(company.getName())) {
				throw new RestrictedEntityDetailsChangeException(company.getName() + " name already exists");
			} else if (cmp.getEmail().equals(company.getEmail())) {
				throw new RestrictedEntityDetailsChangeException(company.getEmail() + " email already exists");
			}
		}
		return this.companiesRepository.save(company);
	}

	public Company updateCompany(Company company) throws RestrictedEntityDetailsChangeException {
		List<Company> companies = this.companiesRepository.findAll();
		for (Company cmp : companies) {
			if (cmp.getId() == company.getId() && cmp.getName().equals(company.getName())) {
				return this.companiesRepository.saveAndFlush(company);
			}
		}
		throw new RestrictedEntityDetailsChangeException("Company's name change is not allowed");
	}

	public void deleteCompany(int companyId) {
		Company company = this.companiesRepository.getOne(companyId);
		List<Coupon> companyCoupons = company.getCoupons();
		List<Customer> customers = this.customersRepository.findAll();
		for (Customer customer : customers) {
			for (Coupon coupon : companyCoupons) {
				List<Coupon> customerCoupons = customer.getCoupons();
				for (Coupon coup : customerCoupons) {
					if (coup.getId() == coupon.getId()) {
						customer.removeCoupon(coup);
						break;
					}
				}
			}
			this.customersRepository.saveAndFlush(customer);
		}
		this.companiesRepository.deleteById(companyId);
	}

	public List<Company> getAllCompanies() {
		return this.companiesRepository.findAll();
	}

	public Company getOneCompany(int companyId) {
		return this.companiesRepository.getOne(companyId);
	}

	public Customer addCustomer(Customer customer) throws RestrictedEntityDetailsChangeException {
		List<Customer> customers = this.customersRepository.findAll();
		for (Customer cust : customers) {
			if (cust.getEmail().equals(customer.getEmail())) {
				throw new RestrictedEntityDetailsChangeException(customer.getEmail() + " email already exists");
			}
		}
		return this.customersRepository.save(customer);
	}

	public Customer updateCustomer(Customer customer) throws RestrictedEntityDetailsChangeException {
		List<Customer> customers = this.customersRepository.findAll();
		for (Customer cust : customers) {
			if (cust.getId() == customer.getId()) {
				return this.customersRepository.saveAndFlush(customer);
			} else if (cust.getId() != customer.getId() && cust.getFirstName().equals(customer.getFirstName())
					&& cust.getLastName().equals(customer.getLastName())
					&& cust.getPassword().equals(customer.getPassword())
					&& cust.getEmail().equals(customer.getEmail())) {
				throw new RestrictedEntityDetailsChangeException("Customer Id change is not allowed");
			}
		}
		return null;
	}

	public void deleteCustomer(int customerId) {
		this.customersRepository.deleteById(customerId);
	}

	public List<Customer> getAllCustomers() {
		return this.customersRepository.findAll();
	}

	public Customer getOneCustomer(int customerId) {
		return this.customersRepository.getOne(customerId);
	}

}
