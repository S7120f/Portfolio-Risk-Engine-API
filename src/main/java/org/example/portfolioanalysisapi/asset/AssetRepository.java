package org.example.portfolioanalysisapi.asset;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssetRepository extends JpaRepository <Asset, Long> {
    Optional<Asset> findByTickerAndAssetType(String ticker, AssetType assetType);
    Optional<Asset> findByTicker(String ticker);
}
