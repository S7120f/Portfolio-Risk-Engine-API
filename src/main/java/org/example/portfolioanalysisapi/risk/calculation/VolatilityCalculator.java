package org.example.portfolioanalysisapi.risk.calculation;

import org.example.portfolioanalysisapi.marketdata.PricePoint;
import org.example.portfolioanalysisapi.risk.calculation.support.ReturnSeriesCalculator;
import org.example.portfolioanalysisapi.risk.calculation.support.StatisticsCalculator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.math.RoundingMode.HALF_UP;

@Component
public class VolatilityCalculator {

    private final ReturnSeriesCalculator returnSeriesCalculator;
    private final StatisticsCalculator statisticsCalculator;

    public VolatilityCalculator(ReturnSeriesCalculator returnSeriesCalculator, StatisticsCalculator statisticsCalculator) {
        this.returnSeriesCalculator = returnSeriesCalculator;
        this.statisticsCalculator = statisticsCalculator;
    }


    public BigDecimal calculateVolatility(List<PricePoint> pricePoints) {

        List<BigDecimal> returns = returnSeriesCalculator.dailyReturns(pricePoints);
        BigDecimal variance = statisticsCalculator.calculateVariance(returns);

        double sqrt = Math.sqrt(variance.doubleValue());
        return BigDecimal.valueOf(sqrt).setScale(6, HALF_UP);
    }

}
