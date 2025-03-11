package com.br.statisticsapi.dtos.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransactionRequest(@NotNull @JsonProperty("valor") BigDecimal value,
                                 @NotNull @JsonProperty("dataHora") OffsetDateTime dateTime)
{}
