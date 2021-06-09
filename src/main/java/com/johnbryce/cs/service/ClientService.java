package com.johnbryce.cs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnbryce.cs.repo.CompaniesRepository;
import com.johnbryce.cs.repo.CouponsRepository;
import com.johnbryce.cs.repo.CustomersRepository;

import lombok.Data;

@Data
@Service
public abstract class ClientService {
	@Autowired
	protected CompaniesRepository companiesRepository;
	@Autowired
	protected CustomersRepository customersRepository;
	@Autowired
	protected CouponsRepository couponsRepository;

	public abstract boolean login(String email, String password);
}
