package online.yuluo.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaProducerApplication2 {

	public static void main(String[] args) {
		SpringApplication.run(EurekaProducerApplication2.class, args);
	}

}
