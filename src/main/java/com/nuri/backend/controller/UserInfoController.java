package com.nuri.backend.controller;


import com.nuri.backend.dto.api.login.JoinDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class UserInfoController {

    @GetMapping("/userInfo")
    public ResponseEntity<JoinDTO> searchUserInfo
}
