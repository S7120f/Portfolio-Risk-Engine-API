package org.example.portfolioanalysisapi.marketdata.dto;

import org.example.portfolioanalysisapi.marketdata.PricePoint;

import java.util.List;

public class MarketDataResponse {

    private String ticker;
    private List<PricePoint> prices;

    public MarketDataResponse(String ticker, List<PricePoint> prices) {
        this.ticker = ticker;
        this.prices = prices;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public List<PricePoint> getPrices() {
        return prices;
    }

    public void setPrices(List<PricePoint> prices) {
        this.prices = prices;
    }
}
