package pe.com.nttdata.report.account.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.nttdata.report.account.model.Account;
import pe.com.nttdata.report.account.model.service.AccountService;
import reactor.core.publisher.Flux;

import java.util.Date;

@RestController
@RequestMapping("${path.reports}")
@AllArgsConstructor
public class AccountReportController {

    private final AccountService accountService;

    @GetMapping("/accounts/{startDate}/{endDate}")
    public Flux<Account> getReportByDates(@PathVariable Date startDate, @PathVariable Date endDate) {
        return accountService.getAllBetweenDates();
    }

    @GetMapping("/accounts/{date}")
    public Flux<Account> getReportByDate(@PathVariable Date date) {
        return accountService.getAllBetweenDates();
    }

}
