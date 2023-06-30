package edu.ynu.ecs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RefreshScope // Refresh 一般只放在需要被刷新的bean上，而不是放在启动类上
public class User_server_11002 {

//	判断配置文件是否更新
	@Value("${message}")
	private static String message;

	public static void main(String[] args) {
		//		从配置文件中获得message变量并输出
		log.info("config-message: {}", message);
		SpringApplication.run(User_server_11002.class, args);
		log.info("logger class: {}", log.getClass());
	}

//    @Bean
//    public RestTemplate getRestTemplate() {
//        return new RestTemplate();
//    }


//	@GetMapping("/hello")
//	public String hello(@RequestParam(value = "name", defaultValue = "world") String name) {
//		return String.format("hello %s", name);
//	}

}
