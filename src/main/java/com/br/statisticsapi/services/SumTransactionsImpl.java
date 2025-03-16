package com.br.statisticsapi.services;

import com.br.statisticsapi.domains.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class SumTransactionsImpl implements CalculateTransactionStatisticService<Map<String, BigDecimal>> {
    @Override
    public Map<String, BigDecimal> getStatistic(List<Transaction> transactions) {
        if(Objects.isNull(transactions) || transactions.isEmpty()) {
            return Map.of(CalculateTransactionStatisticService.SUM, BigDecimal.ZERO);
        }

        var total = transactions.stream().map(Transaction::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
        return Map.of(CalculateTransactionStatisticService.SUM, total);
    }
}
