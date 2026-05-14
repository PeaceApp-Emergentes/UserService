package com.upc.pre.peaceapp.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = {
		"com.upc.pre.peaceapp.user",
		"com.upc.pre.peaceapp.shared.documentation"
})
@EnableDiscoveryClient
@EnableJpaAuditing
@EnableFeignClients
public class PeaceappUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeaceappUserServiceApplication.class, args);
	}

}
