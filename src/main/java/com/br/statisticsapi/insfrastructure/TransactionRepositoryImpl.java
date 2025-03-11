package com.br.statisticsapi.insfrastructure;

import com.br.statisticsapi.domains.Transaction;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository{

    /**
     * Its variable transactions is to keep all transactions in the last minute, in each request made it.
     * With that, after each request, the program 'll verify more fast the transactions in the last minute,
     * because it 'll have fewer transactions to verify.
     */
    private static final List<Transaction> transactions = new CopyOnWriteArrayList<>();

    /**
     * It variable old transaction is just to keep all history of transactions
     */
    private static final List<Transaction> oldTransactions = new CopyOnWriteArrayList<>();

    private static final AtomicLong sequenceId = new AtomicLong(1);

    private synchronized Long generateId() {
        return sequenceId.getAndIncrement();
    }

    @Override
    public Transaction save(Transaction transaction) {
        transaction.setId(generateId());
        transactions.add(transaction);
        return transaction;
    }

    @Override
    public List<Transaction> getTransactionsInTheLastOneMinute() {

        List<Transaction> lastMinuteTransactions = new ArrayList<>();
        List<Transaction> expiredTransactions = new ArrayList<>();

        var currentDateTime = OffsetDateTime.now();
        OffsetDateTime oneMinuteAgo = currentDateTime.minusMinutes(1);

        for (Transaction transaction : transactions) {
            OffsetDateTime transactionTime = transaction.getDateTime();

            if ((!transactionTime.isBefore(oneMinuteAgo)) && (!transactionTime.isAfter(currentDateTime))) {
                lastMinuteTransactions.add(transaction);
            } else {
                expiredTransactions.add(transaction);
            }
        }

        oldTransactions.addAll(expiredTransactions);
        transactions.removeAll(expiredTransactions);

        return lastMinuteTransactions;
    }

    @Override
    public void deleteAll() {
        transactions.clear();
        oldTransactions.clear();
    }

}
