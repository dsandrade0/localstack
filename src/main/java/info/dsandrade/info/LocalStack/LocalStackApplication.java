package info.dsandrade.info.LocalStack;

import info.dsandrade.info.LocalStack.config.AwsConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@ConditionalOnClass(AwsConfiguration.class)
@EnableFeignClients
public class LocalStackApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocalStackApplication.class, args);
	}

}
