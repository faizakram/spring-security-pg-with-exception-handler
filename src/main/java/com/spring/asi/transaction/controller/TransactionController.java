package com.spring.asi.transaction.controller;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.asi.transaction.dto.RefundTransaction;
import com.spring.asi.transaction.dto.ResponseJson;
import com.spring.asi.transaction.exception.ServiceException;
import com.spring.asi.transaction.service.TransactionService;
import com.spring.asi.transaction.utils.CommonConstant;
import com.spring.asi.transaction.utils.ErrorConstant;
import com.spring.asi.transaction.utils.MappingConstant;

@RestController
@RequestMapping(MappingConstant.TRANSACTION_URL)
public class TransactionController {

	private Environment enviornmnet;
	private TransactionService transactionService;

	public TransactionController(Environment enviornmnet, TransactionService transactionService) {
		this.transactionService = transactionService;
		this.enviornmnet = enviornmnet;
	}

	@GetMapping(MappingConstant.SEARCH)
	public ResponseEntity<ResponseJson> searchTransaction() {
		ResponseJson responseJson = new ResponseJson(enviornmnet);
		responseJson.setResponseDescription(CommonConstant.S0001_SUCCESS_DESCRIPTION);
		responseJson.setResponse(transactionService.searchTransaction());
		return ResponseEntity.ok(responseJson);
	}

	@GetMapping(MappingConstant.ID)
	public ResponseEntity<ResponseJson> getByTransactionId(@PathVariable Long id) {
		ResponseJson responseJson = new ResponseJson(enviornmnet);
		responseJson.setResponseDescription(CommonConstant.S0001_SUCCESS_DESCRIPTION);
		responseJson.setResponse(transactionService.getByTransactionId(id));
		return ResponseEntity.ok(responseJson);
	}

	@PutMapping(MappingConstant.REFUND)
	public ResponseEntity<ResponseJson> getByTransactionId(@RequestBody RefundTransaction refundTransaction) {
		ResponseJson responseJson = new ResponseJson(enviornmnet);
		responseJson.setResponseDescription(CommonConstant.S0001_SUCCESS_DESCRIPTION);
		responseJson.setResponse(transactionService.getByTransactionId(refundTransaction));
		return ResponseEntity.ok(responseJson);
	}
	
	@GetMapping("test-success")
	public ResponseEntity<ResponseJson> testSuccess() {
		ResponseJson responseJson = new ResponseJson(enviornmnet);
		responseJson.setResponseDescription(CommonConstant.S0001_SUCCESS_DESCRIPTION);
		return ResponseEntity.ok(responseJson);
	}
	
	@GetMapping("test-Error")
	public ResponseEntity<ResponseJson> testError() {
		ResponseJson responseJson = new ResponseJson(enviornmnet, ErrorConstant.E1000_ERROR_CODE, ErrorConstant.E1000_ERROR_DESCRIPTION);
		throw new ServiceException(responseJson, HttpStatus.BAD_REQUEST);
		
	}

}
