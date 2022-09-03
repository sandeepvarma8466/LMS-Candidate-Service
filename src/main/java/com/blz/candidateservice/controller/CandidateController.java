package com.blz.candidateservice.controller;

import com.blz.candidateservice.dto.CandidateDTO;
import com.blz.candidateservice.model.CandidateModel;
import com.blz.candidateservice.service.ICandidateService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
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
    public CandidateModel addCandidate(@Valid @RequestBody CandidateDTO candidateDTO) {
        return candidateService.addCandidate(candidateDTO/*, token, techStackId*/);
    }

    @PutMapping("/updatecandidate/{id}")
    public CandidateModel updateCandidate(@PathVariable("id") Long id, @Valid @RequestBody CandidateDTO candidateDTO) {
        return candidateService.updateCandidate(id, candidateDTO/*, token*/);
    }

    @GetMapping("/getcandidates")
    public List<CandidateModel> getAllCandidates() {
        return candidateService.getAllCandidates(/*token*/);
    }

    @DeleteMapping("/deleteCandidate/{id}")
    public CandidateModel deleteCandidate(@PathVariable("id") Long id) {
        return candidateService.deleteCandidate(id/*, token*/);
    }

    @GetMapping("/getCandidatebyStatus")
    public List<CandidateModel> getCandidateByStatus(@RequestParam String status) {
        return candidateService.getCandidateByStatus(status);
    }

    @PutMapping("/changestatus/{id}")
    public CandidateModel changeStatus(@PathVariable("id") Long id, @Valid @RequestParam String status, @RequestHeader String token) {
        return candidateService.changeStatus(id, status/*, token*/);
    }

    @GetMapping("/candidatecount")
    public long candidateCount(@RequestParam String status, @RequestHeader String token) {
        return candidateService.statusCount(status/*, token*/);
    }
}
