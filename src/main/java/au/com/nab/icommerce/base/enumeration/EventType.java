package au.com.nab.icommerce.base.enumeration;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public enum EventType {

    AUDIT, LOG
}
