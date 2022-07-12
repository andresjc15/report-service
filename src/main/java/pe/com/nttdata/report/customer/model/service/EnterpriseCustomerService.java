package pe.com.nttdata.report.customer.model.service;

import pe.com.nttdata.report.customer.model.EnterpriseCustomer;
import reactor.core.publisher.Flux;

public interface EnterpriseCustomerService {

    public Flux<EnterpriseCustomer> getAllBetweenDates();

}
