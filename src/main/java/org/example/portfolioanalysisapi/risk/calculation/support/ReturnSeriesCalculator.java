package org.example.portfolioanalysisapi.risk.calculation.support;

import org.example.portfolioanalysisapi.marketdata.PricePoint;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.math.RoundingMode.HALF_UP;

@Component
public class ReturnSeriesCalculator {



    public List<BigDecimal> dailyReturns(List<PricePoint> pricePoints) {

        if (pricePoints.size() < 2 ){
            throw new IllegalArgumentException("At least two price points are required");
        }

        BigDecimal current;
        BigDecimal previous;
        BigDecimal result;

        List<BigDecimal> returns = new ArrayList<>();

        for (int i = 1; i < pricePoints.size(); i++) {

            current = pricePoints.get(i).getClosePrice();
            previous = pricePoints.get(i -1).getClosePrice();

            result = (current.subtract(previous)).divide(previous, 6, HALF_UP);

            returns.add(result);

        }

        return returns;
    }
}
