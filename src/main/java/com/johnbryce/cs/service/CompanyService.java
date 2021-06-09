package com.johnbryce.cs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.johnbryce.cs.beans.Category;
import com.johnbryce.cs.beans.Company;
import com.johnbryce.cs.beans.Coupon;
import com.johnbryce.cs.beans.Customer;
import com.johnbryce.cs.exceptions.RestrictedEntityDetailsChangeException;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Service
@Scope("prototype")
public class CompanyService extends ClientService {

	private int companyId;

	@Override
	public boolean login(String email, String password) {
		return this.companiesRepository.ifCompanyExists(email, password);
	}

	public int getCompIdFromDB(String email, String password) {
		return this.companiesRepository.getCompanyId(email, password);
	}

	public Coupon addCoupon(Coupon coupon) throws RestrictedEntityDetailsChangeException {
		List<Coupon> companyCoupons = getCompanyCoupons();
		for (Coupon coup : companyCoupons) {
			if (coup.getTitle().equals(coupon.getTitle())) {
				throw new RestrictedEntityDetailsChangeException("This coupon title already exists");
			}
		}
		Coupon newCoupon = this.couponsRepository.save(coupon);
		Company company = this.companiesRepository.getOne(this.companyId);
		company.getCoupons().add(coupon);
		this.companiesRepository.saveAndFlush(company);
		return newCoupon;
	}

	public Coupon updateCoupon(Coupon coupon) throws RestrictedEntityDetailsChangeException {
		List<Coupon> companyCoupons = getCompanyCoupons();
		for (Coupon coup : companyCoupons) {
			if (coup.getId() == coupon.getId() && coup.getCompanyId() == coupon.getCompanyId()) {
				return this.couponsRepository.saveAndFlush(coupon);
			}
		}
		throw new RestrictedEntityDetailsChangeException("Coupon's id or coupon's company id change is not allowed");
	}

	public void deleteCoupon(int couponId) {
		Company company = this.companiesRepository.getOne(this.companyId);
		Coupon coupon = this.couponsRepository.getOne(couponId);
		List<Customer> customers = this.customersRepository.findAll();
		for (Customer customer : customers) {
			List<Coupon> customerCoupons = customer.getCoupons();
			for (Coupon coup : customerCoupons) {
				if (coup.getId() == couponId) {
					customer.removeCoupon(coup);
					break;
				}
			}
			this.customersRepository.saveAndFlush(customer);
		}
		company.getCoupons().remove(coupon);
		this.companiesRepository.saveAndFlush(company);
	}

	public List<Coupon> getCompanyCoupons(Category category) {
		return getCompanyCoupons().stream().filter(c -> c.getCategory().equals(category)).collect(Collectors.toList());
	}

	public List<Coupon> getCompanyCoupons(double maxPrice) {
		return getCompanyCoupons().stream().filter(c -> c.getPrice() <= maxPrice).collect(Collectors.toList());
	}

	public List<Coupon> getCompanyCoupons() {
		return this.companiesRepository.getOne(this.companyId).getCoupons();
	}

	public Company getCompanyDetails() {
		List<Company> companies = this.companiesRepository.findAll();
		for (Company cmp : companies) {
			if (cmp.getId() == this.companyId) {
				return cmp;
			}
		}
		return null;
	}
}
