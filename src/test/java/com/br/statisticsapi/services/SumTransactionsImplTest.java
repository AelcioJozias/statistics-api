package com.br.statisticsapi.services;

import com.br.statisticsapi.domains.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class SumTransactionsImplTest {

    @InjectMocks
    private SumTransactionsImpl sumTransactions;

    @Test
    void testGetStatisticWithNullTransactions() {
        Map<String, BigDecimal> result = sumTransactions.getStatistic(null);
        assertEquals(BigDecimal.ZERO, result.get(CalculateTransactionStatisticService.SUM));
    }

    @Test
    void testGetStatisticWithEmptyTransactions() {
        Map<String, BigDecimal> result = sumTransactions.getStatistic(List.of());
        assertEquals(BigDecimal.ZERO, result.get(CalculateTransactionStatisticService.SUM ));
    }

    @Test
    void testGetStatisticWithOneTransaction() {
        
        Transaction transaction = new Transaction(new BigDecimal("100.50"), OffsetDateTime.now());
        Map<String, BigDecimal> result = sumTransactions.getStatistic(List.of(transaction));

        assertEquals(new BigDecimal("100.50"), result.get(CalculateTransactionStatisticService.SUM));
    }

    @Test
    void testGetStatisticWithMultipleTransactions() {
        Transaction transaction1 = new Transaction(new BigDecimal("100.50"), OffsetDateTime.now());
        Transaction transaction2 = new Transaction(new BigDecimal("200.75"), OffsetDateTime.now());
        Map<String, BigDecimal> result = sumTransactions.getStatistic(List.of(transaction1, transaction2));
        assertEquals(new BigDecimal("301.25"), result.get(CalculateTransactionStatisticService.SUM));
    }

}