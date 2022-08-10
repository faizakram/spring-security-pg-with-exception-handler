package com.spring.asi.transaction.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.spring.asi.transaction.utils.ConfigurationConstant;

@Configuration
@PropertySource({ ConfigurationConstant.SUCCESS_PROPERTIES, ConfigurationConstant.ERROR_PROPERTIES })
public class WebConfig {
	
}
