package pe.com.nttdata.report.movement.model.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pe.com.nttdata.report.movement.model.Movement;

@Data
@EqualsAndHashCode(callSuper = true)
public class MovementCreatedEvent extends Event<Movement> {
}
