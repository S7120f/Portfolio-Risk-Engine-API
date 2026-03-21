package org.example.portfolioanalysisapi.risk.dto;

import java.math.BigDecimal;

public class VolatilityResponse {

    private String ticker;
    private BigDecimal volatility;

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public BigDecimal getVolatility() {
        return volatility;
    }

    public void setVolatility(BigDecimal volatility) {
        this.volatility = volatility;
    }
}
