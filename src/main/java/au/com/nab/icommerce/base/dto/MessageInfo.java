package au.com.nab.icommerce.base.dto;

import au.com.nab.icommerce.base.enumeration.MessageCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageInfo {

    private String code;
    private String message;

    public MessageInfo(MessageCodeEnum messageCodeEnum) {
        this.code = messageCodeEnum.getCode();
        this.message = messageCodeEnum.getMsg();
    }
}
