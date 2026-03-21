
package org.example.portfolioanalysisapi.marketdata;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/marketdata")
public class MarketDataController {

    final private MarketDataService marketDataService;

    public MarketDataController(MarketDataService marketDataService) {
        this.marketDataService = marketDataService;
    }



    @GetMapping("/{ticker}")
    public ResponseEntity<List<PricePoint>> getPriceHistory(@PathVariable String ticker) {
        List<PricePoint> list = marketDataService.getPriceHistory(ticker);
        return ResponseEntity.ok(list);
    }

}
