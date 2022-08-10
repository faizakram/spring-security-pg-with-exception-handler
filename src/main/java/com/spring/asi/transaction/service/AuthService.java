package com.spring.asi.transaction.service;

import com.spring.asi.transaction.dto.JwtResponse;
import com.spring.asi.transaction.dto.LoginRequest;
import com.spring.asi.transaction.dto.ResetPassword;
import com.spring.asi.transaction.dto.SignupRequest;

public interface AuthService {
	/**
	 * authenticateuser
	 * @param loginRequest
	 * @return
	 */
	JwtResponse authenticateUser(LoginRequest loginRequest);
	/**
	 * registerUser
	 * @param signUpRequest
	 * @return
	 */
	String registerUser(SignupRequest signUpRequest);
	/**
	 * resetPassword
	 * @param emailId
	 * @return
	 */
	boolean resetPasswordRequest(String emailId);
	/**
	 * resetPassword
	 * @param resetPassword
	 * @return
	 */
	boolean resetPassword(ResetPassword resetPassword);
}

