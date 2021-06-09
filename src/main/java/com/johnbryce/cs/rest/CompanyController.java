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

import com.johnbryce.cs.beans.Category;
import com.johnbryce.cs.beans.Coupon;
import com.johnbryce.cs.beans.LoginResponse;
import com.johnbryce.cs.security.ClientType;
import com.johnbryce.cs.security.LoginManager;
import com.johnbryce.cs.security.TokensManager;
import com.johnbryce.cs.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RestController
@RequiredArgsConstructor
@RequestMapping("company")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
public class CompanyController extends ClientController {

	private final CompanyService companyService;

	private final LoginManager loginManager;

	private final TokensManager tokensManager;

	@Override
	@PostMapping("login/{email}/{password}")
	public ResponseEntity<?> login(@PathVariable String email, @PathVariable String password) {
		try {
			String token = loginManager.login(email, password, ClientType.COMPANY);
			companyService.setCompanyId(companyService.getCompIdFromDB(email, password));
			return new ResponseEntity<>(new LoginResponse(token), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Dear Partner, you've entered incorrect username or password",
					HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("add-coupon")
	@SneakyThrows
	public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon) {
		return new ResponseEntity<>(companyService.addCoupon(coupon), HttpStatus.CREATED);
	}

	@PutMapping("update-coupon")
	@SneakyThrows
	public ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon) {
		return new ResponseEntity<>(companyService.updateCoupon(coupon), HttpStatus.OK);
	}

	@DeleteMapping("delete-coupon/{id}")
	public ResponseEntity<?> deleteCoupon(@PathVariable(name = "id") int couponId) {
		companyService.deleteCoupon(couponId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("all-company-coupons")
	public ResponseEntity<?> getCompanyCoupons() {
		return new ResponseEntity<>(companyService.getCompanyCoupons(), HttpStatus.OK);
	}

	@GetMapping("company-coupons-by-category/{category}")
	public ResponseEntity<?> getCompanyCoupons(@PathVariable(name = "category") Category category) {
		return new ResponseEntity<>(companyService.getCompanyCoupons(category), HttpStatus.OK);
	}

	@GetMapping("company-coupons-by-price/{price}")
	public ResponseEntity<?> getCompanyCoupons(@PathVariable(name = "price") double maxPrice) {
		return new ResponseEntity<>(companyService.getCompanyCoupons(maxPrice), HttpStatus.OK);
	}

	@GetMapping("company-details")
	public ResponseEntity<?> getCompanyDetails() {
		return new ResponseEntity<>(companyService.getCompanyDetails(), HttpStatus.OK);
	}

	@DeleteMapping("logout/{token}")
	public ResponseEntity<?> logout(@PathVariable String token) {
		tokensManager.deleteToken(token);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
