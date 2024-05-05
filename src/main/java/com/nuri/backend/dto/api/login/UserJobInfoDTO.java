package com.nuri.backend.dto.api.login;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserJobInfoDTO {
    private String jobPlace; //근무처
    private String jobSpecific; // 상세 근무 내용
}
