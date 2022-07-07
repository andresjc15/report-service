package pe.com.ajcp.report.movement.model.service;

import pe.com.ajcp.report.customer.model.PersonalCustomer;
import pe.com.ajcp.report.movement.model.Movement;
import reactor.core.publisher.Flux;

public interface MovementService {

    public Flux<Movement> getAllBetweenDates();

}
