package com.nuri.backend.service;

import com.nuri.backend.dto.api.login.JoinDTO;
import com.nuri.backend.dto.api.login.UserJobInfoDTO;
import com.nuri.backend.entity.UserEntity;
import com.nuri.backend.entity.UserJobInfo;
import com.nuri.backend.repository.UserJobInfoRepository;
import com.nuri.backend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserJobInfoRepository userJobInfoRepository;

    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserJobInfoRepository userJobInfoRepository) {

        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userJobInfoRepository = userJobInfoRepository;
    }

    public void joinProcess(JoinDTO joinDTO) {

        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();

        System.out.println(password);

        List<UserJobInfoDTO> jobList = joinDTO.getUserJobInfoList();

        Boolean isExist = userRepository.existsByUsername(username);

        if (isExist) {

            return;
        }

        UserEntity data = new UserEntity();

        data.setUsername(username);
        data.setPassword(bCryptPasswordEncoder.encode(password));
        data.setRole("ROLE_USER");

        userRepository.save(data);

        for(UserJobInfoDTO jobSpecific : jobList){
            UserJobInfo jobInfo = new UserJobInfo();
            jobInfo.setJobPlace(jobSpecific.getJobPlace());
            jobInfo.setJobSpecific(jobSpecific.getJobSpecific());
            jobInfo.setUser(data);

            userJobInfoRepository.save(jobInfo);
        }
    }
}