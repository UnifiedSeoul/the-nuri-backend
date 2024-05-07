package com.nuri.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nuri.backend.domain.Edu;
import com.nuri.backend.service.EduService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EduController {

    private final EduService eduService;

    @GetMapping("/edu/test")
    public ResponseEntity<List<Edu>> saveEdu() throws JsonProcessingException {
        return ResponseEntity.ok(eduService.saveEdu());
    }

    @GetMapping("/edu")
    public ResponseEntity<List<Edu>> getAllEdu(@PageableDefault(size= 4, sort ="eduStartDate",direction = Direction.DESC) Pageable pageable) {

        return ResponseEntity.ok(eduService.getEdu(pageable));
    }

    @GetMapping("/edu/{eduId}")
    public ResponseEntity<Edu> getEduById(@PathVariable("eduId") String eduId) {

        return ResponseEntity.ok(eduService.getEduById(eduId));
    }
}
