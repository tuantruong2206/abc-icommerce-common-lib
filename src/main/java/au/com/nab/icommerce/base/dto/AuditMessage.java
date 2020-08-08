package au.com.nab.icommerce.base.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AuditMessage {

    private Long time;
    private String action;
    private String usename;
    private String remark;
}
