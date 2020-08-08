package au.com.nab.icommerce.base.util;

import au.com.nab.icommerce.base.dto.JsonResponse;
import au.com.nab.icommerce.base.dto.MessageInfo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtil {

    public static JsonResponse<Object> getJsonResponseWithNoData(MessageInfo messageInfo) {
        JsonResponse<Object> response = new JsonResponse<>();
        response.setSuccess(false);
        response.setError(messageInfo);
        response.setData(null);
        return response;
    }

    public static Set<String> getNullProperties(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> Objects.isNull(wrappedSource.getPropertyValue(propertyName)))
                .collect(Collectors.toSet());
    }
}
