package org.example.portfolioanalysisapi.portfolio.dto;

import org.example.portfolioanalysisapi.asset.AssetType;
import org.example.portfolioanalysisapi.portfolio.PortfolioAsset;

import java.math.BigDecimal;
import java.util.List;

public class PortfolioAssetResponse {

    private AssetType assetType;
    private String ticker;
    private BigDecimal quantity;


    public PortfolioAssetResponse( AssetType assetType, String ticker, BigDecimal quantity) {
        this.assetType = assetType;
        this.ticker = ticker;
        this.quantity = quantity;
    }

    public AssetType getAssetType() {
        return assetType;
    }

    public void setAssetType(AssetType assetType) {
        this.assetType = assetType;
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
