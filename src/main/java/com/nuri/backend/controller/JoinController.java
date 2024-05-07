package com.nuri.backend.controller;

import com.nuri.backend.dto.api.login.JoinDTO;
import com.nuri.backend.service.JoinService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
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
}