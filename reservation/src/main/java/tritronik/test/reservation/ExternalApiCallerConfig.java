package tritronik.test.reservation;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ExternalApiCallerConfig {

    @Bean
    public RestTemplate restTemplatePayment() {
        return new RestTemplateBuilder().rootUri("http://localhost:8884")
                .build();
    }

    @Bean
    public RestTemplate restTemplateRoom() {
        return new RestTemplateBuilder().rootUri("http://localhost:8881")
                .build();
    }
}
