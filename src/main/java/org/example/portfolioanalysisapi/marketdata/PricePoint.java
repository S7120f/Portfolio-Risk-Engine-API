package org.example.portfolioanalysisapi.marketdata;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PricePoint {

    private LocalDate date;
    private BigDecimal closePrice;

    public PricePoint(LocalDate date, BigDecimal closePrice) {
        this.date = date;
        this.closePrice = closePrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(BigDecimal closePrice) {
        this.closePrice = closePrice;
    }
}
