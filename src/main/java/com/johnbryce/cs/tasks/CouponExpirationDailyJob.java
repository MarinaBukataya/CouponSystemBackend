package com.johnbryce.cs.tasks;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.johnbryce.cs.beans.Company;
import com.johnbryce.cs.beans.Coupon;
import com.johnbryce.cs.beans.Customer;
import com.johnbryce.cs.repo.CompaniesRepository;
import com.johnbryce.cs.repo.CouponsRepository;
import com.johnbryce.cs.repo.CustomersRepository;

@Component
public class CouponExpirationDailyJob {

	@Autowired
	private CouponsRepository couponsRepository;
	@Autowired
	private CustomersRepository customersRepository;
	@Autowired
	private CompaniesRepository companiesRepository;

	@Scheduled(fixedRate = 1000 * 60 * 60 * 24)
	public void deleteExpiredCoupons() {
		boolean updated = false;
		List<Coupon> allCoupons = this.couponsRepository.findAll();
		for (Coupon coupon : allCoupons) {
			if (coupon.getEndDate().before(Date.valueOf(LocalDate.now())) == true) {
				List<Customer> customers = this.customersRepository.findAll();
				for (Customer customer : customers) {
					List<Coupon> customerCoupons = customer.getCoupons();
					for (Coupon coup : customerCoupons) {
						if (coup.getId() == coupon.getId()) {
							customer.removeCoupon(coup);
							this.customersRepository.saveAndFlush(customer);
							break;
						}
					}
				}
				List<Company> allCompanies = this.companiesRepository.findAll();
				for (Company companyA : allCompanies) {
					List<Coupon> companyCoupons = companyA.getCoupons();
					updated = false;
					for (Coupon coupon1 : companyCoupons) {
						if (coupon1.getId() == coupon.getId()) {
							companyA.getCoupons().remove(coupon1);
							updated = true;
							break;
						}
					}
					if (updated)
						this.companiesRepository.saveAndFlush(companyA);
				}
			}
		}
	}

}
