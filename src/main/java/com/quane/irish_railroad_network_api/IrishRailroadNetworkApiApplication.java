package com.quane.irish_railroad_network_api;

import com.quane.irish_railroad_network_api.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SwaggerConfig.class)
public class IrishRailroadNetworkApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IrishRailroadNetworkApiApplication.class, args);
	}

}
