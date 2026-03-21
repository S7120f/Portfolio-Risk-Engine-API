package org.example.portfolioanalysisapi.portfolio.dto;

import org.example.portfolioanalysisapi.portfolio.PortfolioAsset;

import java.util.List;

public class PortfolioDetailsResponse {

    private Long id;
    private String name;
    private List<PortfolioAssetResponse> holdings;

    public PortfolioDetailsResponse(Long id, String name, List<PortfolioAssetResponse> holdings) {
        this.id = id;
        this.name = name;
        this.holdings = holdings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PortfolioAssetResponse> getHoldings() {
        return holdings;
    }

    public void setHoldings(List<PortfolioAssetResponse> holdings) {
        this.holdings = holdings;
    }
}
