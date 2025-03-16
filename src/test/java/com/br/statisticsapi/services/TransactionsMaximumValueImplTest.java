package com.br.statisticsapi.services;

import com.br.statisticsapi.domains.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionsMaximumValueImplTest {

    private final TransactionsMaximumValueImpl service = new TransactionsMaximumValueImpl();

    @Test
    void returnsZeroWhenTransactionsListIsNull() {
        
        Map<String, BigDecimal> result = service.getStatistic(null);
        assertEquals(BigDecimal.ZERO, result.get(CalculateTransactionStatisticService.MAX));
    }

    @Test
    void returnsZeroWhenTransactionsListIsEmpty() {
        Map<String, BigDecimal> result = service.getStatistic(List.of());
        assertEquals(BigDecimal.ZERO, result.get(CalculateTransactionStatisticService.MAX));
    }

    @Test
    void returnsMaximumValueWhenTransactionsListHasOneTransaction() {
        Transaction transaction = new Transaction(new BigDecimal("100.00"), OffsetDateTime.now());
        Map<String, BigDecimal> result = service.getStatistic(List.of(transaction));
        assertEquals(new BigDecimal("100.00"), result.get(CalculateTransactionStatisticService.MAX));
    }

    @Test
    void returnsMaximumValueWhenTransactionsListHasMultipleTransactions() {
        Transaction transaction1 = new Transaction(new BigDecimal("100.00"), OffsetDateTime.now());
        Transaction transaction2 = new Transaction(new BigDecimal("200.00"), OffsetDateTime.now());
        Transaction transaction3 = new Transaction(new BigDecimal("150.00"), OffsetDateTime.now());
        Map<String, BigDecimal> result = service.getStatistic(List.of(transaction1, transaction2, transaction3));
        assertEquals(new BigDecimal("200.00"), result.get(CalculateTransactionStatisticService.MAX));
    }
}