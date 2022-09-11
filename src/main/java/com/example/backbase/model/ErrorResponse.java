package com.example.backbase.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ErrorResponse {
    private static final long serialVersionUID = 990514568261392629L;

    private String errorCategory;
    private String errorCode;
    private String traceId;
    private Integer severity;
    private String errorMessage;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    public ErrorResponse(String errorCategory, String errorCode, Integer severity, String errorMessage) {
        this.errorCategory = errorCategory;
        this.errorCode = errorCode;
        this.severity = severity;
        this.errorMessage = errorMessage;
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(String errorCode, Integer severity, String errorMessage) {
        this.errorCode = errorCode;
        this.severity = severity;
        this.errorMessage = errorMessage;
        this.timestamp = LocalDateTime.now();
    }
}
