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
     * Generates a TransactionStatisticsResponse from a list of transactions.
     *
     * @param transactionsList the list of transactions
     * @return a TransactionStatisticsResponse containing the calculated statistics
     */
    @SuppressWarnings("unchecked")
    private TransactionStatisticsResponse getTransactionStatisticsResponse(List<Transaction> transactionsList) {
        List<Map<String, Object>> statiticsList = calculateTransactionStatisticService.stream()
                .map(e -> (Map<String, Object>) e.getStatistic(transactionsList))
                .toList();
        Map<String, Object> statisticsMap = TransactionStatisticsMapper.joinMapsFromTransactionStatistics(statiticsList);
        return TransactionStatisticsMapper.mapToTransactionStatisticsResponse(statisticsMap);
    }

    /**
     * Retrieves the transaction statistics for the last one minute.
     *
     * @return a TransactionStatisticsResponse containing the statistics
     */
    @Override
    public TransactionStatisticsResponse getStatisticsInTheLastOneMinute() {
        List<Transaction> transactionsInTheLastOneMinute = transactionRepository.getTransactionsInTheLastOneMinute();
        return getTransactionStatisticsResponse(transactionsInTheLastOneMinute);
    }

    /**
     * Retrieves all the transactions already registered.
     *
     * @return a TransactionStatisticsResponse containing the statistics
     */
    @Override
    public TransactionStatisticsResponse getAllTransactionsStatistics() {
        List<Transaction> transactionsInTheLastOneMinute = transactionRepository.getAll();
        return getTransactionStatisticsResponse(transactionsInTheLastOneMinute);
    }
}
