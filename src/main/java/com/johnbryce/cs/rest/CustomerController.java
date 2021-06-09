package com.johnbryce.cs.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnbryce.cs.beans.Category;
import com.johnbryce.cs.beans.Coupon;
import com.johnbryce.cs.beans.LoginResponse;
import com.johnbryce.cs.security.ClientType;
import com.johnbryce.cs.security.LoginManager;
import com.johnbryce.cs.security.TokensManager;
import com.johnbryce.cs.service.CustomerService;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RestController
@RequiredArgsConstructor
@RequestMapping("customer")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
public class CustomerController extends ClientController {

	private final CustomerService customerService;

	private final LoginManager loginManager;

	private final TokensManager tokensManager;

	@Override
	@PostMapping("login/{email}/{password}")
	public ResponseEntity<?> login(@PathVariable String email, @PathVariable String password) {
		try {
			String token = loginManager.login(email, password, ClientType.CUSTOMER);
			customerService.setCustomerId(customerService.getCustIdFromDB(email, password));
			return new ResponseEntity<>(new LoginResponse(token), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Dear Customer, you've entered incorrect username or password",
					HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("purchase-coupon")
	@SneakyThrows
	public ResponseEntity<?> purchaseCoupon(@RequestBody Coupon coupon) {
		customerService.purchaseCoupon(coupon);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("all-customer-coupons")
	public ResponseEntity<?> getCustomerCoupons() {
		return new ResponseEntity<>(customerService.getCustomerCoupons(), HttpStatus.OK);
	}

	@GetMapping("customer-coupons-by-category/{category}")
	public ResponseEntity<?> getCustomerCoupons(@PathVariable Category category) {
		return new ResponseEntity<>(customerService.getCustomerCoupons(category), HttpStatus.OK);
	}

	@GetMapping("customer-coupons-by-price/{price}")
	public ResponseEntity<?> getCustomerCoupons(@PathVariable(name = "price") double maxPrice) {
		return new ResponseEntity<>(customerService.getCustomerCoupons(maxPrice), HttpStatus.OK);
	}

	@GetMapping("customer-details")
	public ResponseEntity<?> getCustomerDetails() {
		return new ResponseEntity<>(customerService.getCustomerDetails(), HttpStatus.OK);
	}

	@GetMapping("all-coupons")
	public ResponseEntity<?> getAllCcoupons() {
		return new ResponseEntity<>(customerService.getAllCoupons(), HttpStatus.OK);
	}

	@DeleteMapping("logout/{token}")
	public ResponseEntity<?> logout(@PathVariable String token) {
		tokensManager.deleteToken(token);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
