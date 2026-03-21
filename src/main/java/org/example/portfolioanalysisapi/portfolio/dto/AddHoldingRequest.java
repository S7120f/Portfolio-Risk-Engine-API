package org.example.portfolioanalysisapi.portfolio.dto;

import org.example.portfolioanalysisapi.asset.AssetType;

import java.math.BigDecimal;

public class AddHoldingRequest {

    private String ticker;
    private BigDecimal quantity;
    private AssetType assetType;

    public AssetType getAssetType() {
        return assetType;
    }

    public void setAssetType(AssetType assetType) {
        this.assetType = assetType;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}
