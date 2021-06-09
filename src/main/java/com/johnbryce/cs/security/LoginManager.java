package com.johnbryce.cs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.johnbryce.cs.service.AdminService;
import com.johnbryce.cs.service.CompanyService;
import com.johnbryce.cs.service.CustomerService;

import lombok.NoArgsConstructor;

@Service
@Lazy
@NoArgsConstructor
public class LoginManager {

	@Autowired
	private ApplicationContext ctx;

	private CustomerService customerService;
	private CompanyService companyService;

	@Autowired
	private TokensManager tokensManager;

	@Autowired
	private AdminService adminService;

	public String login(String email, String password, ClientType clientType) throws Exception {
		switch (clientType) {
		case ADMINISTRATOR:
			if (adminService.login(email, password)) {
				return tokensManager.addToken(adminService);
			}
			break;
		case COMPANY:
			companyService = ctx.getBean(CompanyService.class);
			if (companyService.login(email, password)) {
				companyService.setCompanyId(companyService.getCompIdFromDB(email, password));
				return tokensManager.addToken(companyService);
			}
			break;
		case CUSTOMER:
			customerService = ctx.getBean(CustomerService.class);
			if (customerService.login(email, password)) {
				customerService.setCustomerId(customerService.getCustIdFromDB(email, password));
				return tokensManager.addToken(customerService);
			}
			break;
		default:
			throw new Exception("Not Found");
		}
		return null;
	}
}
