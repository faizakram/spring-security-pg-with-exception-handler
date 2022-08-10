package com.spring.asi.transaction.service;

import com.spring.asi.transaction.dto.RefundTransaction;

public interface TransactionService {

	Object searchTransaction();

	Object getByTransactionId(Long id);

	Object getByTransactionId(RefundTransaction refundTransaction);

}
