package com.br.statisticsapi.services;

import com.br.statisticsapi.domains.Transaction;
import com.br.statisticsapi.dtos.input.TransactionRequest;

public interface TransactionService {
    Transaction createTransaction(TransactionRequest transactionRequest);
}
