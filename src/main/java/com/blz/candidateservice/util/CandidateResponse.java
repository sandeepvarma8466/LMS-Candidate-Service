package com.blz.candidateservice.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateResponse {
    private int errorcode;
    private String message;
    private Object token;
}
