package org.example.portfolioanalysisapi;

import org.assertj.core.api.Assertions;
import org.example.portfolioanalysisapi.asset.Asset;
import org.example.portfolioanalysisapi.asset.AssetRepository;
import org.example.portfolioanalysisapi.portfolio.*;
import org.example.portfolioanalysisapi.portfolio.dto.CreatePortfolioRequest;
import org.example.portfolioanalysisapi.portfolio.dto.AddHoldingRequest;
import org.example.portfolioanalysisapi.portfolio.dto.AddHoldingResponse;
import org.example.portfolioanalysisapi.portfolio.dto.PortfolioResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Create fake repositories
class PortfolioServiceTest {


    @Mock
    private PortfolioRepository portfolioRepository;

    @Mock
    private AssetRepository assetRepository;

    @Mock
    private PortfolioAssetRepository portfolioAssetRepository;

    @InjectMocks
    private PortfolioService portfolioService;




    @Test
    void shouldCreateNewPortfolio() {
        CreatePortfolioRequest request = new CreatePortfolioRequest();
        request.setName("Jonas");

        Portfolio savedPortfolio = new Portfolio();
        savedPortfolio.setId(1L);
        savedPortfolio.setName("Jonas");

        when(portfolioRepository.save(any(Portfolio.class)))
                .thenReturn(savedPortfolio);

        // run method
        PortfolioResponse result = portfolioService.createPortfolio(request);

        // check result
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getName()).isEqualTo("Jonas");

        // check that "save" actually be called
        verify(portfolioRepository).save(any(Portfolio.class));

    }

    @Test
     void shouldUpdateQuantityWhenHoldingAlreadyExists() {

        Long portfolioId = 1L;

        AddHoldingRequest request = new AddHoldingRequest();
        request.setTicker("AAPL");
        request.setQuantity(new BigDecimal("5"));

        Portfolio portfolio = new Portfolio();
        portfolio.setId(portfolioId);

        Asset asset = new Asset();
        asset.setTicker("AAPL");

        PortfolioAsset existingHolding = new PortfolioAsset();
        existingHolding.setPortfolio(portfolio);
        existingHolding.setAsset(asset);
        existingHolding.setQuantity(new BigDecimal("10"));

        when(portfolioRepository.findById(portfolioId))
                .thenReturn(Optional.of(portfolio));

        when(assetRepository.findByTicker("AAPL"))
                .thenReturn(Optional.of(asset));

        when(portfolioAssetRepository.findByPortfolioAndAsset(portfolio, asset))
                .thenReturn(Optional.of(existingHolding));

        AddHoldingResponse result =
                portfolioService.addHolding(portfolioId, request);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTicker()).isEqualTo("AAPL");
        Assertions.assertThat(result.getQuantity()).isEqualTo("15");
        Assertions.assertThat(existingHolding.getQuantity()).isEqualByComparingTo("15");

        verify(portfolioAssetRepository).findByPortfolioAndAsset(portfolio, asset);
        verify(portfolioAssetRepository).save(existingHolding);

    }


    @Test
    void shouldAddHoldingWhenEmptyPortfolio() {

    }
}
