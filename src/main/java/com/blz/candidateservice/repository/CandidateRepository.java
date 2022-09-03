package com.blz.candidateservice.repository;

import com.blz.candidateservice.model.CandidateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateModel, Long> {
    List<CandidateModel> findByStatusEqualsIgnoreCase(String status);
}
