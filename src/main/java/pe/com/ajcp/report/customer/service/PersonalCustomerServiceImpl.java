package pe.com.ajcp.report.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pe.com.ajcp.report.customer.model.PersonalCustomer;
import pe.com.ajcp.report.customer.model.service.PersonalCustomerService;
import reactor.core.publisher.Flux;

@Service
public class PersonalCustomerServiceImpl implements PersonalCustomerService {

    @Qualifier("personalCustomerWebClient")
    @Autowired
    private WebClient.Builder webClient;

    @Override
    public Flux<PersonalCustomer> getAllBetweenDates() {
        return webClient.build()
                .get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(PersonalCustomer.class);
    }
}
