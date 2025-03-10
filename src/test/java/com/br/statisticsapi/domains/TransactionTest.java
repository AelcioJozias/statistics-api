package com.br.statisticsapi.domains;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void testValidTransaction() {
        BigDecimal value = new BigDecimal("100.00");
        OffsetDateTime dateTime = OffsetDateTime.now().minusDays(1);
        Transaction transaction = new Transaction(value, dateTime);
        assertNotNull(transaction);
        assertEquals(value.setScale(Transaction.DECIMAL_SCALE, RoundingMode.HALF_UP), transaction.getValue());
        assertEquals(dateTime, transaction.getDateTime());
    }

    @Test
    void testNullValue() {
        OffsetDateTime dateTime = OffsetDateTime.now().minusDays(1);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Transaction(null, dateTime));
        assertEquals("Value cannot be null", exception.getMessage());
    }

    @Test
    void testNegativeValue() {
        BigDecimal value = new BigDecimal("-100.00");
        OffsetDateTime dateTime = OffsetDateTime.now().minusDays(1);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Transaction(value, dateTime));
        assertEquals("Value must be greater than or equal to zero", exception.getMessage());
    }

    @Test
    void testNullDateTime() {
        BigDecimal value = new BigDecimal("100.00");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Transaction(value, null));
        assertEquals("Date and tie cannot be null", exception.getMessage());
    }

    @Test
    void testFutureDateTime() {
        BigDecimal value = new BigDecimal("100.00");
        OffsetDateTime dateTime = OffsetDateTime.now().plusDays(1);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Transaction(value, dateTime));
        assertEquals("Date and time cannot be in the future", exception.getMessage());
    }

    @Test
    void testRoundValue() {
        BigDecimal value = new BigDecimal("100.005");
        OffsetDateTime dateTime = OffsetDateTime.now().minusDays(1);
        Transaction transaction = new Transaction(value, dateTime);
        assertEquals(new BigDecimal("100.01"), transaction.getValue());
    }
}