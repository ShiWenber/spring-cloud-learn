# 创建项目

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
