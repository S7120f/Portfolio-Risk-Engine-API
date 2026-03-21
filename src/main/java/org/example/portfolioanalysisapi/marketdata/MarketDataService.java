package org.example.portfolioanalysisapi.marketdata;

import org.example.portfolioanalysisapi.asset.Asset;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MarketDataService {




    public List<PricePoint> getPriceHistory(String ticker){
        List<PricePoint> pricepoints = new ArrayList<>();
        switch (ticker) {
            case "AAPL" -> {
                pricepoints.add(new PricePoint(LocalDate.of(2026, 3, 13), new BigDecimal("32")));
                pricepoints.add(new PricePoint(LocalDate.of(2026, 3, 14), new BigDecimal("24")));
                pricepoints.add(new PricePoint(LocalDate.of(2026, 3, 15), new BigDecimal("3")));
            }
            case "DDL" -> {
                pricepoints.add(new PricePoint(LocalDate.of(2026, 3, 13), new BigDecimal("234")));
                pricepoints.add(new PricePoint(LocalDate.of(2026, 3, 14), new BigDecimal("223")));
                pricepoints.add(new PricePoint(LocalDate.of(2026, 3, 15), new BigDecimal("202")));
            }
            case "BBL" -> {
                pricepoints.add(new PricePoint(LocalDate.of(2026, 3, 13), new BigDecimal("52.00")));
                pricepoints.add(new PricePoint(LocalDate.of(2026, 3, 14), new BigDecimal("42.10")));
                pricepoints.add(new PricePoint(LocalDate.of(2026, 3, 15), new BigDecimal("66.95")));
            }
            case null, default -> throw new IllegalArgumentException("No such ticker found");
        }
        return pricepoints;
    }





    }
