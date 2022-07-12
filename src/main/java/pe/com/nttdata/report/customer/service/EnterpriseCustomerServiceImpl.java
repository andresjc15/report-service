package pe.com.nttdata.report.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pe.com.nttdata.report.customer.model.EnterpriseCustomer;
import pe.com.nttdata.report.customer.model.service.EnterpriseCustomerService;
import reactor.core.publisher.Flux;

@Service
public class EnterpriseCustomerServiceImpl implements EnterpriseCustomerService {

    @Qualifier("enterpriseCustomerWebClient")
    @Autowired
    private WebClient.Builder webClient;

    @Override
    public Flux<EnterpriseCustomer> getAllBetweenDates() {
        return webClient.build()
                .get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(EnterpriseCustomer.class);
    }
}
