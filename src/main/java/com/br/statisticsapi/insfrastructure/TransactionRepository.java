package com.br.statisticsapi.insfrastructure;

import com.br.statisticsapi.domains.Transaction;

import java.util.List;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
    List<Transaction> getTransactionsInTheLastOneMinute();
    List<Transaction> getAll();
    void deleteAll();
}
