package com.johnbryce.cs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.johnbryce.cs.beans.Coupon;

public interface CouponsRepository extends JpaRepository<Coupon, Integer> {
	
	
}
