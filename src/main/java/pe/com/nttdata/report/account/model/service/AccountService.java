package pe.com.nttdata.report.account.model.service;

import pe.com.nttdata.report.account.model.Account;
import reactor.core.publisher.Flux;

public interface AccountService {

    public Flux<Account> getAllBetweenDates();

}
