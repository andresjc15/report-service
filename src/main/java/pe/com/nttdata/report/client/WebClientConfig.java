package pe.com.nttdata.report.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${config.endpoint.accounts}")
    private String accountUri;

    @Value("${config.endpoint.customers.enterprise}")
    private String enterpriseCustomerUri;

    @Value("${config.endpoint.customers.personal}")
    private String personalCustomerUri;

    @Value("${config.endpoint.movements}")
    private String movementUri;

    @Bean(name = "accountWebClient")
    @LoadBalanced
    public WebClient.Builder accountWebClient() {
        return WebClient.builder().baseUrl(accountUri);
    }

    @Bean(name = "enterpriseCustomerWebClient")
    @LoadBalanced
    public WebClient.Builder enterpriseCustomerWebClient() {
        return WebClient.builder().baseUrl(enterpriseCustomerUri);
    }

    @Bean(name = "personalCustomerWebClient")
    @LoadBalanced
    public WebClient.Builder personalCustomerWebClient() {
        return WebClient.builder().baseUrl(personalCustomerUri);
    }

    @Bean(name = "movementWebClient")
    @LoadBalanced
    public WebClient.Builder movementWebClient() {
        return WebClient.builder().baseUrl(movementUri);
    }

}
