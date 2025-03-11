package com.br.statisticsapi.services;

import com.br.statisticsapi.domains.Transaction;
import com.br.statisticsapi.dtos.input.TransactionRequest;
import com.br.statisticsapi.insfrastructure.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(TransactionRequest transactionRequest) {
        var transaction = new Transaction(transactionRequest.value(), transactionRequest.dateTime());
        return transactionRepository.save(transaction);
    }
}
