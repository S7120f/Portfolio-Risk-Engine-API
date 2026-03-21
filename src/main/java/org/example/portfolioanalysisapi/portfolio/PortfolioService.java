package org.example.portfolioanalysisapi.portfolio;

import lombok.extern.slf4j.Slf4j;
import org.example.portfolioanalysisapi.asset.Asset;
import org.example.portfolioanalysisapi.asset.AssetRepository;
import org.example.portfolioanalysisapi.portfolio.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class PortfolioService {



    private final PortfolioRepository portfolioRepository;
    private final AssetRepository assetRepository;
    private final PortfolioAssetRepository portfolioAssetRepository;

    public PortfolioService(PortfolioRepository portfolioRepository, AssetRepository assetRepository, PortfolioAssetRepository portfolioAssetRepository) {
        this.portfolioRepository = portfolioRepository;
        this.assetRepository = assetRepository;
        this.portfolioAssetRepository = portfolioAssetRepository;
    }


    public PortfolioResponse createPortfolio(CreatePortfolioRequest request) {
        Portfolio portfolio = new Portfolio();
        portfolio.setName(request.getName());
        log.info("Portfolio created with: name={}", portfolio.getName());
        Portfolio savedPortfolio = portfolioRepository.save(portfolio);

        PortfolioResponse response = new PortfolioResponse();
        response.setId(savedPortfolio.getId());
        response.setName(savedPortfolio.getName());

        return response;
    }

    public List<PortfolioResponse> getAllPortfolios() {
        return portfolioRepository.findAll()
                .stream()
                .map(p -> new PortfolioResponse(
                        p.getId(),
                        p.getName()
                ))
                .toList();
    }

    public PortfolioDetailsResponse getPortfolioById(Long id) {
        Portfolio portfolio = portfolioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("portfolio not found"));

        List<PortfolioAssetResponse> holdings = portfolio.getHoldings()
                .stream()
                .map(holding -> new PortfolioAssetResponse(
                        holding.getAsset().getAssetType(),
                        holding.getAsset().getTicker(),
                        holding.getQuantity()
                ))
                .toList();

        return new PortfolioDetailsResponse(
                portfolio.getId(),
                portfolio.getName(),
                holdings
        );
    }

    public List<PortfolioDetailsResponse> getPortfolioOverview() {
        List<Portfolio> portfolios = portfolioRepository.findAll();

        List<PortfolioDetailsResponse> overview = portfolios.stream()
                .map(p -> new PortfolioDetailsResponse(
                        p.getId(),
                        p.getName(),
                        p.getHoldings()
                                .stream()
                                .map(holding -> new PortfolioAssetResponse(
                                        holding.getAsset().getAssetType(),
                                        holding.getAsset().getTicker(),
                                        holding.getQuantity()
                                ))
                                .toList()
                ))
                .toList();


        return overview;
    }

    public AddHoldingResponse addHolding(Long portfolioId, AddHoldingRequest request) {
        Portfolio portfolio = portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new IllegalArgumentException("portfolio not found"));

        Optional<Asset> assetOptional = assetRepository.findByTicker(request.getTicker());
        Asset asset;
        if (assetOptional.isPresent()) {
            asset = assetOptional.get();
        } else {
            asset = new Asset();
            asset.setTicker(request.getTicker());
            asset.setAssetType(request.getAssetType());
            asset = assetRepository.save(asset);
        }

        Optional<PortfolioAsset> existingHolding = portfolioAssetRepository.findByPortfolioAndAsset(portfolio, asset);

        PortfolioAsset portfolioAsset;

        if (existingHolding.isPresent()) {
            portfolioAsset = existingHolding.get();
            portfolioAsset.setQuantity(portfolioAsset.getQuantity().add(request.getQuantity())
            );
        } else {
            portfolioAsset = new PortfolioAsset();
            portfolioAsset.setPortfolio(portfolio);
            portfolioAsset.setAsset(asset);
            portfolioAsset.setQuantity(request.getQuantity());
        }

        log.info("quantity={} and ticker={} added to portfolio={}",
                request.getQuantity(), asset.getTicker(), portfolio.getId());

        portfolioAssetRepository.save(portfolioAsset);

        AddHoldingResponse response = new AddHoldingResponse();
        response.setPortfolioId(portfolio.getId());
        response.setTicker(asset.getTicker());
        response.setAssetType(asset.getAssetType());
        response.setQuantity(portfolioAsset.getQuantity());


        return response;

    }
}
