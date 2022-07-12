package pe.com.nttdata.report.customer.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.nttdata.report.customer.model.EnterpriseCustomer;
import pe.com.nttdata.report.customer.model.PersonalCustomer;
import pe.com.nttdata.report.customer.model.service.EnterpriseCustomerService;
import pe.com.nttdata.report.customer.model.service.PersonalCustomerService;
import reactor.core.publisher.Flux;

import java.util.Date;

@RestController
@RequestMapping("${path.reports}")
@AllArgsConstructor
public class CustomerController {

    private final EnterpriseCustomerService enterpriseCustomerService;

    private final PersonalCustomerService personalCustomerService;

    @GetMapping("/enterprise-accounts/{startDate}/{endDate}")
    public Flux<EnterpriseCustomer> getEnterpriseReport(@PathVariable Date startDate, @PathVariable Date endDate) {
        return enterpriseCustomerService.getAllBetweenDates();
    }

    @GetMapping("/personal-accounts/{startDate}/{endDate}")
    public Flux<PersonalCustomer> getPersonalReport(@PathVariable Date startDate, @PathVariable Date endDate) {
        return personalCustomerService.getAllBetweenDates();
    }

}
