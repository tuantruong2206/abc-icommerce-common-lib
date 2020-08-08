package au.com.nab.icommerce.base.dto;

import au.com.nab.icommerce.base.enumeration.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
public class EventEnvelope<T> implements Serializable {

    private EventType eventType;
    private T data;
    private LocalDateTime localDateTime;
}