package com.br.statisticsapi.services;

import com.br.statisticsapi.domains.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class AvgTransactionsImpl implements CalculateTransactionStatisticService<Map<String, BigDecimal>> {

    @Override
    public Map<String, BigDecimal> getStatistic(List<Transaction> transactions) {

        if(Objects.isNull(transactions) || transactions.isEmpty()) {
            return Map.of(CalculateTransactionStatisticService.AVG, BigDecimal.ZERO);
        }

        var quantities = new BigDecimal(transactions.size());
        var totalValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var avg = totalValue.divide(quantities, 2, RoundingMode.HALF_UP);

        return Map.of(CalculateTransactionStatisticService.AVG, avg);
    }
}
