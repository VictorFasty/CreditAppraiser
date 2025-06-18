package io.github.victorfasty.msCreditAppraiser;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableRabbit
@EnableFeignClients(basePackages = "io.github.victorfasty.msCreditAppraiser.infra")
public class MsCreditAppraiserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCreditAppraiserApplication.class, args);
	}

}
