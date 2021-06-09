package com.johnbryce.cs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.johnbryce.cs.beans.Customer;

public interface CustomersRepository extends JpaRepository<Customer, Integer> {

	@Query("SELECT CASE WHEN count(c)> 0 THEN true ELSE false END FROM Customer c WHERE LOWER(c.email) like LOWER(:email) AND LOWER(c.password) like LOWER(:password)")
	boolean ifCustomerExists(@Param("email") String email, @Param("password") String password);

	@Query("SELECT id AS id FROM Customer c WHERE c.email = :email AND c.password = :password")
	int getCustomerId(@Param("email") String email, @Param("password") String password);

}
