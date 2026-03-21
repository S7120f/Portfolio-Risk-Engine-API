package org.example.portfolioanalysisapi.risk.dto;

import java.math.BigDecimal;

public class MaxDrawdownResponse {

    private String ticker;
    private BigDecimal maxDrawdown;

    public BigDecimal getMaxDrawdown() {
        return maxDrawdown;
    }

    public void setMaxDrawdown(BigDecimal maxDrawdown) {
        this.maxDrawdown = maxDrawdown;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
}
