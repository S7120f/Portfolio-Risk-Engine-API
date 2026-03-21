package org.example.portfolioanalysisapi.portfolio;


import org.example.portfolioanalysisapi.asset.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PortfolioAssetRepository extends JpaRepository<PortfolioAsset, Long> {

    Optional<PortfolioAsset> findByPortfolioAndAsset(Portfolio id, Asset ticker);



}
