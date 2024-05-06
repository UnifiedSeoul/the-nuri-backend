package com.nuri.backend.dto.api.login;


import com.nuri.backend.entity.UserJobInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserJobInfoDTO {
    private String jobPlace; //근무처
    private String jobSpecific; // 상세 근무 내용

    public static UserJobInfoDTO from(UserJobInfo userJobInfo){
        UserJobInfoDTO userJobInfoDTO = new UserJobInfoDTO();
        userJobInfoDTO.jobPlace =userJobInfo.getJobPlace();
       userJobInfoDTO.jobSpecific = userJobInfo.getJobSpecific();

       return userJobInfoDTO;
    }
}
