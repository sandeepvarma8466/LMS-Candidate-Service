package com.blz.candidateservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class CandidateNotFoundException extends RuntimeException {
    private int statuscode;
    private String message;

    public CandidateNotFoundException(String message, int statuscode) {
        super(message);
        this.statuscode = statuscode;
        this.message = message;
    }
}
