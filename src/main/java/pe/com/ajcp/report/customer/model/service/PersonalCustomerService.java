package pe.com.ajcp.report.customer.model.service;

import pe.com.ajcp.report.customer.model.PersonalCustomer;
import reactor.core.publisher.Flux;

public interface PersonalCustomerService {

    public Flux<PersonalCustomer> getAllBetweenDates();

}
