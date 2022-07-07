package pe.com.ajcp.report.movement.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.ajcp.report.movement.model.Movement;
import pe.com.ajcp.report.movement.model.service.MovementService;
import reactor.core.publisher.Flux;

import java.util.Date;

@RestController
@RequestMapping("${path.reports}")
@AllArgsConstructor
public class MovementController {

    private final MovementService movementService;

    @GetMapping("/movements/{startDate}/{endDate}")
    public Flux<Movement> getPersonalReport(@PathVariable Date startDate, @PathVariable Date endDate) {
        return movementService.getAllBetweenDates();
    }
}
