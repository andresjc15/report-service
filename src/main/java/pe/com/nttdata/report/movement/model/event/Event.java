package pe.com.nttdata.report.movement.model.event;

import lombok.Data;

import java.util.Date;

@Data
public abstract class Event<T> {

    private String id;
    private Date date;
    private EventType type;
    private T data;

}
