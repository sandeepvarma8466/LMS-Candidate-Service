package com.blz.candidateservice.service;

import com.blz.candidateservice.dto.CandidateDTO;
import com.blz.candidateservice.exception.CandidateNotFoundException;
import com.blz.candidateservice.model.CandidateModel;
import com.blz.candidateservice.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class CandidateService implements ICandidateService {
    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    MailService mailService;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public CandidateModel addCandidate(CandidateDTO candidateDTO, String token) {
        boolean isCandidatePresent = restTemplate.getForObject("http://localhost:8068/adminmodule/validateuser/" +
                token, Boolean.class);
        if (isCandidatePresent) {
            CandidateModel candidateModel = new CandidateModel(candidateDTO);
            candidateRepository.save(candidateModel);
            String body = "Candidate Added Successfully with Candidate Id" + candidateModel.getCandidateId();
            String subject = "Candidate Added successfully";
            mailService.send(candidateModel.getEmail(), subject, body);
            return candidateModel;
        }
        throw new CandidateNotFoundException("Candidate token not Found", 500);
    }

    @Override
    public CandidateModel updateCandidate(Long id, CandidateDTO candidateDTO, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8068/adminmodule/validateuser/" + token,
                Boolean.class);
        if (isUserPresent) {
            Optional<CandidateModel> isCandidatePresent = candidateRepository.findById(id);
            if (isCandidatePresent.isPresent()) {
                isCandidatePresent.get().setCicId(candidateDTO.getCicId());
                isCandidatePresent.get().setFullName(candidateDTO.getFullName());
                isCandidatePresent.get().setEmail(candidateDTO.getEmail());
                isCandidatePresent.get().setMobileNum(candidateDTO.getMobileNum());
                isCandidatePresent.get().setHiredDate(candidateDTO.getHiredDate());
                isCandidatePresent.get().setDegree(candidateDTO.getDegree());
                isCandidatePresent.get().setAggrPer(candidateDTO.getAggrPer());
                isCandidatePresent.get().setCity(candidateDTO.getCity());
                isCandidatePresent.get().setState(candidateDTO.getState());
                isCandidatePresent.get().setPreferredJobLocation(candidateDTO.getPreferredJobLocation());
                isCandidatePresent.get().setStatus(candidateDTO.getStatus());
                isCandidatePresent.get().setPassedOutYear(candidateDTO.getPassedOutYear());
                isCandidatePresent.get().setCreatorUser(candidateDTO.getCreatorUser());
                isCandidatePresent.get().setCandidateStatus(candidateDTO.getCandidateStatus());
                isCandidatePresent.get().setCreationTimeStamp(LocalDateTime.now());
                isCandidatePresent.get().setUpdatedTimeStamp(LocalDateTime.now());
                candidateRepository.save(isCandidatePresent.get());
                String body = "Candidate Updated Successfully with Candidate Id" + isCandidatePresent.get().getCandidateId();
                String subject = "Candidate Updated successfully";
                mailService.send(isCandidatePresent.get().getEmail(), subject, body);
                return isCandidatePresent.get();
            }
            throw new CandidateNotFoundException("Candidate Not Found", 500);
        }
        throw new CandidateNotFoundException("Incorrect Token", 500);
    }

    @Override
    public List<CandidateModel> getAllCandidates(String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8068/adminmodule/validateuser/" + token,
                Boolean.class);
        if (isUserPresent) {
            List<CandidateModel> checkData = candidateRepository.findAll();
            if (checkData.size() > 0) {
                return checkData;
            }
            throw new CandidateNotFoundException("Candidate Not Found", 500);
        }
        throw new CandidateNotFoundException("Incorrect Token", 500);
    }

    @Override
    public CandidateModel deleteCandidate(Long id, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8068/adminmodule/validateuser/" + token,
                Boolean.class);
        if (isUserPresent) {
            Optional<CandidateModel> isCandidatePresent = candidateRepository.findById(id);
            if (isCandidatePresent.isPresent()) {
                candidateRepository.delete(isCandidatePresent.get());
                String body = "Candidate Deleted Successfully with Candidate Id" + isCandidatePresent.get().getCandidateId();
                String subject = "Candidate Added successfully";
                mailService.send(isCandidatePresent.get().getEmail(), subject, body);
                return isCandidatePresent.get();
            }
            throw new CandidateNotFoundException("Candidate Not Found", 500);
        }
        throw new CandidateNotFoundException("Incorrect Token", 500);
    }

    @Override
    public List<CandidateModel> getCandidateByStatus(String status, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8068/adminmodule/validateuser/" + token,
                Boolean.class);
        if (isUserPresent) {
            List<CandidateModel> isStatusPresent = candidateRepository.findByStatusEqualsIgnoreCase(status);
            if (isStatusPresent.size() > 0) {
                return isStatusPresent;
            }
            throw new CandidateNotFoundException("Status not found", 500);
        }
        throw new CandidateNotFoundException("Invalid token", 500);
    }

    @Override
    public CandidateModel changeStatus(Long id, String status, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8068/adminmodule/validateuser/" + token,
                Boolean.class);
        if (isUserPresent) {
            Optional<CandidateModel> isIdPresent = candidateRepository.findById(id);
            if (isIdPresent.isPresent()) {
                isIdPresent.get().setStatus(status);
                candidateRepository.save(isIdPresent.get());
                String body = "Candidate Status changed Successfully with Candidate Id" + isIdPresent.get().getCandidateId();
                String subject = "Candidate Status changed successfully";
                mailService.send(isIdPresent.get().getEmail(), subject, body);
                return isIdPresent.get();
            }
            throw new CandidateNotFoundException("Status not found", 500);
        }
        throw new CandidateNotFoundException("Invalid token", 500);
    }

    @Override
    public long statusCount(String status, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8068/adminmodule/validateuser/" + token,
                Boolean.class);
        if (isUserPresent) {
            List<CandidateModel> isStatusPresent = candidateRepository.findByStatusEqualsIgnoreCase(status);
            if (isStatusPresent.size() > 0) {
                return isStatusPresent.stream().count();
            }
            throw new CandidateNotFoundException("Status not found", 500);
        }
        throw new CandidateNotFoundException("Invalid token", 500);
    }
}
