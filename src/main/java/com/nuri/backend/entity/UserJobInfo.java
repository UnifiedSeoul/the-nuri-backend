package com.nuri.backend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_job_info")
public class UserJobInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long childId;

    @ManyToOne
    @JoinColumn(name = "userId")
    UserEntity user;

    private String jobPlace; //근무처
    private String jobSpecific; //상세 근무 내용

}
