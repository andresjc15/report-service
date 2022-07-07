package pe.com.ajcp.report.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pe.com.ajcp.report.account.model.Account;
import pe.com.ajcp.report.account.model.service.AccountService;
import reactor.core.publisher.Flux;

@Service
public class AccountServiceImpl implements AccountService {

    @Qualifier("accountWebClient")
    @Autowired
    private WebClient.Builder webClient;

    @Override
    public Flux<Account> getAllBetweenDates() {
        return webClient.build()
                .get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Account.class);
    }
}
