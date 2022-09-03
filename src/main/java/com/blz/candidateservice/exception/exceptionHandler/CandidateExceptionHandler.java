package com.blz.candidateservice.exception.exceptionHandler;

import com.blz.candidateservice.exception.CandidateNotFoundException;
import com.blz.candidateservice.util.CandidateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CandidateExceptionHandler {
    @ExceptionHandler(CandidateNotFoundException.class)
    public ResponseEntity<CandidateResponse> response(CandidateNotFoundException exception) {
        CandidateResponse candidateResponse = new CandidateResponse();
        candidateResponse.setErrorcode(400);
        candidateResponse.setMessage(exception.getMessage());
        return new ResponseEntity<>(candidateResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<CandidateResponse>
    defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        CandidateResponse candidateResponse = new CandidateResponse();
        candidateResponse.setErrorcode(500);
        candidateResponse.setMessage(e.getMessage());
        return new ResponseEntity<CandidateResponse>(candidateResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
