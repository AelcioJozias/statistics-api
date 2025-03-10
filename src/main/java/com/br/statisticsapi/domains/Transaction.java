package com.br.statisticsapi.domains;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static java.math.RoundingMode.HALF_UP;
import static java.util.Objects.isNull;

public class Transaction {

    public static final int DECIMAL_SCALE = 2;

    private final Long id;
    private final BigDecimal value;
    private final OffsetDateTime dateTime;

    public Transaction(BigDecimal value, OffsetDateTime dateTime) {
        this.id = 1L;
        this.value = validValue(value);
        this.dateTime = validDateTime(dateTime);
    }

    private OffsetDateTime validDateTime(OffsetDateTime dateTime) {
        isDateNotNull(dateTime);
        isDateTimeOnPast(dateTime);
        return dateTime;
    }

    private static void isDateNotNull(OffsetDateTime dataHora) {
        if (isNull(dataHora)) {
            throw new IllegalArgumentException("Date and tie cannot be null");
        }
    }

    private void isDateTimeOnPast(OffsetDateTime dataHora) {
        if (dataHora.isAfter(OffsetDateTime.now())) {
            throw new IllegalArgumentException("Date and time cannot be in the future");
        }
    }

    private BigDecimal validValue(BigDecimal valor) {
        isValueNotNull(valor);
        isValueBiggerOrEqualsZero(valor);
        return roundValue(valor);
    }

    private static void isValueBiggerOrEqualsZero(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Value must be greater than or equal to zero");
        }
    }

    private static void isValueNotNull(BigDecimal valor) {
        if (isNull(valor)) {
            throw new IllegalArgumentException("Value cannot be null");
        }
    }

    private BigDecimal roundValue(BigDecimal valor) {
        return valor.setScale(DECIMAL_SCALE, HALF_UP);
    }

    // Getters methods
    public Long getId() {
        return id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public OffsetDateTime getDateTime() {
        return dateTime;
    }

}
