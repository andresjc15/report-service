package pe.com.ajcp.report.movement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pe.com.ajcp.report.movement.model.Movement;
import pe.com.ajcp.report.movement.model.service.MovementService;
import reactor.core.publisher.Flux;

@Service
public class MovementServiceImpl implements MovementService {

    @Qualifier("movementWebClient")
    @Autowired
    private WebClient.Builder webClient;

    @Override
    public Flux<Movement> getAllBetweenDates() {
        return webClient.build()
                .get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Movement.class);
    }
}
