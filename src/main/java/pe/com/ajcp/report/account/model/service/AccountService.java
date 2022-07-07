package pe.com.ajcp.report.account.model.service;

import pe.com.ajcp.report.account.model.Account;
import reactor.core.publisher.Flux;

public interface AccountService {

    public Flux<Account> getAllBetweenDates();

}
