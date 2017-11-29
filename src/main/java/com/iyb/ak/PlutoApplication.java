package com.iyb.ak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.web.client.RestTemplate;


@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages={"com.iyb.ak"})
@EnableFeignClients
@EnableEurekaClient
@EnableHystrix
//@SpringCloudApplication
//@EnableCircuitBreaker
public class PlutoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlutoApplication.class, args);
	}


	@Bean
	RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
		return restTemplate;
	}


/*	@Bean
	@ConditionalOnMissingBean
	public IRule ribbonRule() {
		System.out.println("随机的。。。。");
		return new RandomRule();
	}*/
}
