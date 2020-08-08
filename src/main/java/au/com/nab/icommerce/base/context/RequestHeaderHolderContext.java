package au.com.nab.icommerce.base.context;

import au.com.nab.icommerce.base.dto.RequestHeaderDTO;
import au.com.nab.icommerce.base.enumeration.MessageCodeEnum;
import au.com.nab.icommerce.base.exception.NABException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RequestHeaderHolderContext extends HolderContext<RequestHeaderDTO> {
    /** The logger */
    private static final Logger log = LoggerFactory.getLogger(RequestHeaderHolderContext.class);

    public RequestHeaderDTO getRequestObj() throws Exception {
        RequestHeaderDTO requestHeaderDTO = super.getRequestObj();
        if (Objects.isNull(requestHeaderDTO)) {
            throw new NABException(MessageCodeEnum.COMMON_ERROR_020);
        }
        return requestHeaderDTO;
    }
}
