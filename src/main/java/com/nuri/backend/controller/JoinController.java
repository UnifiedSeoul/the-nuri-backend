package com.nuri.backend.controller;

import com.nuri.backend.dto.JobInfoDto;
import com.nuri.backend.dto.api.login.JoinDTO;
import com.nuri.backend.service.JoinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@ResponseBody
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @PostMapping("/join")
    public String joinProcess(@RequestBody JoinDTO joinDTO) {
        System.out.println("Username: " + joinDTO.getUsername());
        joinService.joinProcess(joinDTO);
        return "ok";
    }

    @GetMapping("/check")
    public ResponseEntity<String> loginCheck(Authentication authentication){
        String username;
        if(authentication != null && authentication.isAuthenticated()){
            username = authentication.getName();
            return ResponseEntity.ok(username);
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("error");
        }
    }
}