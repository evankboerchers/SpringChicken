package com.springchicken.presentation;

import com.springchicken.presentation.exceptions.ErrorMessage;
import com.springchicken.presentation.shutdown.Status;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * Defines endpoints for checking service health
 */
@SuppressWarnings("checkstyle:magicnumber")
@RestController
@RequestMapping("/health")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class HealthStatusController
{
    private Status status;

    @Autowired
    public HealthStatusController(Status status)
    {
        this.status = status;
    }

    /**
     * Map response to /health endpoint
     * @return Json representation of the service status
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public ResponseEntity responseHealthCheck()
    {
        if (status.getIsShutdown())
        {
            return new ResponseEntity<>(new ErrorMessage((new Date()).toString(), 404,
                    "Server in shutdown", "",""), HttpStatus.NOT_FOUND);
        }
        else
            {
            return new ResponseEntity<>(new ErrorMessage(new Date().toString(), 200, "",
                    "Server is online", ""), HttpStatus.OK);
        }
    }

    @Override
    public String toString()

    {
        return ReflectionToStringBuilder.toString(this);
    }
}
