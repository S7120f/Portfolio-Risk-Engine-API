package org.example.portfolioanalysisapi.risk.controller;

import org.example.portfolioanalysisapi.risk.dto.CorrelationResponse;
import org.example.portfolioanalysisapi.risk.dto.MaxDrawdownResponse;
import org.example.portfolioanalysisapi.risk.dto.VolatilityResponse;
import org.example.portfolioanalysisapi.risk.service.RiskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/risk")
public class RiskController {

    private final RiskService riskService;

    public RiskController(RiskService riskService) {
        this.riskService = riskService;
    }

    @GetMapping("/{ticker}/volatility")
    public ResponseEntity<VolatilityResponse> getVolatility(@PathVariable String ticker) {
        BigDecimal volatility = riskService.getVolatilityForTicker(ticker);

        VolatilityResponse response = new VolatilityResponse();
        response.setTicker(ticker);
        response.setVolatility(volatility);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{ticker}/max-drawdown")
    public ResponseEntity<MaxDrawdownResponse> getMaxDrawdown(@PathVariable String ticker) {
        BigDecimal maxDrawdown = riskService.getMaxDaxDrawdownForTicker(ticker);

        MaxDrawdownResponse response = new MaxDrawdownResponse();
        response.setMaxDrawdown(maxDrawdown);
        response.setTicker(ticker);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/correlation")
    public ResponseEntity<CorrelationResponse> getCorrelation(@RequestParam String tickerA, @RequestParam String tickerB) {
        BigDecimal correlation = riskService.getCorrelationForTicker(tickerA, tickerB);

        CorrelationResponse response = new CorrelationResponse();
        response.setTickerA(tickerA);
        response.setTickerB(tickerB);
        response.setCorrelation(correlation);
        return ResponseEntity.ok(response);

    }
}
