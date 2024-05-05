package com.nuri.backend.dto.api.login;

import com.nuri.backend.entity.UserJobInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class JoinDTO {

    private String username;
    private String password;
    private List<UserJobInfoDTO> userJobInfoList;
}