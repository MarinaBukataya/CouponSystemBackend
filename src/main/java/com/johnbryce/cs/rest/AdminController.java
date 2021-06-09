package com.johnbryce.cs.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnbryce.cs.beans.Company;
import com.johnbryce.cs.beans.Customer;
import com.johnbryce.cs.beans.LoginResponse;
import com.johnbryce.cs.security.ClientType;
import com.johnbryce.cs.security.LoginManager;
import com.johnbryce.cs.security.TokensManager;
import com.johnbryce.cs.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
public class AdminController extends ClientController {

	private final AdminService adminService;

	private final LoginManager loginManager;

	private final TokensManager tokensManager;

	@Override
	@PostMapping("login/{email}/{password}")
	public ResponseEntity<?> login(@PathVariable String email, @PathVariable String password) {
		try {
			String token = loginManager.login(email, password, ClientType.ADMINISTRATOR);
			if (token == null) {
				return new ResponseEntity<>("Dear Administrator, you've entered incorrect username or password",
						HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<>(new LoginResponse(token), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("add-company")
	@SneakyThrows
	public ResponseEntity<?> addCompany(@RequestBody Company company) {
		return new ResponseEntity<>(adminService.addCompany(company), HttpStatus.CREATED);
	}

	@PutMapping("update-company")
	@SneakyThrows
	public ResponseEntity<?> updateCompany(@RequestBody Company company) {
		return new ResponseEntity<>(adminService.updateCompany(company), HttpStatus.OK);

	}

	@DeleteMapping("delete-company/{id}")
	public ResponseEntity<?> deleteCompany(@PathVariable(name = "id") int companyId) {
		adminService.deleteCompany(companyId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("all-companies")
	public ResponseEntity<?> getAllCompanies() {
		return new ResponseEntity<>(adminService.getAllCompanies(), HttpStatus.OK);
	}

	@GetMapping("one-company/{id}")
	public ResponseEntity<?> getOneCompany(@PathVariable(name = "id") int companyId) {
		return new ResponseEntity<>(adminService.getOneCompany(companyId), HttpStatus.OK);
	}

	@PostMapping("add-customer")
	@SneakyThrows
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
		return new ResponseEntity<>(adminService.addCustomer(customer), HttpStatus.CREATED);
	}

	@PutMapping("update-customer")
	@SneakyThrows
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {
		return new ResponseEntity<>(adminService.updateCustomer(customer), HttpStatus.OK);
	}

	@DeleteMapping("delete-customer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable(name = "id") int customerId) {
		adminService.deleteCustomer(customerId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("all-customers")
	public ResponseEntity<?> getAllCustomers() {
		return new ResponseEntity<>(adminService.getAllCustomers(), HttpStatus.OK);
	}

	@GetMapping("one-customer/{id}")
	public ResponseEntity<?> getOneCustomer(@PathVariable(name = "id") int customerId) {
		return new ResponseEntity<>(adminService.getOneCustomer(customerId), HttpStatus.OK);
	}

	@DeleteMapping("logout/{token}")
	public ResponseEntity<?> logout(@PathVariable String token) {
		tokensManager.deleteToken(token);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
