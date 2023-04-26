# 创建项目

- [ ] completableFuture

##   

packagin

使用restTemplate作为代替axios的http请求发送包(功能较简单，建议使用更好的)

```java

@GetMapping("/hello")
public String hello(){
        return restTemplate.getForObject(
        "",
        String.class
        );
```

提供url和返回类型

## 服务分割



## 产品选择

## 过程

### 负载均衡

openfeign

负载均衡自行配置，查看environment# spring-cloud-learn

circuitbreaker 只保留了熔断器

熔断和隔离的区别:
熔断：
1. 服务失效熔断
2. 慢调用熔断

熔断一定要有，如果没有熔断，做微服务如果没有熔断就可能会出现雪崩效应，所有的服务都会失效

而限流和隔离是为了保证服务的可用性

限流以时间为维度，线程隔离或者信号量隔离是从底层上限制并发量

熔断限流可以同时存在

### gateway 网关

功能：
1. 路由转发
2. 过滤器
3. 限流
4. 熔断，负载均衡

> gateway的熔断和负载均衡和单个服务内部的熔断和负载均衡是不一样的，gateway的熔断和负载均衡是针对所有服务的，而单个服务内部的熔断和负载均衡是针对单个服务的

completableFuture