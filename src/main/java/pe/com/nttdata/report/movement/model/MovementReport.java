package pe.com.nttdata.report.movement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovementReport {

    private Flux<Movement> movements;
    private Mono<Long> quantity;

}
