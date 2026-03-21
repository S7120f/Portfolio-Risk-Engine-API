package org.example.portfolioanalysisapi.risk.calculation.support;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import static java.math.RoundingMode.HALF_UP;

@Component
public class StatisticsCalculator {


    public BigDecimal calculateMean(List<BigDecimal> returns) {
        BigDecimal sum = BigDecimal.ZERO;

        if (returns.isEmpty()) {
            throw new IllegalArgumentException("Values list is empty");
        }

        for (int i = 0; i <returns.size(); i++) {
            sum = sum.add(returns.get(i));
        }
        return sum.divide(BigDecimal.valueOf(returns.size()), 6, HALF_UP);
    }

    public BigDecimal calculateVariance(List<BigDecimal> values) {

        BigDecimal mean = calculateMean(values);
        BigDecimal value;
        BigDecimal deviation;
        BigDecimal squared;
        BigDecimal sum = BigDecimal.ZERO;

        for (int i = 0; i <  values.size(); i++) {

            value = values.get(i);
            deviation = (value.subtract(mean));
            squared = deviation.multiply(deviation);
            sum = sum.add(squared);
        }
        return sum.divide(BigDecimal.valueOf(values.size()), 6, HALF_UP);
    }

}
