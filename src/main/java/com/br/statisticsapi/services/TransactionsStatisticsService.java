package com.br.statisticsapi.services;

import com.br.statisticsapi.dtos.output.TransactionStatisticsResponse;

public interface TransactionsStatisticsService {
    TransactionStatisticsResponse getStatisticsInTheLastOneMinute();
}
