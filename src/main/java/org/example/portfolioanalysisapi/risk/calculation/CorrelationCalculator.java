package org.example.portfolioanalysisapi.risk.calculation;

import org.example.portfolioanalysisapi.risk.calculation.support.StatisticsCalculator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class CorrelationCalculator {

    private final StatisticsCalculator statisticsCalculator;

    public CorrelationCalculator(StatisticsCalculator statisticsCalculator) {
        this.statisticsCalculator = statisticsCalculator;
    }

    public BigDecimal calculateCorrelation(List<BigDecimal> returnsA, List<BigDecimal> returnsB) {

        if (returnsA.size() != returnsB.size()) {
            throw new IllegalArgumentException("Returns lists must have the same size");
        }

        if (returnsA.size() < 2) {
            throw new IllegalArgumentException("At least two overlapping return observations are required");
        }

        BigDecimal sumNumerator = BigDecimal.ZERO;
        BigDecimal sumSquaredA = BigDecimal.ZERO;
        BigDecimal sumSquaredB = BigDecimal.ZERO;

        BigDecimal meanA = statisticsCalculator.calculateMean(returnsA);
        BigDecimal meanB = statisticsCalculator.calculateMean(returnsB);


        for (int i = 0; i < returnsA.size(); i++ ) {

            BigDecimal deviationA = returnsA.get(i).subtract(meanA);
            BigDecimal deviationB = returnsB.get(i).subtract(meanB);

            sumNumerator = sumNumerator.add(deviationA.multiply(deviationB));
            sumSquaredA = sumSquaredA.add(deviationA.multiply(deviationA));
            sumSquaredB = sumSquaredB.add(deviationB.multiply(deviationB));

        }

        if (sumSquaredA.signum() == 0 || sumSquaredB.signum() == 0) {
            throw new IllegalArgumentException(
                    "Correlation cannot be calculated because one series has zero variance"
            );
        }

        double denominator = Math.sqrt(sumSquaredA.doubleValue() * sumSquaredB.doubleValue());

        return sumNumerator.divide(BigDecimal.valueOf(denominator), 6, RoundingMode.HALF_UP);
    }
}
