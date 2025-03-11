package com.br.statisticsapi.insfrastructure;

import com.br.statisticsapi.domains.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class TransactionRepositoryImplTest {

    private final TransactionRepositoryImpl repository = new TransactionRepositoryImpl();

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void save() {
        Transaction transaction = new Transaction(new BigDecimal("12.00"), OffsetDateTime.now().minusSeconds(30));

        Transaction savedTransaction = repository.save(transaction);

        assertNotNull(savedTransaction.getId());
        assertEquals(1, repository.getTransactionsInTheLastOneMinute().size());
    }

    @Test
    void getTransactionsInTheLastOneMinute() {
        Transaction transaction1 = new Transaction(new BigDecimal("12.00"), OffsetDateTime.now().minusSeconds(30));
        repository.save(transaction1);

        Transaction transaction2 = new Transaction(new BigDecimal("12.00"), OffsetDateTime.now().minusSeconds(90));
        repository.save(transaction2);

        List<Transaction> transactions = repository.getTransactionsInTheLastOneMinute();

        assertEquals(1, transactions.size());
        assertEquals(transaction1.getId(), transactions.get(0).getId());
    }

    @Test
    void testGenerationIdInMultipleCreationsSimultaneous() throws InterruptedException {
        try (ExecutorService executorService = Executors.newFixedThreadPool(50)) {
            for (int i = 0; i < 500; i++) {
                executorService.submit(() -> {
                    Transaction transaction = new Transaction(new BigDecimal("12.00"), OffsetDateTime.now());
                    repository.save(transaction);
                });
            }
            executorService.shutdown();
            boolean isExecutorSuccessfullyFinished = executorService.awaitTermination(1, TimeUnit.MINUTES);

            var transactionsInTheLastOneMinute = repository.getTransactionsInTheLastOneMinute();
            Set<Transaction> transactionSetList = new HashSet<>(transactionsInTheLastOneMinute);

            assertEquals(transactionsInTheLastOneMinute.size(), transactionSetList.size());
            assertTrue(isExecutorSuccessfullyFinished);
        }
    }

}