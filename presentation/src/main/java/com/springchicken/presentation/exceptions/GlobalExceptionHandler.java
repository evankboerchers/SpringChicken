package com.springchicken.presentation.exceptions;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import com.springchicken.logic.exceptions.CustomerNumberNotFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Handles all exceptions thrown by controllers in the presentation layer
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
    /**
     * Handles customer not found exception
     * @param ex exception for customer number not found
     * @param request generic web request
     * @return Response with customer number not found error
     */
    @ExceptionHandler(CustomerNumberNotFoundException.class)
    public final ResponseEntity<ErrorMessage> customerNotFoundException(CustomerNumberNotFoundException ex, WebRequest request)
    {
        ErrorMessage message = new ErrorMessage(
                (new Date()).toString(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                "Customer Number Not Found",
                request.getDescription(false));

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND) ;
    }
}






