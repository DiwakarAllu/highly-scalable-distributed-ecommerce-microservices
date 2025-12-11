# Distributed Microservices Architecture for an E-Commerce Platform

## Overview

This project is a comprehensive, end-to-end implementation of a cloud-native e-commerce platform built using a microservices architecture. It showcases best practices for building scalable, resilient, secure, and observable distributed systems using the Spring ecosystem and modern DevOps tools.

The primary goal of this project was to explore and implement core microservices design patterns, moving from basic REST services to a production-ready, containerized application deployable on Kubernetes.

## Key Features & Implemented Patterns

*   **RESTful APIs:** Building robust applications following best practices with [Spring Boot 3](spring.io).
*   **Inter-Service Communication:** Synchronous communication using [Spring Cloud Open Feign](spring.io).
*   **Service Discovery:** Dynamic service registration and discovery using [Spring Cloud Netflix Eureka](spring.io).
*   **API Gateway:** Centralized routing, load balancing, and security enforcement with [Spring Cloud Gateway](spring.io).
*   **Security:** Implementing robust authentication and authorization using [Keycloak](www.keycloak.org) (OAuth 2.0/OIDC).
*   **Resilience & Fault Tolerance:** Circuit Breaker pattern implementation using [Resilience4J](resilience4j.readme.io) within Spring Cloud CircuitBreaker.
*   **Event-Driven Architecture (EDA):** Asynchronous communication and event processing using [Apache Kafka](kafka.apache.org).
*   **Observability:**
    *   **Distributed Tracing:** Using [Open Telemetry (OTel)](opentelemetry.io) and [Grafana Tempo](grafana.com).
    *   **Log Aggregation:** Centralized logging solution with [Grafana Loki](grafana.com).
    *   **Metrics Monitoring:** Collecting and visualizing metrics using [Prometheus](prometheus.io) and [Grafana](grafana.com) dashboards.
*   **Containerization & Orchestration:** Packaging services with [Docker](www.docker.com) and orchestration/deployment on [Kubernetes (K8s)](kubernetes.io) using Docker Compose for local testing and K8s manifests for deployment.


## Technologies Used

*   **Language:** Java 21+
*   **Framework:** Spring Boot 3 & Spring Cloud
*   **Messaging:** Apache Kafka, Kafka Connect
*   **Databases:**  MySQL and MongoDb
*   **Security:** Keycloak, JWT, OIDC
*   **DevOps:** Docker, Docker Compose, Kubernetes
*   **Monitoring Stack:** Prometheus, Grafana, Loki, Tempo, OpenTelemetry
*   **Build Tool:** Maven 
