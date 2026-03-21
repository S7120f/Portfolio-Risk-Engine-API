package org.example.portfolioanalysisapi.risk.calculation;


import org.example.portfolioanalysisapi.marketdata.PricePoint;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class MaxDrawdownCalculator {




    public BigDecimal calculateMaxDrawdown(List<PricePoint> pricePoints) {

        BigDecimal current;
        BigDecimal peak;
        BigDecimal drawdown;
        BigDecimal maxDrawdown = BigDecimal.ZERO;

        if (pricePoints.isEmpty()) {
            throw  new IllegalArgumentException("No values in list");
        }

        peak = pricePoints.get(0).getClosePrice();

        for (int i = 1; i < pricePoints.size(); i++) {

            current = pricePoints.get(i).getClosePrice();

            if (current.compareTo(peak) > 0) {
                peak = current;
            } else {
                drawdown = (current.subtract(peak).divide(peak, 6, RoundingMode.HALF_UP));

                if (drawdown.compareTo(maxDrawdown) < 0) {
                    maxDrawdown = drawdown;
                }
            }
        }

        return maxDrawdown;
    }
}
