package com.dapp.outng.common.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;

@Data
public class OutngRuntimeErrorResponse extends OutngErrorResponse {

    private static final long serialVersionUID = 1L;

    private String requestUri;
    private String rootCause;
    private int status;

    @JsonProperty("class")
    private String exceptionCauseClass;

    public OutngRuntimeErrorResponse(String message, DateTime timestamp) {
        super(message, timestamp);
    }

    public static class Builder {
        private String message;
        private DateTime timestamp;
        private String requestUri;
        private String rootCause;
        private HttpStatus status;
        private String exceptionClass;

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withTimestamp(DateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder withRootCause(String rootCause) {
            this.rootCause = rootCause;
            return this;
        }

        public Builder withRequestUri(String requestUri) {
            this.requestUri = requestUri;
            return this;
        }

        public Builder withHttpStatus(HttpStatus status) {
            this.status = status;
            return this;
        }

        public Builder withExceptionCauseClass(String exceptionCauseClass) {
            this.exceptionClass = exceptionCauseClass;
            return this;
        }

        public OutngRuntimeErrorResponse build() {
            OutngRuntimeErrorResponse exceptionDto = new OutngRuntimeErrorResponse(message, timestamp);
            exceptionDto.setRequestUri(this.requestUri);
            exceptionDto.setRootCause(this.rootCause);
            exceptionDto.setStatus(this.status.value());
            exceptionDto.setExceptionCauseClass(this.exceptionClass);

            return exceptionDto;
        }
    }
}
