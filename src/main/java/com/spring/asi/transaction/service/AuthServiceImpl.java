package com.spring.asi.transaction.service;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.asi.transaction.config.JwtUtils;
import com.spring.asi.transaction.dto.JwtResponse;
import com.spring.asi.transaction.dto.LoginRequest;
import com.spring.asi.transaction.dto.ResetPassword;
import com.spring.asi.transaction.dto.ResponseJson;
import com.spring.asi.transaction.dto.SignupRequest;
import com.spring.asi.transaction.exception.ServiceException;
import com.spring.asi.transaction.model.User;
import com.spring.asi.transaction.repository.UserRepository;
import com.spring.asi.transaction.utils.ErrorConstant;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private Environment environment;


	@Override
	public JwtResponse authenticateUser(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail());
	}

	@Override
	public String registerUser(SignupRequest signUpRequest) {
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {

			ResponseJson responseJson = new ResponseJson(environment, ErrorConstant.E1007_ERROR_CODE,
					ErrorConstant.E1007_ERROR_DESCRIPTION);
			throw new ServiceException(responseJson, HttpStatus.ALREADY_REPORTED);
		}
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));
		userRepository.save(user);
		return "User registered successfully!";
	}

	@Override
	public boolean resetPasswordRequest(String emailId) {
		Optional<User> userOpt = userRepository.findByEmail(emailId);
		if (!userOpt.isPresent()) {
			ResponseJson responseJson = new ResponseJson(environment, ErrorConstant.E1007_ERROR_CODE,
					ErrorConstant.E1007_ERROR_DESCRIPTION);
			throw new ServiceException(responseJson, HttpStatus.ALREADY_REPORTED);
		}
		String uuid = UUID.randomUUID().toString();
		User user = userOpt.get();
		user.setResetPassKey(uuid);
		userRepository.save(user);
		return true;
	}

	@Override
	public boolean resetPassword(ResetPassword resetPassword) {
		Optional<User> result = userRepository.findByEmailAndSecretKey(resetPassword.getEmailId(),
				resetPassword.getSecretKey());
		if (!result.isPresent()) {
			ResponseJson responseJson = new ResponseJson(environment, ErrorConstant.E1007_ERROR_CODE,
					ErrorConstant.E1007_ERROR_DESCRIPTION);
			throw new ServiceException(responseJson, HttpStatus.ALREADY_REPORTED);
		}
		User user = result.get();
		user.setPassword(encoder.encode(resetPassword.getPassword()));
		user.setResetPassKey("");
		userRepository.save(user);
		return true;
	}
}
