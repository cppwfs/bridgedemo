package io.spring.bridgedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableTask
public class BridgedemoApplication {

	@Autowired
	StreamBridge streamBridge;

	public static void main(String[] args) {
		SpringApplication.run(BridgedemoApplication.class, args);
	}


	@Bean
	ApplicationRunner applicationRunner() {
		return new ApplicationRunner() {
			@Override
			public void run(ApplicationArguments args) throws Exception {
				streamBridge.send("howdy", "data");
			}
		};
	}
}
