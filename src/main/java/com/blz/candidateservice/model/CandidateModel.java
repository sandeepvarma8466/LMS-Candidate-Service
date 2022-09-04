package com.blz.candidateservice.model;

import com.blz.candidateservice.dto.CandidateDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "candidateInfo")
@Data
@NoArgsConstructor
public class CandidateModel {
    @Id
    @GenericGenerator(name = "candidateInfo", strategy = "increment")
    @GeneratedValue(generator = "candidateInfo")
    private Long candidateId;
    private String cicId;
    private String fullName;
    private String email;
    private String mobileNum;
    private String hiredDate;
    private String degree;
    private double aggrPer;
    private String city;
    private String state;
    private String preferredJobLocation;
    private String status;
    private String passedOutYear;
    private String creatorUser;
    private String candidateStatus;
    private LocalDateTime creationTimeStamp;
    private LocalDateTime updatedTimeStamp;

    private Long techStackEntity;

    public CandidateModel(CandidateDTO candidateDTO) {
        this.cicId = candidateDTO.getCicId();
        this.fullName = candidateDTO.getFullName();
        this.email = candidateDTO.getEmail();
        this.mobileNum = candidateDTO.getMobileNum();
        this.hiredDate = candidateDTO.getHiredDate();
        this.degree = candidateDTO.getDegree();
        this.aggrPer = candidateDTO.getAggrPer();
        this.city = candidateDTO.getCity();
        this.state = candidateDTO.getState();
        this.preferredJobLocation = candidateDTO.getPreferredJobLocation();
        this.status = candidateDTO.getStatus();
        this.passedOutYear = candidateDTO.getPassedOutYear();
        this.creatorUser = candidateDTO.getCreatorUser();
        this.candidateStatus = candidateDTO.getCandidateStatus();
        this.creationTimeStamp = LocalDateTime.now();
        this.updatedTimeStamp = LocalDateTime.now();
    }
}
