package pe.com.nttdata.report.movement.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.nttdata.report.movement.model.MovementReport;
import pe.com.nttdata.report.movement.model.service.MovementService;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("${path.reports}")
@AllArgsConstructor
public class MovementController {

    private final MovementService movementService;

    @GetMapping("/movements/{startDate}/{endDate}")
    public Mono<MovementReport> getMovementReportByDates(@PathVariable Date startDate, @PathVariable Date endDate)
            throws ExecutionException, InterruptedException {
        return movementService.getMovementsReportByDates(startDate, endDate);
    }

    @GetMapping("/movements/{date}")
    public Mono<MovementReport> getMovementReportByDate(@PathVariable Date date)
            throws ExecutionException, InterruptedException {
        return movementService.getMovementsReportByDate(date);
    }

}
