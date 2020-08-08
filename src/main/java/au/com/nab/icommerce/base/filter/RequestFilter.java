package au.com.nab.icommerce.base.filter;

import au.com.nab.icommerce.base.constant.MyHttpHeader;
import au.com.nab.icommerce.base.context.RequestHeaderHolderContext;
import au.com.nab.icommerce.base.dto.RequestHeaderDTO;
import au.com.nab.icommerce.base.enumeration.MessageCodeEnum;
import au.com.nab.icommerce.base.exception.NABException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

@Component
public abstract class RequestFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(RequestFilter.class);

    private final RequestHeaderHolderContext requestHeaderHolderContext;


    public RequestFilter(RequestHeaderHolderContext requestHeaderHolderContext) {
        this.requestHeaderHolderContext = requestHeaderHolderContext;
    }

    protected abstract Boolean validateUrl(String url);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("do filter");
        String userid = request.getHeader(MyHttpHeader.HEADER_USER);
        Boolean isBusinessUrl = validateUrl(request.getRequestURI());
        if (StringUtils.isNotEmpty(userid) && isBusinessUrl) {
            RequestHeaderDTO requestHeaderDTO = new RequestHeaderDTO(userid, request.getMethod(), request.getRequestURI(), "requestBody", Instant.now(), Instant.now());
            this.requestHeaderHolderContext.setRequestObj(requestHeaderDTO);
        } else if (isBusinessUrl) {
            throw new NABException(MessageCodeEnum.COMMON_ERROR_020);
        }
        filterChain.doFilter(request, response);
    }
}
