package com.br.statisticsapi.services;

import com.br.statisticsapi.domains.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class TransactionsMaximumValueImpl implements CalculateTransactionStatisticService<Map<String, BigDecimal>> {
    @Override
    public Map<String, BigDecimal> getStatistic(List<Transaction> transactions) {

        if(Objects.isNull(transactions) || transactions.isEmpty()) {
            return Map.of(CalculateTransactionStatisticService.MAX, BigDecimal.ZERO);
        }

        var maxValue = transactions.stream().map(Transaction::getValue).max(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
        return Map.of(CalculateTransactionStatisticService.MAX, maxValue);
    }

}
