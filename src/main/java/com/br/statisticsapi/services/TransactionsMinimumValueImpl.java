package com.br.statisticsapi.services;

import com.br.statisticsapi.domains.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class TransactionsMinimumValueImpl implements CalculateTransactionStatisticService<Map<String, BigDecimal>> {

    @Override
    public Map<String, BigDecimal> getStatistic(List<Transaction> transactions) {
        if(Objects.isNull(transactions) || transactions.isEmpty()) {
            return Map.of(CalculateTransactionStatisticService.MIN, BigDecimal.ZERO);
        }

        var min = transactions.stream().map(Transaction::getValue).min(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
        return Map.of(CalculateTransactionStatisticService.MIN, min);
    }
}
