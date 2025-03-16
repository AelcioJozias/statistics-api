package com.br.statisticsapi.services;

import com.br.statisticsapi.domains.Transaction;

import java.util.List;

/**
 * Service interface for calculating transaction statistics.
 *
 * @param <T> the type of the statistic result
 */
public interface CalculateTransactionStatisticService<T> {
     String MIN = "min";
     String MAX = "max";
     String SUM = "sum";
     String AVG = "avg";
     String COUNT = "count";

     /**
      * Calculates the statistic for a given list of transactions.
      *
      * @param transactions the list of transactions to calculate the statistic for
      * @return the calculated statistic of type T
      */
     T getStatistic(List<Transaction> transactions);
}