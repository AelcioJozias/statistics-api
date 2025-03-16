package com.br.statisticsapi.services;

import com.br.statisticsapi.domains.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CountCalculateTransactionImpl implements CalculateTransactionStatisticService<Map<String, Integer>> {

    @Override
    public Map<String, Integer> getStatistic(List<Transaction> transactions) {
        if(Objects.isNull(transactions) || transactions.isEmpty()) {
            return Map.of(CalculateTransactionStatisticService.COUNT, 0);
        }
        return Map.of(CalculateTransactionStatisticService.COUNT, transactions.size());
    }
}
