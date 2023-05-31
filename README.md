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

### 消息总线 bus

当使用spring cloud config 托管配置文件时，更新git配置库后，需要重新启动，微服务中的配置才能更新

在更新config后，如果使用for来更新整个微服务，可能导致有些服务的config更新早于某些服务的config，导致服务之间的config不一致，这里使用观察者模式来更新整个微服务更好

### 微服务的拆分

AFK 服务拆分

有三个拓展维度

X轴：

Y轴：

Z轴：

微服务中的相互调用一般最多三层

尽量不要出现环形调用，这会导致服务之间的耦合度过高，一个修改导致多个服务的修改

#### DDD 领域驱动设计

贫血模型在设计之初就只有数据，而所有的逻辑要全部写在一组加service的对象中，而service则构建在领域模型上，而service则构建在领域模型上，需要使用这些模型来传递数据

这导致service层及其臃肿，导致拆分微服务过难。

如果将持久化也放入领域模型中，称为涨血模型，这样的模型会导致dao层的消失

有一些使用充血模型编写的Spring Boot服务的高质量源码库，以下是其中的一些：

Spring PetClinic：这是一个宠物诊所管理系统的示例应用程序，使用Spring Boot和Thymeleaf构建。它使用充血模型来实现业务逻辑，代码结构清晰，易于理解和扩展。源码库地址：https://github.com/spring-projects/spring-petclinic

JHipster：这是一个使用Spring Boot和Angular构建的现代Web应用程序生成器。它使用充血模型来实现业务逻辑，代码结构清晰，易于理解和扩展。源码库地址：https://github.com/jhipster/jhipster

CQRS-ES sample：这是一个使用Spring Boot和Axon Framework构建的示例应用程序，演示了如何使用CQRS和事件溯源模式来实现业务逻辑。它使用充血模型来实现业务逻辑，代码结构清晰，易于理解和扩展。源码库地址：https://github.com/AxonIQ/cqrs-axon-examples/tree/master/spring-boot-command-gateway

Spring Cloud Samples：这是一个使用Spring Cloud构建的示例应用程序集合，包括配置中心、服务注册和发现、负载均衡、断路器等功能。它使用充血模型来实现业务逻辑，代码结构清晰，易于理解和扩展。源码库地址：https://github.com/spring-cloud-samples

总之，以上是一些使用充血模型编写的Spring Boot服务的高质量源码库，它们都使用了Spring Boot和其他相关技术，代码结构清晰，易于理解和扩展。

##### 充血模型示例

不需要manager帮助其他对象做保存或者其他的行为。

而现在，我们应该将save直接作为领域模型的行为

微服务引起了对贫血模型的更高关注

[知乎领域对象分析模型](https://www.zhihu.com/question/469882444)

[bilibili ddd](https://www.bilibili.com/video/BV1ag4115758/)

[ddd领域驱动设计-概述 无涯]()

仓库与工厂（将仓库和工厂做分离，把仓库抽象出来作为一个单层）