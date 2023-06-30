package edu.ynu.ecs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootApplication
//@EnableDiscoveryClient
@EnableFeignClients
public class User_server_11003 {

	public static void main(String[] args) {
		SpringApplication.run(User_server_11003.class, args);
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
