package com.dapp.outng.common.spring;

import com.dapp.outng.common.exception.OutngRuntimeException;
import com.dapp.outng.common.http.response.BaseResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(exception, body, headers, status, request);
    }

    /**
     * Entry point for handling OutngRuntimeException
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler( {OutngRuntimeException.class} )
    public ResponseEntity<Object> handleOutngRuntimeException(final OutngRuntimeException exception, WebRequest request) {
        return handleOutngRuntimeException(exception, request, exception.getHttpStatus());
    }

    @ExceptionHandler( {Exception.class} )
    public ResponseEntity<Object> handleOutngRuntimeException(final Exception exception, WebRequest request) {
        return handleOutngRuntimeException(new OutngRuntimeException(exception), request, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private ResponseEntity<Object> handleOutngRuntimeException(OutngRuntimeException exception, WebRequest request, HttpStatus httpStatus) {
        return handleExceptionInternal(exception, new BaseResponse(exception.getErrorResponse()), new HttpHeaders(), httpStatus, request);
    }
}
