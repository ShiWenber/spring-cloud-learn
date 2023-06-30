package edu.ynu.ecs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ElmServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElmServerApplication.class, args);
		log.info("logger class: {}", log.getClass());
	}


//	@GetMapping("/hello")
//	public String hello(@RequestParam(value = "name", defaultValue = "world") String name) {
//		return String.format("hello %s", name);
//	}

}
