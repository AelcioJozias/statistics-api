package com.br.statisticsapi.services;

import com.br.statisticsapi.domains.Transaction;
import com.br.statisticsapi.dtos.output.TransactionStatisticsResponse;
import com.br.statisticsapi.insfrastructure.TransactionRepository;
import com.br.statisticsapi.mappers.TransactionStatisticsMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service implementation for calculating transaction statistics.
 */
@Service
public class TransactionsStatisticsServiceImpl implements TransactionsStatisticsService {

    private final TransactionRepository transactionRepository;
    private final List<CalculateTransactionStatisticService<?>> calculateTransactionStatisticService;

    public TransactionsStatisticsServiceImpl(TransactionRepository transactionRepository, List<CalculateTransactionStatisticService<?>> calculateTransactionStatisticService) {
        this.transactionRepository = transactionRepository;
        this.calculateTransactionStatisticService = calculateTransactionStatisticService;
    }

    /**
     * Retrieves the transaction statistics for the last one minute.
     *
     * @return a TransactionStatisticsResponse containing the statistics
     */
    @Override
    @SuppressWarnings("unchecked")
    public TransactionStatisticsResponse getStatisticsInTheLastOneMinute() {
        List<Transaction> transactionsInTheLastOneMinute = transactionRepository.getTransactionsInTheLastOneMinute();
        List<Map<String, Object>> statiticsList = calculateTransactionStatisticService.stream()
                .map(e -> (Map<String, Object>) e.getStatistic(transactionsInTheLastOneMinute))
                .toList();
        Map<String, Object> statisticsMap = TransactionStatisticsMapper.joinMapsFromTransactionStatistics(statiticsList);
        return TransactionStatisticsMapper.mapToTransactionStatisticsResponse(statisticsMap);
    }
}
