package com.iyb.ak;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@ComponentScan(basePackages={"com.iyb.ak"})
@EnableFeignClients
@EnableEurekaClient
@EnableHystrix
//@SpringCloudApplication
//@EnableCircuitBreaker
//@RefreshScope
@Slf4j
public class PlutoApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(PlutoApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {
		log.info(">>>>>>>>>>>>>>> sc-pluto 启动完成<<<<<<<<<<<<<");
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
