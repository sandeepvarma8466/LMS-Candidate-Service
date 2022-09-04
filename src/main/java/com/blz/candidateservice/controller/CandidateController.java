package com.blz.candidateservice.controller;

import com.blz.candidateservice.dto.CandidateDTO;
import com.blz.candidateservice.model.CandidateModel;
import com.blz.candidateservice.service.ICandidateService;
import com.blz.candidateservice.util.CandidateResponse;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/candidatemodule")
public class CandidateController {

   /* @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @PostMapping("/importCandidates")
    public void importCsvToDBJob() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAs",System.currentTimeMillis()).toJobParameters();
        try {
            jobLauncher.run(job,jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobParametersInvalidException |
                 JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        }
    }*/
    @Autowired
    ICandidateService candidateService;

    @PostMapping("/addcandidate")
    public ResponseEntity<CandidateResponse> addCandidate(@Valid @RequestBody CandidateDTO candidateDTO, @RequestHeader String token) {
        CandidateModel candidateModel = candidateService.addCandidate(candidateDTO, token);
        CandidateResponse candidateResponse = new CandidateResponse(200,"Candidate inserted successfully", candidateModel);
        return new ResponseEntity<>(candidateResponse, HttpStatus.OK);
    }

    @PutMapping("/updatecandidate/{id}")
    public ResponseEntity<CandidateResponse> updateCandidate(@PathVariable("id") Long id, @Valid @RequestBody CandidateDTO candidateDTO,
                                          @RequestHeader String token) {
        CandidateModel candidateModel = candidateService.updateCandidate(id, candidateDTO, token);
        CandidateResponse candidateResponse = new CandidateResponse(200,"Candidate updated successfully", candidateModel);
        return new ResponseEntity<>(candidateResponse, HttpStatus.OK);
    }

    @GetMapping("/getcandidates")
    public ResponseEntity<CandidateResponse> getAllCandidates(@RequestHeader String token) {
        List<CandidateModel> candidateModel = candidateService.getAllCandidates(token);
        CandidateResponse candidateResponse = new CandidateResponse(200,"Candidate Details Fetched successfully", candidateModel);
        return new ResponseEntity<>(candidateResponse, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCandidate/{id}")
    public ResponseEntity<CandidateResponse> deleteCandidate(@PathVariable("id") Long id, @RequestHeader String token) {
        CandidateModel candidateModel = candidateService.deleteCandidate(id, token);
        CandidateResponse candidateResponse = new CandidateResponse(200,"Candidate deleted successfully", candidateModel);
        return new ResponseEntity<>(candidateResponse, HttpStatus.OK);
    }

    @GetMapping("/getCandidatebyStatus")
    public ResponseEntity<CandidateResponse> getCandidateByStatus(@RequestParam String status, @RequestHeader String token) {
        List<CandidateModel> candidateModel = candidateService.getCandidateByStatus(status, token);
        CandidateResponse candidateResponse = new CandidateResponse(200,"Candidates fetcheb by status updated successfully", candidateModel);
        return new ResponseEntity<>(candidateResponse, HttpStatus.OK);
    }

    @PutMapping("/changestatus/{id}")
    public ResponseEntity<CandidateResponse> changeStatus(@PathVariable("id") Long id, @Valid @RequestParam String status, @RequestHeader String token) {
        CandidateModel candidateModel = candidateService.changeStatus(id, status, token);
        CandidateResponse candidateResponse = new CandidateResponse(200,"Candidate status updated successfully", candidateModel);
        return new ResponseEntity<>(candidateResponse, HttpStatus.OK);
    }

    @GetMapping("/candidatecount")
    public ResponseEntity<CandidateResponse> candidateCount(@RequestParam String status, @RequestHeader String token) {
        Long count = candidateService.statusCount(status, token);
        CandidateResponse candidateResponse = new CandidateResponse(200,"Candidate count", count);
        return new ResponseEntity<>(candidateResponse, HttpStatus.OK);
    }
}
