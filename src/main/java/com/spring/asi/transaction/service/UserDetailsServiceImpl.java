package com.spring.asi.transaction.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.asi.transaction.dto.ResponseJson;
import com.spring.asi.transaction.exception.ServiceException;
import com.spring.asi.transaction.model.User;
import com.spring.asi.transaction.repository.UserRepository;
import com.spring.asi.transaction.utils.ErrorConstant;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private Environment enviornmnet;

	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByEmail(userEmail);
		if (!user.isPresent()) {
			ResponseJson responseJson = new ResponseJson(enviornmnet, ErrorConstant.E1008_ERROR_CODE,
					ErrorConstant.E1008_ERROR_DESCRIPTION);
			throw new ServiceException(responseJson, HttpStatus.BAD_REQUEST);
		}
		return UserDetailsImpl.build(user.get());
	}

}
