//package edu.ynu.config;
//
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//@Configuration
//public class GatewayConfig {
//    @Bean
//    @CircuitBreaker(name = "backendB", fallbackMethod = "fallback")
//    public GatewayFilter myFilter() {
//        return (exchange, chain) -> {
//            // 这里是您的过滤器逻辑
//            return chain.filter(exchange);
//        };
//    }
//
//    public Mono<Void> fallback(ServerWebExchange exchange, Throwable throwable) {
//        // 这里是熔断降级的逻辑
//        exchange.getResponse().setStatusCode(HttpStatus.SERVICE_UNAVAILABLE);
//        return exchange.getResponse().setComplete();
//    }
//
//    @Bean
//    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(p -> p
//                        .path("/cart/**")
//                        .filters(f -> f.filter(myFilter()))
//                        .uri("http://localhost:8080"))
//                .build();
//    }
//}