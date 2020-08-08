package au.com.nab.icommerce.base.interceptor;

import au.com.nab.icommerce.base.context.RequestHeaderHolderContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FeignClientRequestInterceptor implements RequestInterceptor {

    private static final Logger log = LoggerFactory.getLogger(FeignClientRequestInterceptor.class);

    private final RequestHeaderHolderContext requestHeaderHolderContext;

    public FeignClientRequestInterceptor(RequestHeaderHolderContext requestHeaderHolderContext) {
        this.requestHeaderHolderContext = requestHeaderHolderContext;
    }

    @SneakyThrows
    @Override
    public void apply(RequestTemplate requestTemplate) {
        log.info("At FeignClientRequestInterceptor - apply"); //TODO add header dynamic in future

        requestTemplate.header(
                "HEADER_USER",
                "Brian.truong");
    }
}
