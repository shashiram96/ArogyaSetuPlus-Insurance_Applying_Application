package ArogyaSetuPlus.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApplicationConfig {
    @Bean("webClient")
    WebClient createWebClient() {
	return WebClient.create();
    }

}
