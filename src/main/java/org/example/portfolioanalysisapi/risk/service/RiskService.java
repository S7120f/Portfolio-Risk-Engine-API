package org.example.portfolioanalysisapi.risk.service;


import lombok.extern.slf4j.Slf4j;
import org.example.portfolioanalysisapi.marketdata.MarketDataController;
import org.example.portfolioanalysisapi.marketdata.MarketDataService;
import org.example.portfolioanalysisapi.marketdata.PricePoint;
import org.example.portfolioanalysisapi.risk.calculation.CorrelationCalculator;
import org.example.portfolioanalysisapi.risk.calculation.MaxDrawdownCalculator;
import org.example.portfolioanalysisapi.risk.calculation.VolatilityCalculator;
import org.example.portfolioanalysisapi.risk.calculation.support.ReturnSeriesCalculator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class RiskService {

    final private MarketDataService marketDataService;
    final private VolatilityCalculator volatilityCalculator;
    final private MaxDrawdownCalculator maxDrawdownCalculator;
    final private CorrelationCalculator correlationCalculator;
    final private ReturnSeriesCalculator returnSeriesCalculator;

    public RiskService(MarketDataService marketDataService, VolatilityCalculator volatilityCalculator,
                       MaxDrawdownCalculator maxDrawdownCalculator, CorrelationCalculator correlationCalculator,
                       ReturnSeriesCalculator returnSeriesCalculator) {
        this.marketDataService = marketDataService;
        this.volatilityCalculator = volatilityCalculator;
        this.maxDrawdownCalculator = maxDrawdownCalculator;
        this.correlationCalculator = correlationCalculator;
        this.returnSeriesCalculator = returnSeriesCalculator;
    }

    public BigDecimal getVolatilityForTicker(String ticker) {

        List<PricePoint> pricePoints = marketDataService.getPriceHistory(ticker);
        return volatilityCalculator.calculateVolatility(pricePoints);
    }

    public BigDecimal getMaxDaxDrawdownForTicker(String ticker) {

        List<PricePoint> pricePoints = marketDataService.getPriceHistory(ticker);
        return maxDrawdownCalculator.calculateMaxDrawdown(pricePoints);
    }

    public BigDecimal getCorrelationForTicker(String tickerA, String tickerB) {

        List<PricePoint> pricePointsA = marketDataService.getPriceHistory(tickerA);
        List<PricePoint> pricePointsB = marketDataService.getPriceHistory(tickerB);

        List<BigDecimal> returnA = returnSeriesCalculator.dailyReturns(pricePointsA);
        List<BigDecimal> returnB = returnSeriesCalculator.dailyReturns(pricePointsB);

        return correlationCalculator.calculateCorrelation(returnA, returnB);

    }

}
