package au.com.nab.icommerce.base.exception;

import au.com.nab.icommerce.base.dto.JsonResponse;
import au.com.nab.icommerce.base.enumeration.MessageCodeEnum;
import au.com.nab.icommerce.base.util.JsonUtil;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.Objects;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);
    private static final HttpHeaders headers = new HttpHeaders();

    /**
     * The logic to catch all service invalid arguments exception when validating
     *
     * @param request
     * @param ex
     * @return ResponseEntity
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleInvalidArgumentsException(WebRequest request,
                                                          ConstraintViolationException ex) {
        NABException metaException = new NABException(MessageCodeEnum.COMMON_ERROR_001);
        JsonResponse<Object> response = JsonUtil.getJsonResponseWithNoData(metaException.getMessageInfo());
        this.printLog(request, ex);
        return handleExceptionInternal(metaException, response, headers, HttpStatus.OK, request);
    }


    /**
     * The logic to catch all service error
     *
     * @param request
     * @param ex
     * @return ResponseEntity
     */
    @ExceptionHandler(NABException.class)
    public ResponseEntity<Object> serviceRuntimeException(WebRequest request, NABException ex) {

        JsonResponse<Object> response = JsonUtil.getJsonResponseWithNoData(ex.getMessageInfo());


        this.printLog(request, ex);
        return Objects.nonNull(ex.getHttpStatus()) ? handleExceptionInternal(ex, response, headers,
                ex.getHttpStatus(), request) :
                handleExceptionInternal(ex, response, headers, HttpStatus.INTERNAL_SERVER_ERROR,
                        request);
    }

    /**
     * The logic to catch all service error
     *
     * @param request
     * @param ex
     * @return ResponseEntity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(WebRequest request, Exception ex) {
        NABException metaException = new NABException(MessageCodeEnum.COMMON_ERROR_500);
        JsonResponse<Object> response = JsonUtil
                .getJsonResponseWithNoData(metaException.getMessageInfo());
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.printLog(request, ex);
        if (ex instanceof AccessDeniedException) {
            metaException = new NABException(MessageCodeEnum.COMMON_ERROR_019);
            response = JsonUtil
                    .getJsonResponseWithNoData(metaException.getMessageInfo());
            status = HttpStatus.UNAUTHORIZED;
        }
        return
                handleExceptionInternal(ex, response, headers, status,
                        request);
    }

    /**
     * The logic to catch access denied exception error
     *
     * @param request
     * @param ex
     * @return ResponseEntity
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(WebRequest request, Exception ex) {
        NABException metaException = new NABException(MessageCodeEnum.COMMON_ERROR_019);
        JsonResponse<Object> response = JsonUtil
                .getJsonResponseWithNoData(metaException.getMessageInfo());
        this.printLog(request, ex);
        return
                handleExceptionInternal(ex, response, headers, HttpStatus.UNAUTHORIZED,
                        request);
    }

    /**
     * Print the error log
     *
     * @param request
     * @param e
     */
    private void printLog(WebRequest request, Exception e) {
        log.error("GlobalExceptionHandler error :: msg -> {} :: class -> {}",
                request.getContextPath(), e.getMessage(), e.getClass(), e);
    }
}
