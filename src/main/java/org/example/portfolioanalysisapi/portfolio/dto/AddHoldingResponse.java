package org.example.portfolioanalysisapi.portfolio.dto;

import org.example.portfolioanalysisapi.asset.AssetType;

import java.math.BigDecimal;

public class AddHoldingResponse {

    private Long portfolioId;
    private String ticker;
    private BigDecimal quantity;
    private AssetType assetType;


    public AssetType getAssetType() {
        return assetType;
    }

    public void setAssetType(AssetType assetType) {
        this.assetType = assetType;
    }

    public Long getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(Long portfolioId) {
        this.portfolioId = portfolioId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
}
