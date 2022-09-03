package com.blz.candidateservice.service;

import com.blz.candidateservice.dto.CandidateDTO;
import com.blz.candidateservice.model.CandidateModel;

import java.util.List;

public interface ICandidateService {
    CandidateModel addCandidate(CandidateDTO candidateDTO);

    CandidateModel updateCandidate(Long id, CandidateDTO candidateDTO/*, String token*/);

    List<CandidateModel> getAllCandidates(/*String token*/);

    CandidateModel deleteCandidate(Long id/*, String token*/);

    List<CandidateModel> getCandidateByStatus(String status);

    CandidateModel changeStatus(Long id, String status/*, String token*/);

    long statusCount(String status/*, String token*/);
}
