package com.dapp.outng.common.exception;

import lombok.Data;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;

@Data
public class OutngRuntimeException extends RuntimeException {

    private static final long serialVersionUUID = 1L;

    private HttpStatus httpStatus;
    private String message;
    private String requestUri;
    private DateTime timestamp = DateTime.now();

    public OutngRuntimeException(HttpServletRequest request, Exception exception, HttpStatus httpStatus) {
        this(exception, httpStatus);

        if (request != null) {
            requestUri = new UrlPathHelper().getPathWithinApplication(request);
        }
    }

    public OutngRuntimeException(Throwable throwable, HttpStatus httpStatus) {
        super(throwable);
        this.httpStatus = httpStatus;
    }

    public OutngRuntimeException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public OutngRuntimeException(String message) {
        super(message);
        this.message = message;
    }

    public OutngRuntimeException(Throwable cause) {
        super(cause);
    }

    public OutngErrorResponse getErrorResponse() {
        OutngRuntimeErrorResponse.Builder builder = new OutngRuntimeErrorResponse.Builder();
        Throwable rootCause = ExceptionUtils.getRootCause(this);
        builder.withMessage(message)
               .withHttpStatus(getHttpStatus())
               .withRequestUri(requestUri)
               .withTimestamp(timestamp)
               .withExceptionCauseClass(rootCause != null ? rootCause.getClass().getName() : null);

        return builder.build();
    }

    public HttpStatus getHttpStatus() {
        if (httpStatus == null) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return httpStatus;
    }
}
