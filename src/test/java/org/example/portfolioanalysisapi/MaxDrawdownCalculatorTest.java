package org.example.portfolioanalysisapi;


import org.assertj.core.api.Assertions;
import org.example.portfolioanalysisapi.marketdata.PricePoint;
import org.example.portfolioanalysisapi.risk.calculation.MaxDrawdownCalculator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class MaxDrawdownCalculatorTest {

    private final MaxDrawdownCalculator maxDrawdownCalculator = new MaxDrawdownCalculator();


    @Test
    void shouldReturnMaxDrawdown() {


        List<PricePoint> pricePoints = List.of(
                new PricePoint(LocalDate.of(2024, 1, 1), new BigDecimal("80")),
                new PricePoint(LocalDate.of(2024, 1, 2), new BigDecimal("110")),
                new PricePoint(LocalDate.of(2024, 1, 3), new BigDecimal("121")),
                new PricePoint(LocalDate.of(2024, 1, 4), new BigDecimal("111")),
                new PricePoint(LocalDate.of(2024, 1, 4), new BigDecimal("140")),
                new PricePoint(LocalDate.of(2024, 1, 4), new BigDecimal("90")),
                new PricePoint(LocalDate.of(2024, 1, 4), new BigDecimal("130")),
                new PricePoint(LocalDate.of(2024, 1, 4), new BigDecimal("140"))
        );

        BigDecimal result = maxDrawdownCalculator.calculateMaxDrawdown(pricePoints);
        Assertions.assertThat(result).isEqualByComparingTo("-0.357143");
    }

    @Test
    void shouldReturnZeroWhenPricesOnlyIncrease () {

        List<PricePoint> pricePoints = List.of(
                new PricePoint(LocalDate.of(2024, 1, 1), new BigDecimal("80")),
                new PricePoint(LocalDate.of(2024, 1, 2), new BigDecimal("110")),
                new PricePoint(LocalDate.of(2024, 1, 3), new BigDecimal("120")),
                new PricePoint(LocalDate.of(2024, 1, 4), new BigDecimal("130")),
                new PricePoint(LocalDate.of(2024, 1, 4), new BigDecimal("140"))
        );

        BigDecimal result = maxDrawdownCalculator.calculateMaxDrawdown(pricePoints);
        Assertions.assertThat(result).isEqualByComparingTo("0");
    }

}
