package com.br.statisticsapi.mappers;

import com.br.statisticsapi.dtos.output.TransactionStatisticsResponse;
import com.br.statisticsapi.services.CalculateTransactionStatisticService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.br.statisticsapi.services.CalculateTransactionStatisticService.*;
import static com.br.statisticsapi.services.CalculateTransactionStatisticService.AVG;
import static java.math.BigDecimal.ZERO;
import static java.util.Objects.isNull;

/**
 * Utility class for mapping transaction statistics.
 */
public class TransactionStatisticsMapper {

    /**
     * Maps a statistics map to a TransactionStatisticsResponse object.
     *
     * @param statistics a map containing transaction statistics
     * @return a TransactionStatisticsResponse object containing the mapped statistics
     */
    public static TransactionStatisticsResponse mapToTransactionStatisticsResponse(Map<String, Object> statistics) {
        if(isNull(statistics) || statistics.isEmpty()) {
            return new TransactionStatisticsResponse(0, ZERO, ZERO, ZERO, ZERO);
        }

        var count = (Integer) statistics.get(COUNT);
        var avg = (BigDecimal) statistics.get(AVG);
        var max = (BigDecimal) statistics.get(MAX);
        var min = (BigDecimal) statistics.get(MIN);
        var sum = (BigDecimal) statistics.get(SUM);

        return new TransactionStatisticsResponse(count, avg, max, min, sum);
    }

    /**
     * Joins a list of maps containing transaction statistics into a single map.
     *
     * @param statistics a list of maps, each containing transaction statistics
     * @return a single map containing all the entries from the input maps
     */
    public static Map<String, Object> joinMapsFromTransactionStatistics(List<Map<String, Object>> statistics) {
        return statistics.stream()
            .flatMap(map -> map.entrySet().stream())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1));
    }
}
