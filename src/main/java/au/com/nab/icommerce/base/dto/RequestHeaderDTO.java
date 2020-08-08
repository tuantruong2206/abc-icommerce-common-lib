package au.com.nab.icommerce.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestHeaderDTO {
    private String userid;
    private String httpMethod;
    private String httpUrl;
    private String httpBody;
    private Instant createdAt;
    private Instant updatedAt;
}
