package com.nuri.backend.controller;


import com.nuri.backend.dto.ApiResponse;
import com.nuri.backend.repository.UserRepository;
import com.nuri.backend.service.LoginService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
@Slf4j
public class LoginController {
    private UserRepository userRepository;
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody String oauthToken){
        String
    }


}
