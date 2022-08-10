package com.spring.asi.transaction.controller;

import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.asi.transaction.dto.LoginRequest;
import com.spring.asi.transaction.dto.ResetPassword;
import com.spring.asi.transaction.dto.ResponseJson;
import com.spring.asi.transaction.dto.SignupRequest;
import com.spring.asi.transaction.service.AuthService;
import com.spring.asi.transaction.utils.CommonConstant;
import com.spring.asi.transaction.utils.MappingConstant;

@RestController
@RequestMapping(MappingConstant.AUTH)
public class AuthController {

	private AuthService authService;
	private Environment environment;

	public AuthController(AuthService authService, Environment environment) {
		this.authService = authService;
		this.environment = environment;
	}

	@PostMapping(MappingConstant.SIGNIN)
	public ResponseEntity<ResponseJson> authenticateUser(@RequestBody LoginRequest loginRequest) {
		ResponseJson responseJson = new ResponseJson(environment);
		responseJson.setResponse(authService.authenticateUser(loginRequest));
		responseJson.setResponseDescription(CommonConstant.S0001_SUCCESS_DESCRIPTION);
		return ResponseEntity.ok(responseJson);
	}

	@PostMapping(MappingConstant.SIGNUP)
	public ResponseEntity<ResponseJson> registerUser(@RequestBody SignupRequest signUpRequest) {
		ResponseJson responseJson = new ResponseJson(environment);
		responseJson.setResponse(authService.registerUser(signUpRequest));
		responseJson.setResponseDescription(CommonConstant.S0002_SUCCESS_DESCRIPTION);
		return ResponseEntity.ok(responseJson);
	}

	@PostMapping(MappingConstant.RESET_PASSWORD_REQUEST)
	public ResponseEntity<ResponseJson> resetPassword(@RequestParam String emailId) {
		ResponseJson responseJson = new ResponseJson(environment);
		responseJson.setResponse(authService.resetPasswordRequest(emailId));
		responseJson.setResponseDescription(CommonConstant.S0001_SUCCESS_DESCRIPTION);
		return ResponseEntity.ok(responseJson);
	}

	@PostMapping(MappingConstant.RESET_PASSWORD)
	public ResponseEntity<ResponseJson> resetPassword(@RequestBody ResetPassword resetPassword) {
		ResponseJson responseJson = new ResponseJson(environment);
		responseJson.setResponse(authService.resetPassword(resetPassword));
		responseJson.setResponseDescription(CommonConstant.S0001_SUCCESS_DESCRIPTION);
		return ResponseEntity.ok(responseJson);
	}
}
