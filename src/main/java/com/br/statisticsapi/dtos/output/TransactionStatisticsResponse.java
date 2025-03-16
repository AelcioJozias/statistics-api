package com.br.statisticsapi.dtos.output;

import java.math.BigDecimal;

public record TransactionStatisticsResponse(Integer count, BigDecimal avg, BigDecimal max, BigDecimal min, BigDecimal sum) {

}
