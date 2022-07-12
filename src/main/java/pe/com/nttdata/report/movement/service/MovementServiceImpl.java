package pe.com.nttdata.report.movement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pe.com.nttdata.movement.event.MovementCreatedEvent;
import pe.com.nttdata.report.movement.model.event.Event;
import pe.com.nttdata.report.movement.model.Movement;
import pe.com.nttdata.report.movement.model.MovementReport;
import pe.com.nttdata.report.movement.model.service.MovementService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Date;

@Service
public class MovementServiceImpl implements MovementService {

    private static final Logger log = LoggerFactory.getLogger(MovementServiceImpl.class);

    @Qualifier("movementWebClient")
    @Autowired
    private WebClient.Builder webClient;

    @Override
    public Mono<MovementReport> getMovementsReportByDates(Date startDate, Date endDate) {
        Flux<Movement> movements = webClient.build()
                .get()
                .uri("/dates/{startDate}/{endDate}", Collections.singletonMap("startDate", startDate),
                        Collections.singletonMap("endDate", endDate))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Movement.class);
        Mono<Long> quantity = webClient.build()
                .get()
                .uri("/dates/{startDate}/{endDate}/quantity", Collections.singletonMap("startDate", startDate),
                        Collections.singletonMap("endDate", endDate))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Long.class);
        return Mono.just(new MovementReport(movements, quantity));
    }

    @Override
    public Mono<MovementReport> getMovementsReportByDate(Date date) {
        Flux<Movement> movements = webClient.build()
                .get()
                .uri("/date/{date}", Collections.singletonMap("date", date))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Movement.class);
        Mono<Long> quantity = webClient.build()
                .get()
                .uri("/date/{date}/quantity", Collections.singletonMap("date", date))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Long.class);
        return Mono.just(new MovementReport(movements, quantity));
    }

    @KafkaListener(
            topics = "${topic.movement.name}",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "group2")
    @Override
    public void listener(Event<?> event) {
        if (event.getClass().isAssignableFrom(MovementCreatedEvent.class)) {
            MovementCreatedEvent movementCreatedEvent = (MovementCreatedEvent) event;
            log.info("Received Movement created event .... with Id={}, data={}",
                    movementCreatedEvent.getId(),
                    movementCreatedEvent.getData().toString());
        }
    }


}
