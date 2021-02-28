package info.akahoris.localremo.controller;

import info.akahoris.localremo.component.ErrorResponse.ErrorResponse;
import info.akahoris.localremo.component.ErrorResponse.NotFoundSignal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundSignal.class)
    public ResponseEntity<Object> handleNotFoundSignalException(NotFoundSignal ex, WebRequest request) {
        return super.handleExceptionInternal(ex, new ErrorResponse(ex.getMessage(), "404"), null, HttpStatus.NOT_FOUND, request);
    }

}
