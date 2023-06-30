package edu.ynu.ecs.loadbalance;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

public class CustomLoadBalanceConfiguration {
    @Bean
    ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment environment, LoadBalancerClientFactory loadBalancerClientFactory) {
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        ReactorLoadBalancer reactorLoadBalancer = new RandomLoadBalancer(loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
        return reactorLoadBalancer;
    }

//    @Bean
//    public ReactorLoadBalancer<ServiceInstance> leastConnectionsLoadBalancer(Environment environment, LoadBalancerClientFactory loadBalancerClientFactory) {
//        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
//        return new LeastConnectionsLoadBalancer(loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
//    }

}
//
//public class LeastConnectionsLoadBalancer implements ReactorLoadBalancer<ServiceInstance> {
//    private final ServiceInstanceListSupplier serviceInstanceListSupplier;
//    private final String name;
//
//    public LeastConnectionsLoadBalancer(ServiceInstanceListSupplier serviceInstanceListSupplier, String name) {
//        this.serviceInstanceListSupplier = serviceInstanceListSupplier;
//        this.name = name;
//    }
//
//    @Override
//    public Mono<Response<ServiceInstance>> choose(Request request) {
//        List<ServiceInstance> instances = serviceInstanceListSupplier.get();
//        if (instances.isEmpty()) {
//            return Mono.empty();
//        }
//        ServiceInstance instance = instances.get(0);
//        for (int i = 1; i < instances.size(); i++) {
//            ServiceInstance currentInstance = instances.get(i);
//            if (currentInstance instanceof LoadBalancerStats) {
//                LoadBalancerStats stats = (LoadBalancerStats) currentInstance;
//                LoadBalancerStats currentStats = (LoadBalancerStats) instance;
//                if (stats.getActiveRequestsCount() < currentStats.getActiveRequestsCount()) {
//                    instance = currentInstance;
//                }
//            }
//        }
//        return Mono.just(new DefaultResponse(instance));
//    }
//
//    @Override
//    public ServiceInstanceListSupplier getServiceInstanceListSupplier() {
//        return serviceInstanceListSupplier;
//    }
//
//    @Override
//    public String getName() {
//        return name;
//    }
//}
