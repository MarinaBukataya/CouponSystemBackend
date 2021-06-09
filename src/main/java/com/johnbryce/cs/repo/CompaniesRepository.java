package com.johnbryce.cs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.johnbryce.cs.beans.Company;

public interface CompaniesRepository extends JpaRepository<Company, Integer> {

	@Query("SELECT CASE WHEN count(c)> 0 THEN true ELSE false END FROM Company c WHERE LOWER(c.email) like LOWER(:email) AND LOWER(c.password) like LOWER(:password)")
	boolean ifCompanyExists(@Param("email") String email, @Param("password") String password);

	@Query("SELECT id AS id FROM Company c WHERE c.email = :email AND c.password = :password")
	int getCompanyId(@Param("email") String email, @Param("password") String password);
}
