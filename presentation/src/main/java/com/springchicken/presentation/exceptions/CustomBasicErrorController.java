package com.springchicken.presentation.exceptions;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Defines endpoint for Springs default error mapping
 */
@RestController
@RequestMapping("/error")
@PreAuthorize("permitAll()")
public class CustomBasicErrorController extends BasicErrorController
{
    public CustomBasicErrorController(ErrorAttributes errorAttributes, ServerProperties serverProperties)
    {
        super(errorAttributes, serverProperties.getError());
    }

    /**
     * Creates error response for default mapping
     * @param request Httpservlet request
     * @return Response with specific error message
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> handleError(HttpServletRequest request)
    {
        HttpStatus status = getStatus(request);
        Map<String, Object> body = getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.APPLICATION_JSON));

        return new ResponseEntity<>(body, status);
    }
}


