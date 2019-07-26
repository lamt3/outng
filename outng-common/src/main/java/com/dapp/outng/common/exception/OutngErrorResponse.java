package com.dapp.outng.common.exception;

import lombok.Data;
import org.joda.time.DateTime;

import java.io.Serializable;

@Data
public class OutngErrorResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message;
    private DateTime timestamp;

    public OutngErrorResponse(String message, DateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
