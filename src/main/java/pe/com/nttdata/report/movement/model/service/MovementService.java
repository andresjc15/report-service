package pe.com.nttdata.report.movement.model.service;

import pe.com.nttdata.report.movement.model.event.Event;
import pe.com.nttdata.report.movement.model.MovementReport;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.concurrent.ExecutionException;

public interface MovementService {

    public Mono<MovementReport> getMovementsReportByDates(Date startDate, Date endDate) throws ExecutionException, InterruptedException;

    public Mono<MovementReport> getMovementsReportByDate(Date date) throws ExecutionException, InterruptedException;

    public void listener(Event<?> event);

}
