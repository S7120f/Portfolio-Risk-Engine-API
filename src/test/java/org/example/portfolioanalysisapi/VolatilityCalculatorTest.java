package org.example.portfolioanalysisapi;

import org.assertj.core.api.Assertions;
import org.example.portfolioanalysisapi.marketdata.PricePoint;
import org.example.portfolioanalysisapi.risk.calculation.VolatilityCalculator;
import org.example.portfolioanalysisapi.risk.calculation.support.ReturnSeriesCalculator;
import org.example.portfolioanalysisapi.risk.calculation.support.StatisticsCalculator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class  VolatilityCalculatorTest {

    private final VolatilityCalculator calculator;
    private final ReturnSeriesCalculator returnSeriesCalculator;
    private final StatisticsCalculator statisticsCalculator;

    public VolatilityCalculatorTest(VolatilityCalculator calculator, ReturnSeriesCalculator returnSeriesCalculator, StatisticsCalculator statisticsCalculator) {

        this.calculator= calculator;
        this.returnSeriesCalculator = returnSeriesCalculator;
        this.statisticsCalculator = statisticsCalculator;
    }

    @Test
    void shouldCalculateDailyReturns() {

        List<PricePoint> pricePoints = List.of(
                new PricePoint(LocalDate.of(2024, 1, 1), new BigDecimal("100")),
                new PricePoint(LocalDate.of(2024, 1, 2), new BigDecimal("110")),
                new PricePoint(LocalDate.of(2024, 1, 3), new BigDecimal("121")),
                new PricePoint(LocalDate.of(2024, 1, 4), new BigDecimal("121"))
        );

        List<BigDecimal> result = returnSeriesCalculator.dailyReturns(pricePoints);

        Assertions.assertThat(result).hasSize(3);
        Assertions.assertThat(result.get(0)).isEqualByComparingTo("0.100000");
        Assertions.assertThat(result.get(1)).isEqualByComparingTo("0.100000");
        Assertions.assertThat(result.get(2)).isEqualByComparingTo("0.000000");
    }

    @Test
    void shouldReturnEmptyListWhenOnePricePointExists() {

        List<PricePoint> pricePoints = List.of(
                new PricePoint(LocalDate.of(2024, 1, 1), new BigDecimal("100"))
        );

        List<BigDecimal> result = returnSeriesCalculator.dailyReturns(pricePoints);
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    void shouldCalculateMeanOfValues() {

        List<BigDecimal> values = List.of(
                new BigDecimal("0.100000"),
                new BigDecimal("0.100000"),
                new BigDecimal("0.000000")
        );

        BigDecimal mean = statisticsCalculator.calculateMean(values);
        Assertions.assertThat(mean).isEqualTo("0.066667");
    }

    @Test
    void shouldGiveVariance() {

        List<BigDecimal> values = List.of(
                new BigDecimal("0.100000"),
                new BigDecimal("0.100000"),
                new BigDecimal("0.000000")

        );

        BigDecimal result = statisticsCalculator.calculateVariance(values);
        Assertions.assertThat(result).isEqualTo(new BigDecimal("0.002222"));
    }

    @Test
    void shouldCalculateVolatility() {

        List<PricePoint> pricePoints = List.of(
                new PricePoint(LocalDate.of(2024, 1, 1), new BigDecimal("100")),
                new PricePoint(LocalDate.of(2024, 1, 2), new BigDecimal("110")),
                new PricePoint(LocalDate.of(2024, 1, 3), new BigDecimal("121")),
                new PricePoint(LocalDate.of(2024, 1, 4), new BigDecimal("121"))
        );

        BigDecimal volatility = calculator.calculateVolatility(pricePoints);
        Assertions.assertThat(volatility).isEqualByComparingTo(new BigDecimal("0.047138"));
    }
}
