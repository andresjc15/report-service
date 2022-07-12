package pe.com.nttdata.report.movement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pe.com.nttdata.email.EmailServiceImpl;
import pe.com.nttdata.movement.event.MovementCreatedEvent;
import pe.com.nttdata.report.customer.model.Customer;
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
            movementCreatedEvent.getData().setCustomer(new Customer());
            movementCreatedEvent.getData().getCustomer().setEmail("telescopio322@gmail.com");
            String email =  movementCreatedEvent.getData().getCustomer().getEmail();

            sendMessage(email, "New movement", movementCreatedEvent.getData().getDescription());
        }
    }

    @Autowired
    private JavaMailSender emailSender;

    public void sendMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("arcp1512@gmail.com");
        //message.setTo("telescopio322@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        //emailSender.send(message);
        log.info("[MAIL SENT]: " + message.getTo().toString());
    }


}
