package pe.com.nttdata.movement.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pe.com.nttdata.report.movement.model.Movement;
import pe.com.nttdata.report.movement.model.event.Event;

@Data
@EqualsAndHashCode(callSuper = true)
public class MovementCreatedEvent extends Event<Movement> {
}
