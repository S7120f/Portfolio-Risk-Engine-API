# Portfolio Risk Engine API

A simple Java Spring Boot backend for portfolio analysis and risk calculations.

## Overview

This project provides the API layer for a portfolio risk engine. It is designed to support market data lookups, portfolio analysis, and risk-related calculations for financial assets.

## Features

- Spring Boot REST API
- Java-based backend
- Market data service with sample ticker history
- Portfolio analysis support
- PostgreSQL database support
- Docker setup for local development

## Tech Stack

- Java 25
- Spring Boot 3.5.x
- PostgreSQL
- Maven
- Docker / Docker Compose

## Prerequisites

Before you start, make sure you have:

- Java 25+
- Maven
- Docker and Docker Compose

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/S7120f/Portfolio-Risk-Engine-API.git
cd Portfolio-Risk-Engine-API
```

### 2. Start the database

```bash
docker compose up -d
```

### 3. Run the application

```bash
./mvnw spring-boot:run
```

The API will start on the default Spring Boot port, usually:

```text
http://localhost:8080
```

## Project Structure

```text
src/
  main/
    java/
      org/example/portfolioanalysisapi/
        asset/
        marketdata/
        portfolio/
        risk/
  test/
```

## Example

The project includes a sample market data service for tickers such as:

- AAPL
- DDL
- BBL

These are used to return basic price histories for testing and local development.

## Useful Commands

```bash
# Build the project
./mvnw clean install

# Run tests
./mvnw test
```

## Notes

This is a starter/backend project for a portfolio risk application. You can extend the services and controllers to add more analytics, scenario modeling, and API endpoints.
