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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AvgTransactionsImplTest {

    @InjectMocks
    private AvgTransactionsImpl avgTransactions;

    @Test
    void testGetStatisticWithNullListShouldReturnZeroAvg() {
        Map<String, BigDecimal> result = avgTransactions.getStatistic(null);
        assertEquals(BigDecimal.ZERO, result.get(CalculateTransactionStatisticService.AVG));
    }

    @Test
    void testGetStatisticWithEmptyListShouldReturnZeroAvg() {
        Map<String, BigDecimal> result = avgTransactions.getStatistic(List.of());
        assertEquals(BigDecimal.ZERO, result.get(CalculateTransactionStatisticService.AVG));
    }

    @Test
    void testGetStatisticWithMultipleTransactionsShouldReturnCorrectAvg() {
        List<Transaction> transactions = List.of(
                new Transaction(new BigDecimal("10.00"), OffsetDateTime.now()),
                new Transaction(new BigDecimal("20.00"), OffsetDateTime.now()),
                new Transaction(new BigDecimal("30.00"), OffsetDateTime.now())
        );

        Map<String, BigDecimal> result = avgTransactions.getStatistic(transactions);
        assertEquals(new BigDecimal("20.00"), result.get(CalculateTransactionStatisticService.AVG));
    }
}